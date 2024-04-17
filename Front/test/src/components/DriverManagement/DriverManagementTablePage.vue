<template>
  <!--  <h3>司机管理表</h3>-->
  <!-- 查询司机  生成所有司机的二维码 -->
  <h6></h6>
  <el-card>
    <el-row :gutter="20" class="header">  <!--gutter间距-->

      <el-col :span="7">
        <div class="container1">
          <el-input v-model="DriverName" style="width: 240px" placeholder="请输入司机名" clearable/>
          &nbsp; &nbsp;
          <div class="container">
            <el-button @click="DriverNameQuery_handleButtonClick"
                       :style="DriverNameQuery_buttonStyle">
              <div v-if="DriverNameQuery_buttonStatus">
                <el-icon class="el-icon-check">
                  <Check/>
                </el-icon>
              </div>

              {{ cz }}


            </el-button>


            <!--          DriverNameQuery_buttonStatus = {{ DriverNameQuery_buttonStatus }}-->
          </div>
        </div>
      </el-col>
      <el-col :span="7">
        <el-button type="primary" @click="downloadFile">导出全部司机的二维码</el-button>
      </el-col>


      <el-col :span="7">


        <el-button type="primary" style="margin-left: 26px" @click="drawer2 = true">
          新增司机
        </el-button>

        <el-drawer
            v-model="drawer2"
            :direction="direction"
        >
          <template #header>
            <h4>新增司机</h4>
          </template>
          <template #default>

            请输入要新增的司机的姓名：
            <el-input v-model="testInsertIntoDriverName"></el-input>

          </template>
          <template #footer>
            <div style="flex: auto">
              <el-button>取消</el-button>
              <el-button type="primary" @click="confirmClick">提交</el-button>
            </div>
          </template>
        </el-drawer>

      </el-col>

    </el-row>


    <el-table :data="tableData" border stripe style="width: 100%">
      <el-table-column :prop="item.prop" :label="item.label" v-for="(item,index) in options" :key="index"/>

      <el-table-column prop="action" label="操作" width="280">
        <template v-slot="{ row }"><!-- 在这里使用 row 变量获取当前行的数据 -->
          <!--
                    <el-button type="primary" :icon="Edit"
                               @click="router.push({name:'司机评价记录查询表',params: {id: row.name}})">
                      &lt;!&ndash;            查看详情&ndash;&gt;
                    </el-button>
          -->

          <!--
                    <el-button type="primary" :icon="Edit"
                               @click="router.push({name:'修改司机管理表',params: {id: row}})">
                    </el-button>
          -->
          <el-button plain type="primary" :icon="Edit" @click="open(row)">编辑</el-button>
          <!--          <el-button plain type="primary" :icon="Delete" @click="deletedata(row)">删除</el-button>
                   -->
          <el-button plain type="primary" :icon="Delete" @click="deleteopen(row)"><!--@click="dialogVisible1 = true">-->
            删除
          </el-button>

          <el-dialog
              v-model="dialogVisible1"
              title=" 注意 "
              width="500"
              :before-close="handleClose1"
          >
            <span> 删除该司机同时会删除该司机对应的所有评价数据，请谨慎操作 </span>
            <template #footer>
              <div class="dialog-footer">
                <el-button @click="dialogVisible1 = false">关闭窗口</el-button>
                <el-button type="primary" @click="delectdata(row)" >
                  确认操作
                </el-button>
              </div>
            </template>
          </el-dialog>


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
import {options} from "@/components/DriverManagement/options.js";
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import en from 'element-plus/dist/locale/en.mjs'
import {Delete, Edit} from "@element-plus/icons-vue";
import {useTokenStore} from '@/stores/token.js'
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const kkkktest = () => {

}


const Tokenstore = useTokenStore()

const language = ref('zh-cn')  //设置语言，默认中文
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))


const small = ref(false)   //分页组件
const background = ref(false)
const disabled = ref(false)

const pageSize = ref(10)  //每页(默认)显示条数
const pageNum = ref(1) //当前(默认)页码
const Total = ref(0)       //总数

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
const DriverName = ref("") //司机名
const cz = ref("按照名字查找司机")


