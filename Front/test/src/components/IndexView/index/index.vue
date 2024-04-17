<template>
  <h4>index.vue</h4>
  <h3>查看当前的Token => {{ Tokenstore.token }}</h3>
  <h3>若有值为{{ id }},路由上的值为{{ $route.params.id }}</h3>
  <button @click="testtokenpost">测试当前的token是否有效</button>
  <button @click="wjtoken">删除客户端保存的Token</button>
  <button @click="dowmtest">下载文件</button>


  <el-input v-model="testtoken" style="width: 240px" placeholder="请输入密码"/>


  <h3>分割线---函数测试</h3>
  <button @click="testselectdirvername">测试查询司机总数量 dirvernametotal =={{ dirvernametotal }}</button>
  <button @click="testselectevaluation">测试查询评价信息总数量 evalutiontotal =={{ evalutiontotal }}</button>
  <button @click="$router.push({ name: 'Cockpit' })">跳转到 Cockpit 页面</button>

  <!--  <EchartsCalendarChart/>-->
  <!--
    <el-dropdown @command="handleCommand">
      <span class="el-dropdown-link">
        Dropdown List     <el-icon class="el-icon&#45;&#45;right"><arrow-down /></el-icon>
      </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="退出登录">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  -->
  <el-config-provider :locale="locale">
    <el-date-picker
        v-model="value2"
        type="datetimerange"
        :shortcuts="shortcuts"
        range-separator="到"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
    />

    <el-button type="primary" @click="timeget">发送时间给后端</el-button>
    <br/>


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


  </el-config-provider>
</template>


<script setup>

import {ref, onMounted, watchEffect, computed} from "vue";
import axios from "axios";
import router from "@/router/index.js";
import {ElMessage} from "element-plus";

import {useTokenStore} from '@/stores/token.js'
import {useRoute} from "vue-router";

const Tokenstore = useTokenStore()

import zhCn from 'element-plus/dist/locale/zh-cn.mjs'

const language = ref('zh-cn')  //设置语言，默认中文
const locale = computed(() => (language.value === 'zh-cn' ? zhCn : en))

const value2 = ref('')
const shortcuts = [
  {
    text: '近7天',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setDate(start.getDate() - 7)
      return [start, end]
    },
  },
  {
    text: '近一个月',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setMonth(start.getMonth() - 1)
      return [start, end]
    },
  },
  {
    text: '近3个月',
    value: () => {
      const end = new Date()
      const start = new Date()
      start.setMonth(start.getMonth() - 3)
      return [start, end]
    },
  },
  {
    text: '当天',
    value: () => {
      const now = new Date();
      const start = new Date(now.getFullYear(), now.getMonth(), now.getDate());
      const end = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1);
      return [start, end];
    },
  },
]

function formatDate(date) {
  const d = new Date(date);
  const year = d.getFullYear();
  const month = String(d.getMonth() + 1).padStart(2, '0');  // 月份从0开始，所以要加1
  const day = String(d.getDate()).padStart(2, '0');
  const hours = String(d.getHours()).padStart(2, '0');
  const minutes = String(d.getMinutes()).padStart(2, '0');
  const seconds = String(d.getSeconds()).padStart(2, '0');

  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}

const timeget = () => {

  if (value2.value === '') { //都没填
    console.log("都没填情况下不允许查询")
    ElMessage.error("请填写查询时间")
  } else {


    const date01 = formatDate(value2.value[0]);

    const date02 = formatDate(value2.value[1]);

    axios.post("/api/daterange", {
      startDate: date01,
      endDate: date02,
      /*   /!* pageNum: pageNum.value*!/*/
    }, {
      headers: {
        'Authorization': Tokenstore.token,
        "Content-Type": "application/x-www-form-urlencoded"
      },
    }).then((res) => {
      console.log("axios.post(/api/GetAllDriverData,{},{  ==>")
      console.log(res)
    })

  }
}


watchEffect(() => {
  console.log("value2.value ==>")
  console.log(value2.value)
  console.log(typeof value2.value)
  console.log(typeof value2)
  console.log("value2.value[0] ==>")
  console.log(value2.value[0])
  console.log("value2.value[1] ==>")
  console.log(value2.value[1])
})


const id = ref(null)
const route = useRoute();


const testtoken = ref("")

const dirvernametotal = ref(0)//存储 获取司机表的总数
const evalutiontotal = ref(0)//存储 获取评价表的总数


const handleCommand = (command) => {
  ElMessage(`click on item ${command}`)
  if (command === "a") {
    ElMessage.success(`click on item ${command}`)
  }
}


const dowmtest = () => {
  axios.get('/api/file/download/',
      {
        headers: {
          'Authorization': Tokenstore.token,
        }, responseType: 'blob'
      }
  ).then((res) => {
    console.log('文件下载成功');
    const blob = new Blob([res.data]);
    const fileName = "二维码.zip";

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
  });
}


function mounted() {   //驾驶舱返回这里时会携带参数，有参数就刷新成无参的（无用，已废弃）
  if (route.params.id != "") {
    id.value = route.params.id;
    console.log('\"\"接收到的参数ID:', id.value);
    document.body.style.backgroundColor = 'red';

    console.log("document.body =")
    console.log(document.body)

    router.push("/home/")
    //router.push({name:'司机记录表',params: {id: row.name}})
    console.log(typeof id.value);
  }
}


onMounted(() => {
  console.log("当前的环境是：")
  console.log(import.meta.env.VITE_ENV)
  console.log("当前的后端访问URL是：")
  console.log(import.meta.env.VITE_APP_BASEURL)

  //mounted()
  /* console.log("document.body.style =")
   console.log(document.body.style)
 */
})

const testselectdirvername = () => {  //获取司机表的总数
  axios.post("/api/GetAlldrivernameNum", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("打印出返回的json ==>  ")
    console.log(res)
    dirvernametotal.value = res.data.data
  });
}

const testselectevaluation = () => {  //获取评价表的总数
  axios.post("/api/GetAllEvaluationNum", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("打印出返回的json ==>  ")
    console.log(res)
    evalutiontotal.value = res.data.data
  });
}


const testtokenpost = () => {  //测试当前的token是否有效（保留）
  axios.post("/api/VerifyToken", {
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
      ElMessage.error("登录信息已失效，请重新登录")
      router.push('/Login/welcome_login')
      console.log("token无效") //0成功1失败
    }
  });
}

const wjtoken = () => {  //销毁token
  Tokenstore.removeToken()
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
            if (res.data.code === 0){
              ElMessage.success("操作成功")
            }else {
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


const open = () => {
  ElMessageBox.prompt('Please input your e-mail', 'Tip', {
    confirmButtonText: 'OK',
    cancelButtonText: 'Cancel',
    inputPattern:
     /[^\s]+/,
    inputErrorMessage: 'Invalid Email',
  })
      .then(({ value }) => {
        ElMessage({
          type: 'success',
          message: `Your email is:${value}`,
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: 'Input canceled',
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

</style>



