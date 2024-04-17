<!--
<template>
  <el-input v-model="input_name" style="width: 240px" placeholder="请输入账号"/>
  <br/>
  <el-input v-model="input_password" style="width: 240px" placeholder="请输入密码"/>
  <br/>
  <el-button type="primary" v-on:click="Login">登录</el-button>
  <h3>Tokenstore.token = {{Tokenstore.token}}</h3>

</template>


<script setup>
import {ref} from "vue";

import router from "@/router";
import {post} from "@/net/index.js";
import {ElMessage} from "element-plus";
//import {useStore} from "@/stores/user";
import {useTokenStore} from '@/stores/token.js'
import axios from "axios";
const Tokenstore = useTokenStore()

//const use_Store = useStore()
const input_name = ref("")
const input_password = ref("")
const state1 = ref("")

const Login = () => {
  console.log("x")
  console.log("input_name  =>")
  console.log(input_name.value)
  console.log("input_password  =>")
  console.log(input_password.value)


  console.log("用封装的post  ==post  ==>")
  post("/api/Login", {
    username: input_name.value,
    password1: input_password.value
  }, (message) => {
    console.log("message =")
    console.log(message)
    ElMessage.success("登录成功")
    Tokenstore.setToken(message)

    //token获取username
    axios.post("/api/tokenGetUserName", {
      token: Tokenstore.token,
    }, {
      headers: {
        'Authorization': Tokenstore.token,
        "Content-Type": "application/x-www-form-urlencoded"
      },
    }).then((res) => {
      console.log("axios.post(\"/api/tokenGetUserName\", {  ==>")
      console.log(res.data)
      Tokenstore.setUsername(res.data)
    })





    router.push('/home/')
  }, (message) => {

    console.log("message =", message)
    ElMessage.error(message)
  })

}

</script>



-->


<!--

<template>
  <div id="login">
    <div id="login-form" >
      <h1>车辆评价系统   </h1>
      <label for="username"><i class="el-icon-user-solid" style="color: #c1c1c1"></i></label>
      <input type="text" placeholder="用户名" name="username" id="username" autocapitalize="off" v-model.trim=input_name aria-autocomplete="off">
      <label for="password"><i class="el-icon-right" style="color: #c1c1c1"></i></label>
      <input type="password" placeholder="密码" name="password" id="password" autocapitalize="off" v-model.trim="input_password">
      <div>
        <el-button type="primary" v-on:click="Login">登录</el-button>
&lt;!&ndash;        <el-button type="info"  @click="open2" v-on:click="resetInfo">重置</el-button>&ndash;&gt;
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";

const input_password = ref()
const input_name = ref()
const resetInfo = () => {

}
const Login = () => {

}
const open2 = () => {

}

</script>

<style lang="scss" scoped>
#login {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  position: relative;
  background-image: url("@/assets/img/bj2.jpg");
  background-repeat: no-repeat;
  background-attachment: fixed;
  background-position: center;
  background-size: cover;
}

#login-form {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 50vw;
  min-width: 300px;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  background-color: rgba(0, 0, 0, 0.7);
  border-radius: 15px;
  box-shadow: 0 15px 25px rgba(0, 0, 0, .5);

  h1 {
    width: 60%;
    margin: 50px auto 20px;
    color: #c1c1c1;
    text-align: center;
  }

  input {
    width: 60%;
    margin: 15px auto;
    outline: none;
    border: none;
    padding: 10px;
    border-bottom: 1px solid #fff;
    background: transparent;
    color: white;
  }

  label {
    width: 60%;
    margin: 0 auto;
    position: relative;
    top: 30px;
    left: -15px;
  }

  div {
    width: 60%;
    margin: 10px auto;
    display: flex;
    justify-content: center;
    align-content: center;
  }

  button {
    // rgba
    background-color: rgba(9, 108, 144, 0.5);
    margin: 10px 25px 40px 25px;
  }

  p {
    width: 60%;
    margin: 8px auto;
    position: relative;
    left: -15px;
    color: #ff0000;
    font-size: 8px;
  }
}

input {
  -webkit-text-fill-color: #ffffff !important;
  transition: background-color 5000s ease-in-out ,width 1s ease-out!important;
}
</style>


-->



<template>
  <div class="container">
    <div class="login-wrapper">
      <div class="header">车辆评价系统后台</div>
<!--
      <div class="form-wrapper">
        <input type="text" name="username" placeholder="username" class="input-item" v-model="input_name">
        <input type="password" name="password" placeholder="password" class="input-item" v-model="input_password">
        <div class="btn" v-on:click="Login">Login</div>
      </div>
-->

      <div class="form-wrapper">
        <input type="text" name="username" placeholder="请输入用户名" class="input-item" v-model="input_name"/>
        <input type="password" name="password" placeholder="请输入密码" class="input-item" v-model="input_password"/>
      </div>
      <button  class="btn" style=" border: none; color: #3198fa ;font-size: 16px; /* 将字体大小设置为24像素 */"  v-on:click="Login"> 登陆</button>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";

import router from "@/router";
import {post} from "@/net/index.js";
import {ElMessage} from "element-plus";
//import {useStore} from "@/stores/user";
import {useTokenStore} from '@/stores/token.js'
import axios from "axios";
const Tokenstore = useTokenStore()

//const use_Store = useStore()
const input_name = ref("")
const input_password = ref("")
const state1 = ref("")

const Login = () => {
  console.log("x")
  console.log("input_name  =>")
  console.log(input_name.value)
  console.log("input_password  =>")
  console.log(input_password.value)


  console.log("用封装的post  ==post  ==>")
  post("/api/Login", {
    username: input_name.value,
    password1: input_password.value
  }, (message) => {
    console.log("message =")
    console.log(message)
    ElMessage.success("登录成功")
    Tokenstore.setToken(message)

    //token获取username
    axios.post("/api/tokenGetUserName", {
      token: Tokenstore.token,
    }, {
      headers: {
        'Authorization': Tokenstore.token,
        "Content-Type": "application/x-www-form-urlencoded"
      },
    }).then((res) => {
      console.log("axios.post(\"/api/tokenGetUserName\", {  ==>")
      console.log(res.data)
      Tokenstore.setUsername(res.data)
    })





    router.push('/home/')
  }, (message) => {

    console.log("message =", message)
    ElMessage.error(message)
  })

}


</script>

<style scoped>

html {
  height: 100%;
}
body {
  height: 100%;
}
.container {
  /* margin-top: 5%; */
  height: 980px;
  width: 100%;
  background-image: linear-gradient(to right, #ebf5f8, #a6c1ee);
}
.login-wrapper {
  background-color: #fff;
  width: 358px;
  height: 588px;
  border-radius: 15px;
  padding: 0 50px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.header {
  font-size: 38px;
  font-weight: bold;
  text-align: center;
  line-height: 200px;
}
.input-item {
  display: block;
  width: 100%;
  margin-bottom: 20px;
  border: 0;
  padding: 10px;
  border-bottom: 1px solid rgb(128, 125, 125);
  font-size: 15px;
  outline: none;
}

.input-item::placeholder {
  text-transform: uppercase;
}
.btn {
  text-align: center;
  padding: 10px;
  margin: 0 auto;
  width: 100%;
  margin-top: 40px;
  background-image: linear-gradient(to right, #a6c1ee, #ebf5f8);
  color: #fff;
}
.msg {
  text-align: center;
  line-height: 88px;
}
a {
  text-decoration-line: none;
  color: #abc1ee;
}

</style>