/*
const dowmtest = () => {


// 发送 GET 请求，下载文件
      const downloadUrl = "/api/file/downloadDoc";
      axios({
        url: downloadUrl,
        method: "get",
        responseType: "blob",
        headers: {
          "Content-Type": "application/json;charset=UTF-8",
        },
      })
          .then((response) => {
            const downloadElement = document.createElement("a");
            const blob = new Blob([response.data]);
            const url = window.URL.createObjectURL(blob);
            downloadElement.href = url;
            downloadElement.download = "7.txt";
            document.body.appendChild(downloadElement);
            downloadElement.click();
            document.body.removeChild(downloadElement);
          })
          .catch((error) => {
            console.log(error);
            alert("下载文件失败");
          });













/!*
  axios.get('/api/file/download/7.txt',
      {
        headers: {
          'Authorization': Tokenstore.token,
        }, responseType: 'blob'
      }
  ).then((res) => {
    console.log('文件下载成功');
    const blob = new Blob([res.data]);
    const fileName = "7.txt";

    //对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
    //IE10以上支持blob，但是依然不支持download
    if ('download' in document.createElement('a')) {
      //支持a标签download的浏览器
      const link = document.createElement('a');//创建a标签
      link.download = fileName;//a标签添加属性
      link.style.display = 'none';
      link.href = URL.createObjectURL(blob);
      document.body.appendChild(link);
      link.click();//执行下载
      URL.revokeObjectURL(link.href); //释放url
      document.body.removeChild(link);//释放标签
    } else {
      navigator.msSaveBlob(blob, fileName);
    }
  }).catch((res) => {
    console.log('文件下载失败');
  });*!/
  /!*
  axios.get('/api/file/download/',
      {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }, responseType: 'blob'
      }
  ).then((res) => {
    console.log('文件下载成功');
    const blob = new Blob([res.data]);
    const fileName = "司机对应的二维码.zip";

    //对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
    //IE10以上支持blob，但是依然不支持download
    if ('download' in document.createElement('a')) {
      //支持a标签download的浏览器
      const link = document.createElement('a');//创建a标签
      link.download = fileName;//a标签添加属性
      link.style.display = 'none';
      link.href = URL.createObjectURL(blob);
      document.body.appendChild(link);
      link.click();//执行下载
      URL.revokeObjectURL(link.href); //释放url
      document.body.removeChild(link);//释放标签
    } else {
      navigator.msSaveBlob(blob, fileName);
    }
  }).catch((res) => {
    console.log('文件下载失败');
  });*!/


}
*/
/*
const downloadFile = async () => {
  try {
    const response = await axios({
      method: 'get',
      url: '/api/download', // 后端下载接口的URL
      responseType: 'blob' // 重要，设置响应类型为blob
    });

    fileBlob.value = response.data;
    const url = window.URL.createObjectURL(fileBlob.value);
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', 'filename.ext'); // 你想要保存的文件名
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
  } catch (error) {
    console.error('文件下载出错:', error);
  }
};

*/

//const downloadUrl = '/api/download/1.txt'; // 后端下载文件的URL
const downloadFile = () => {
  // 发送GET请求下载文件
  axios({
    url: '/api/file/download', // 后端提供的下载接口
    method: 'GET',
    responseType: 'blob', // 指定响应类型为blob，以便处理文件流
    headers: {
      'Authorization': Tokenstore.token
    },
  })
      .then((response) => {
        // 创建一个a标签，模拟点击下载文件
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', '全部司机对应的二维码.zip'); // 指定下载的文件名
        document.body.appendChild(link);
        link.click();
      })
      .catch((error) => {
        console.error('Error downloading file: ', error);
      });

}


