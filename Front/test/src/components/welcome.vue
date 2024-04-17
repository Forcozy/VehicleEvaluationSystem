<template>
<h3>wellllllllllllll</h3>
  <button @click="$router.push({ name: '主页' })">跳转到 主页 页面</button>
  <br>
  <h4>poststatus  ==> {{poststatus}}   //结尾</h4>
  <br>
  <el-button @click="x('hello')">hello点我</el-button>
  <el-button @click="x('hello1')">hello1点我</el-button>
  <el-button @click="x('hello2')">hello2点我</el-button>
  <el-button @click="x('hello3')">hello3点我</el-button>
  <el-button @click="x('hello9')">hello9点我</el-button>
</template>

<script setup>
import {ref} from "vue";

const poststatus = ref("")
const x = (xxx) => {
  console.log("x函数拿到的参数：");
  console.log(xxx);
  if (poststatus.value==xxx){    //相同就取消特殊查询，改全部查询
    poststatus.value =''
    console.log(" if (poststatus.value==xxx){")
  }else{         //不相同就执行新的查询
    console.log(" if (poststatus.value!=xxx){")
    poststatus.value =xxx
  }



}


</script>





<style scoped>

</style>
