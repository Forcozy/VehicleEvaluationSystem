<template>
  <!--  <h3>评价排名表</h3>-->
  <h6></h6>
  <el-card>
    <el-row :gutter="20" class="header">  <!--gutter间距-->

      <el-col :span="4">

        <div class="container">
          <el-button @click="QueryTodaysInformation_handleButtonClick" :style="QueryTodaysInformation_buttonStyle">
            <div v-if="QueryTodaysInformation_buttonStatus">
              <el-icon class="el-icon-check">
                <Check/>
              </el-icon>
            </div>
            查询今日所有评价数据
          </el-button>
          <!--          QueryTodaysInformation_buttonStatus = {{ QueryTodaysInformation_buttonStatus }}-->
        </div>
      </el-col>
      <el-col :span="7">
        <div class="container">
          <el-button @click="QueryInformationForThisMonth_handleButtonClick"
                     :style="QueryInformationForThisMonth_buttonStyle">
            <div v-if="QueryInformationForThisMonth_buttonStatus">
              <el-icon class="el-icon-check">
                <Check/>
              </el-icon>
            </div>
            查询本月所有评价数据
          </el-button>
          <!--          QueryInformationForThisMonth_buttonStatus = {{ QueryInformationForThisMonth_buttonStatus }}-->
        </div>
      </el-col>


    </el-row>
    <el-row :gutter="20" class="header">  <!--gutter间距-->

      <!--      <el-icon class="el-icon-check">
              <Check/>
            </el-icon>-->
    </el-row>

    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column :prop="item.prop" :label="item.label" v-for="(item,index) in options" :key="index"/>


      <el-table-column prop="action" label="操作" width="180">
        <template v-slot="{ row }">
          <!-- 在这里使用 row 变量获取当前行的数据 -->


          <el-button type="primary" :icon="Edit"
                     @click="router.push({name:'司机评价记录查询表',params: {id: row.name}})">
            <!--            查看详情-->
          </el-button>


        </template>


      </el-table-column>


    </el-table>

    <br/>

    <el-config-provider :locale="locale">
      <el-pagination
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 30, 40]"
          :small="small"
          :disabled="disabled"
          :background="background"
          layout="total, sizes, prev, pager, next, jumper"
          :total="Total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
      />
    </el-config-provider>

  </el-card>

</template>

<script setup>
import {computed, onMounted, ref, watch, watchEffect} from "vue";
import axios from "axios";
import {options} from "@/components/EvaluationRankingTable/options.js";
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'
import {Delete, Edit} from "@element-plus/icons-vue";
import {useTokenStore} from '@/stores/token.js'
import {List} from "echarts";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";

const Tokenstore = useTokenStore()

const language = ref('zh-cn')  //设置语言，默认中文
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))


const small = ref(false)   //分页组件
const background = ref(false)
const disabled = ref(false)

const pageSize = ref(10)  //每页(默认)显示条数
const pageNum = ref(1) //当前(默认)页码
const Total = ref(0)       //总数


watchEffect(() => {           //校验token
  if (Tokenstore.token === '') {     //token为空时，就是没登陆，闭着眼睛去登录
    console.log("onMounted ========   if (Tokenstore.token === ''){   ===========> ")
    ElMessage.error("登录信息已失效，请重新登录")
    router.push('/Login/welcome_login')
  }
  axios.post("/api/VerifyToken", {  //token不为空时，将token发送给后端校验，校验失败就算过期，重新登陆
        token: Tokenstore.token
      }, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("打印出返回的json ==>  ")
    console.log(res)
    if (res.data.code === 0) {
      console.log("token有效") //0成功1失败
    } else {
      Tokenstore.removeToken()
      ElMessage.error("登录信息已过期，请重新登录")
      router.push('/Login/welcome_login')
      console.log("token无效") //0成功1失败
    }
  });
})

//每页显示条数选项
const pagesize_options = [
  {
    value: '10',      /* 实际存到变量内的 */
    label: '10',      /* 显示到前端上面的 */
  },
  {
    value: '20',
    label: '20',
  },
  {
    value: '50',
    label: '50',
  },
  {
    value: '100',
    label: '100',
  },
  {
    value: '200',
    label: '200',
  },
]

/* 有用到的 */
const tableData = ref(null);  /* 表格数据存放处 */