/*
  // 文件下载路径，根据实际情况进行修改
  const filePath = ref('/api/download/1.zip');

  // 发送文件下载请求
 console.log("dasfsafa")
    axios({
      url: filePath.value,
      method: 'GET',
      responseType: 'blob', // 指定响应数据类型为二进制流
      headers: {
        'Authorization': Tokenstore.token
      },
    })
        .then(response => {
          // 创建一个 Blob 对象
          const blob = new Blob([response.data]);
          // 创建一个 <a> 元素
          const link = document.createElement('a');
          // 设置 <a> 元素的 href 属性为 Blob 对象的 URL
          link.href = window.URL.createObjectURL(blob);
          // 设置下载的文件名，根据实际情况进行修改
          link.download = 'file.zip';
          // 触发点击事件进行下载
          link.click();
        })
        .catch(error => {
          console.error('File download failed:', error);
        });

*/

/* console.log("dasd =>")
 // 发送获取 CSV 文件的请求
 axios.get("/api/download", { responseType: 'blob' })
     .then(response => {
       const blob = new Blob([response.data], { type: 'text/csv' });// 创建 Blob 对象并下载文件
       const url = window.URL.createObjectURL(blob);
       const link = document.createElement('a');
       link.href = url;
       link.download = 'xm.csv';
       link.click();// 模拟点击事件，触发下载
       window.URL.revokeObjectURL(url);// 清理
     })
     .catch(error => {
       console.error('下载 CSV 文件失败', error);
     });*/

/*
};
*/
/*

const downloadFile = async () => {
  try {
    // 替换为你的API端点
    const url = '/api/files/download/1.txt';
    const response = await axios({
      url,
      method: 'GET',
      responseType: 'blob', // 告诉axios你期待服务器响应的数据类型
      headers: {
        'Authorization': Tokenstore.token
      },

    });

    // 创建一个blob对象
    const blob = new Blob([response.data], { type: response.headers['content-type'] });

    // 创建一个下载链接
    const downloadLink = document.createElement('a');
    downloadLink.href = window.URL.createObjectURL(blob);
    downloadLink.download = 'filename.txt'; // 设置下载文件的名称
    document.body.appendChild(downloadLink);
    downloadLink.click();
    document.body.removeChild(downloadLink);
  } catch (error) {
    console.error('下载文件时出错:', error);
  }
};

*/




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
  if (DriverNameQuery_buttonStatus.value) {
    console.log("请求条件判断器 查询司机  if (DriverNameQuery_buttonStatus){   ==>")
    DriverNameQuery()
  } else {
    console.log("请求条件判断器 无标志位 查询全部   ==>")
    GetPagingDataAndTotal()
  }
}

const AssignFToAllFlagBits = () => {  //将所有标志位赋F(凡是标志位和颜色替换就要加到这里面)  标志位为F背景颜色为蓝色
  pageNum.value = 1//由于更新了筛选条件，页码重置为1
  DriverNameQuery_buttonStatus.value = false
  DriverNameQuery_buttonStyle.value.backgroundColor = '#409eff'


}

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


// 定义一个函数来转换时间格式
function formatEvaluationTime(indata) {
  const date = new Date(indata);
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
  for (let p1 = 0; p1 < tableData.value.length; p1++) { //格式转换
    tableData.value[p1].indata = formatEvaluationTime(tableData.value[p1].indata); //时间戳转正常时间格式
    // tableData.value[p1].evaluationindicator1 = EvaluationLevel(tableData.value[p1].evaluationindicator1);//评价信息修改成对应的值
    // tableData.value[p1].evaluationindicator2 = EvaluationLevel(tableData.value[p1].evaluationindicator2);
  }
}


const DriverNameQuery_buttonStyle = ref({     //查询今日的按钮样式
  backgroundColor: '#409eff', // 按钮初始背景颜色
  color: '#fff', // 按钮文字颜色
  padding: '10px 20px', // 内边距
  border: 'none', // 边框
  borderRadius: '4px', // 圆角
  cursor: 'pointer' // 鼠标样式
});
const DriverNameQuery_buttonStatus = ref(false)   //查询今日的按钮标志位

