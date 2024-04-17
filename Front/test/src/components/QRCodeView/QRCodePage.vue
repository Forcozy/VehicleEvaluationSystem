<template>
  <h3>这里是二维码页面</h3>
  <h3>当前正在对司机: &nbsp; {{ DriverName }} &nbsp; 进行评价</h3><br/>

  <el-form :model="formData" :rules="rules">
    <table>

      <tr>
        <el-form-item label="评价1">
          <el-radio-group v-model="formData.radio1" size="large">
            <el-radio-button label="1"> 不及格</el-radio-button>
            <el-radio-button label="2"> 及格</el-radio-button>
            <el-radio-button label="3"> 良好</el-radio-button>
            <el-radio-button label="4"> 优秀</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </tr>

      <tr>
        <el-form-item label="评价2">
          <el-radio-group v-model="formData.radio2" size="large">
            <el-radio-button label="1"> 不及格</el-radio-button>          <!-- 标签内的文字是显示在按钮上面的，label：选中后存入到对应变量的值 -->
            <el-radio-button label="2"> 及格</el-radio-button>
            <el-radio-button label="3"> 良好</el-radio-button>
            <el-radio-button label="4"> 优秀</el-radio-button>
          </el-radio-group>
        </el-form-item>
      </tr>

      <tr>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="formData.phone"></el-input>
        </el-form-item>
      </tr>

      <tr>
        <el-form-item label="用车单位">
          <el-input v-model="ycdw"></el-input>
        </el-form-item>
      </tr>

      <tr>
        <el-button type="primary" @click="insert_evaluate">提交</el-button>
      </tr>

    </table>


    <h3> formData.radio1 = {{ formData.radio1 }}</h3>
    <h3> formData.radio2 = {{ formData.radio2 }}</h3>
    <h3> formData.phone = {{ formData.phone }}</h3>


  </el-form>


</template>


<script setup>
import {ref, reactive, onMounted} from "vue";
import axios from "axios";
import router from "@/router/index.js";
import {useRoute} from "vue-router";
import {post} from "@/net/index.js";
import {ElMessage} from "element-plus";

//表单内提交变量
const formData = reactive({
  radio1: "4",
  radio2: "4",
  phone: null
})

const ycdw = ref("")


//输入校验规则
const rules = ref({
  phone: [
    {required: true, message: '请输入手机号码', trigger: 'blur'},   //required需要：必填，没填则会显示错误信息 '请输入手机号码',trigger触发条件：blur失去焦点时触发
    {pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur'}
  ]
})


const DriverName = ref("") //司机名

let path = ref("")
const route = useRoute()
path.value = route.path  //拿到部分 url


//查看司机是否存储在内
const Check_if_the_driver_is_stored_in_it = () => {
  console.log("Check_if_the_driver_is_stored_in_it  检查URL给的司机的信息是否有存 => ")
  console.log("path = ", path)  //拿到的当前url
  const UUID = path.value.replace('/QRCode/', '');
  console.log("urlddaata = ", UUID)  //裁剪掉 拿到的当前url 的重复部分

  //拿到数据库司机名单比对，有值就有这个司机，准备填评价表，没有这个司机就跳转联系管理员添加该用户
  axios.post("/api/getDriverName", {UUID: UUID}, {
    headers: {  //
      "Content-Type": "application/x-www-form-urlencoded"
    }
  }).then((res) => {
    console.log("CompareWhetherThereIsThisDriver ==>  ")
    console.log("打印出返回的json ==>  ")
    console.log(res)
    console.log("res.data.message ==>  ")
    console.log(res.data.message)
    if (res.data.message == "查无此人") {  //查找失败，无该司机信息，跳转到联系界面
      router.push('/NoDataQRCode')
    }
    if (res.data.code == "0") {  //查找成功
      console.log("//查找成功")
      DriverName.value = res.data.message
      console.log("DriverName = ", DriverName.value)
    }
  });
}


onMounted(() => {
  Check_if_the_driver_is_stored_in_it()
})


const insert_evaluate = () => {
  console.log("path = ", path)
  console.log("formData.radio1 = ", formData.radio1)
  console.log("formData.radio2 = ", formData.radio2)
  console.log("formData.phone = ", formData.phone)

  console.log("path = ", path)  //拿到的当前url
  const urlddaata = path.value.replace('/QRCode/', '');
  console.log("urlddaata = ", urlddaata)  //裁剪掉 拿到的当前url 的重复部分


  console.log("testCross2 ====post ==>")



   axios.post("/api/QRCodeSubmitInformation", {
      uuid: urlddaata,
      evaluationindicator1: formData.radio1,  //键值对要用小写lombok问题
      evaluationindicator2: formData.radio2,
      contactnumberofevaluator: formData.phone
   }, {
      headers: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
    }).then((res) => {
      console.log("打印出返回的json ==>  ")
      console.log(res)

      if (res.data.message == "插入成功") {  //插入成功就跳转到感谢页面
        router.push('/QRCodeThanks')
      }

    });



}


</script>


<style>

</style>