//每页显示条数改变
const handleSizeChange = () => {
  console.log("每页显示条数改变  handleSizeChange  ==>")
  console.log("最新每页显示条数  pageSize =>", pageSize.value)
  console.log("最新当前页码pageNum =>", pageNum.value)
  RequestConditionEvaluator()//请求条件判断器
}

//当前页码数改变
const handleCurrentChange = () => {
  console.log("当前页码数改变  handleCurrentChange  ==>")
  console.log("最新每页显示条数  pageSize =>", pageSize.value)
  console.log("最新当前页码pageNum =>", pageNum.value)
  RequestConditionEvaluator()//请求条件判断器
}


const RequestConditionEvaluator = () => {  //请求条件判断器(根据不同的请求标志位来请求不同的地址)
  if (QueryTodaysInformation_buttonStatus.value) {
    console.log("请求条件判断器 查询今日所有评价数据  if (QueryTodaysInformation_buttonStatus){   ==>")
    GetQueryAllEvaluationDataForToday()
  } else if (QueryInformationForThisMonth_buttonStatus.value) {
    console.log("请求条件判断器 查询本月所有评价数据  if (QueryTodaysInformation_buttonStatus){   ==>")
    QueryAllEvaluationDataForThisMonth()
  } else {
    console.log("请求条件判断器 无标志位 查询全部   ==>")
    GetPagingDataAndTotal()
  }
}

const AssignFToAllFlagBits = () => {  //将所有标志位赋F(凡是标志位和颜色替换就要加到这里面)  标志位为F背景颜色为蓝色
  pageNum.value = 1//由于更新了筛选条件，页码重置为1
  QueryTodaysInformation_buttonStatus.value = false
  QueryTodaysInformation_buttonStyle.value.backgroundColor = '#409eff'
  QueryInformationForThisMonth_buttonStatus.value = false
  QueryInformationForThisMonth_buttonStyle.value.backgroundColor = '#409eff'
}