const DriverNameQuery_handleButtonClick = () => {    //查询今日的按钮函数
  console.log("const DriverNameQuery_handleButtonClick = () => {    //查询今日的按钮函数    ====>")
  let Temp = DriverNameQuery_buttonStatus.value //先存当前标志位信息
  AssignFToAllFlagBits()   //将所有标志位赋F
  DriverNameQuery_buttonStatus.value = Temp.valueOf()  //取回当前标志位信息（保证最多只有一个标志位为T）
  DriverNameQuery_buttonStatus.value = !DriverNameQuery_buttonStatus.value
// 当标志位为T时，背景颜色为绿色，为F时，背景颜色为蓝色，
  if (DriverNameQuery_buttonStatus.value) {
    DriverNameQuery_buttonStyle.value.backgroundColor = '#67c23a'
    cz.value = "查看全部"
  } else {
    DriverNameQuery_buttonStyle.value.backgroundColor = '#409eff'
    DriverName.value = ""
    cz.value = "按照名字查找司机"
  }
  RequestConditionEvaluator()
};


//（查询全部）
const GetPagingDataAndTotal = () => {

  axios.post("/api/GetAllDriverData", {

    pageSize: pageSize.value,
    pageNum: pageNum.value
  }, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/GetAllDriverData,{},{  ==>")
    console.log(res)
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


//获取（查询司机）
const DriverNameQuery = () => {

  axios.post("/api/DriverNameQuery", {
    DriverName: DriverName.value,
    pageSize: pageSize.value,
    pageNum: pageNum.value
  }, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/QueryAllEvaluationDataForToday,{},{  ==>")
    console.log(res.data.data)
    tableData.value = res.data.data.items
    Total.value = res.data.data.total
    DataFormatOptimization()
  })

}


//抽屉代码
import {ElMessageBox} from 'element-plus'

const drawer2 = ref(false)
const testInsertIntoDriverName = ref("")
const direction = ref('rtl')


function confirmClick() {
  ElMessageBox.confirm(`你确认提交了吗？ `)
      .then(() => {  //真正提交位置
        if (testInsertIntoDriverName.value === "") {
          console.log("插入的司机名不能为空")
          ElMessage.error("插入的司机名不能为空")
        } else {

          axios.post("/api/InsertNewDriver", {
                drivername: testInsertIntoDriverName.value
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
              ElMessage.success("操作成功")
              RequestConditionEvaluator()
            } else {
              ElMessage.error("操作失败")
            }
          });
        }

      })
      .catch(() => {
        console.log("   .catch(() => {   ")
        // catch error
      })
}


const dialogVisible1 = ref(false)

const handleClose1 = () => {
  dialogVisible1.value = false
/*  ElMessageBox.confirm('是否确定关闭此对话框?')
      .then(() => {
        dialogVisible1.value = false
        console.log("const handleClose1 = () => {      .then(() => {")
      })
      .catch(() => {
        dialogVisible1.value = false
        console.log("const handleClose1 = () => {          .catch(() => {")
        // catch error
      })*/
}
const delectdata = (row) => {
  console.log("const delectdata = (row) => {")
  console.log("row  =>")
  console.log(row)
  console.log("row.id  =>")
  console.log(row.id)
  dialogVisible1.value = false
}

const deleteopen = (row) => {
  console.log("row.id  =>")
  console.log(row.id)
  dialogVisible1.value = true
}

const open = (row) => {
  ElMessageBox.prompt('请输入修改后的司机名', '编辑信息', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    inputPattern:
        /[^\s]+/,
    inputErrorMessage: '请输入新的司机名',
    inputValue: row.drivername,
  })
      .then(({value}) => {
        //value ：新的司机名
        console.log("row.id  =>")
        console.log(row.id)
        //id+新的司机名 =》
        axios.post("/api/UPDATEDrivernameFindByID", {
              ID: row.id,
              DRIVERNAME: value
            }, {
              headers: {
                'Authorization': Tokenstore.token,
                "Content-Type": "application/x-www-form-urlencoded"
              }
            }
        ).then((res) => {
          console.log("打印出返回的json ==>  ")
          console.log(res)
          RequestConditionEvaluator()


        });

        ElMessage({
          type: 'success',
          message: `司机名已改为:${value}`,
        })

      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: '取消修改',
        })
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

.container1 {
  display: flex;
  align-items: center; /* 垂直居中 */
}
</style>