// 定义一个函数来转换时间格式
function formatEvaluationTime(evaluationTime) {
  const date = new Date(evaluationTime);
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  const hours = date.getHours().toString().padStart(2, '0');
  const minutes = date.getMinutes().toString().padStart(2, '0');
  const seconds = date.getSeconds().toString().padStart(2, '0');
  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

//评价信息修改成对应的值
function EvaluationLevel(value) {
  let value1 = Number(value); // 将Object转换为数值
  switch (value1) {
    case 1:
      return '不及格';
    case 2:
      return '及格';
    case 3:
      return '良好';
    case 4:
      return '优秀';
    default:
      return '未知';
  }
}

const DataFormatOptimization = () => { //数据格式优化(将请求到的数据转成想要看到的)
  /* for (let p1 = 0; p1 < tableData.value.length; p1++) { //格式转换
     tableData.value[p1].evaluationTime = formatEvaluationTime(tableData.value[p1].evaluationTime); //时间戳转正常时间格式
     tableData.value[p1].evaluationindicator1 = EvaluationLevel(tableData.value[p1].evaluationindicator1);//评价信息修改成对应的值
     tableData.value[p1].evaluationindicator2 = EvaluationLevel(tableData.value[p1].evaluationindicator2);
   }
   */

}


const QueryTodaysInformation_buttonStyle = ref({     //查询今日的按钮样式
  backgroundColor: '#409eff', // 按钮初始背景颜色
  color: '#fff', // 按钮文字颜色
  padding: '10px 20px', // 内边距
  border: 'none', // 边框
  borderRadius: '4px', // 圆角
  cursor: 'pointer' // 鼠标样式
});
const QueryTodaysInformation_buttonStatus = ref(false)   //查询今日的按钮标志位
const QueryTodaysInformation_handleButtonClick = () => {    //查询今日的按钮函数
  console.log("const QueryTodaysInformation_handleButtonClick = () => {    //查询今日的按钮函数    ====>")
  let Temp = QueryTodaysInformation_buttonStatus.value //先存当前标志位信息
  AssignFToAllFlagBits()   //将所有标志位赋F
  QueryTodaysInformation_buttonStatus.value = Temp.valueOf()  //取回当前标志位信息（保证最多只有一个标志位为T）
  QueryTodaysInformation_buttonStatus.value = !QueryTodaysInformation_buttonStatus.value
// 当标志位为T时，背景颜色为绿色，为F时，背景颜色为蓝色，
  if (QueryTodaysInformation_buttonStatus.value) {
    QueryTodaysInformation_buttonStyle.value.backgroundColor = '#67c23a'
  } else {
    QueryTodaysInformation_buttonStyle.value.backgroundColor = '#409eff'
  }
  RequestConditionEvaluator()
};


const QueryInformationForThisMonth_buttonStyle = ref({     //查询本月信息的按钮样式(1)带1就替换名字即可
  backgroundColor: '#409eff', // 按钮初始背景颜色
  color: '#fff', // 按钮文字颜色
  padding: '10px 20px', // 内边距
  border: 'none', // 边框
  borderRadius: '4px', // 圆角
  cursor: 'pointer' // 鼠标样式
});
const QueryInformationForThisMonth_buttonStatus = ref(false)   //查询本月的按钮标志位(1)
const QueryInformationForThisMonth_handleButtonClick = () => {    //查询本月的按钮函数(1)
  console.log("const QueryInformationForThisMonth_handleButtonClick = () => {    //查询本月的按钮函数(1)")
  let Temp = QueryInformationForThisMonth_buttonStatus.value //先存当前标志位信息(1)
  AssignFToAllFlagBits()   //将所有标志位赋F
  QueryInformationForThisMonth_buttonStatus.value = Temp  //取回当前标志位信息（保证最多只有一个标志位为T）(1)
  QueryInformationForThisMonth_buttonStatus.value = !QueryInformationForThisMonth_buttonStatus.value
  // 当标志位为T时，背景颜色为绿色，为F时，背景颜色为蓝色(3)，
  if (QueryInformationForThisMonth_buttonStatus.value) {
    QueryInformationForThisMonth_buttonStyle.value.backgroundColor = '#67c23a'
  } else {
    QueryInformationForThisMonth_buttonStyle.value.backgroundColor = '#409eff'
  }
  RequestConditionEvaluator()
};


//获取分页好的数据和总数 （查询全部）
const GetPagingDataAndTotal = () => {

  axios.post("/api/GetAllDriverRankingPaging", {
    pageSize: pageSize.value,
    pageNum: pageNum.value
  }, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/GetAllDriverRankingPaging,{},{  ==>")
    /*    console.log(res)*/
    console.log(res.data.data)
    tableData.value = res.data.data.items
    Total.value = res.data.data.total
    /*
        console.log("tableData.value.length =")
        console.log(tableData.value.length)

        console.log("tableData =")
        console.log(tableData)
        console.log("typeof tableData =");
        console.log(typeof tableData);    // "string"

        console.log("typeof tableData.value =");
        console.log(typeof tableData.value);
        console.log(" for (let p1 = 0;p1<tableData.value.length;p1++){ =")*/
    DataFormatOptimization()
    //formattedEvaluations()
  })

}


//初始化
onMounted(() => {
  console.log("onMounted ===================> ")
  RequestConditionEvaluator()//请求条件判断器
//直接将当前页的页码和每页条数和查询条件发送给后端，返回带总数和分好页的数据回来直接渲染即可
})


//获取分页好的今日数据 （获取今日数据）
const GetQueryAllEvaluationDataForToday = () => {

  axios.post("/api/GetAllDriverRankingToday", {
    pageSize: pageSize.value,
    pageNum: pageNum.value
  }, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/GetAllDriverRankingToday,{},{  ==>")
    console.log(res.data.data)
    tableData.value = res.data.data.items
    Total.value = res.data.data.total
    DataFormatOptimization()
  })

}

//获取分页好的本月数据 （获取本月数据）
const QueryAllEvaluationDataForThisMonth = () => {

  axios.post("/api/GetAllDriverRankingthismonth", {
    pageSize: pageSize.value,
    pageNum: pageNum.value
  }, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/GetAllDriverRankingthismonth,{},{  ==>")
    console.log(res.data.data)
    tableData.value = res.data.data.items
    Total.value = res.data.data.total
    DataFormatOptimization()
  })

}


</script>


<style scoped>
.header {
  padding-bottom: 16px;
  box-sizing: border-box;
}

.demo-pagination-block + .demo-pagination-block {
  margin-top: 10px;
}

.demo-pagination-block .demonstration {
  margin-bottom: 16px;
}

.upload-demo {
  display: inline-block;
  /* margin-top: 10px;*/
}

.container {
  display: flex; /*   */
}

</style>




