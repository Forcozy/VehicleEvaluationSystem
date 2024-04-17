<template>
  <!-- 主页头组件 -->
  <div class="navbar">

    <!-- 汉堡伸缩按钮 -->
    <div class="collapse" @click="handleCollapse">
      <el-icon>
        <expand v-if="props.collpase"></expand>
        <fold v-else></fold>
      </el-icon>
    </div>

    <breadcrumb/>

    <!--  <EchartsCalendarChart/>-->
    <el-dropdown @command="handleCommand" class="navbar-right">
    <span class="el-dropdown-link">
      {{ Tokenstore.Username}}    <el-icon class="el-icon--right"><arrow-down/></el-icon>
    </span>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="退出登录">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>


  </div>
</template>

<script setup lang='ts'>
import {defineProps, onMounted} from 'vue'
import Breadcrumb from "@/components/IndexView/index/breadcrumb.vue";
import {ElMessage} from "element-plus";
import {post1} from "@/net";

const props = defineProps<{
  collpase: boolean
}>()

const emits = defineEmits<{
  // 这样写，父组件通过v-model传值进来，父组件那边就不用在定义事件改变这里传过去的值了
  // update:collpase 就会自动改变v-model传过来的值了
  (e: 'update:collpase', value: boolean): void
}>()


const handleCollapse = () => {
  emits('update:collpase', !props.collpase)
}

import router from "@/router/index.js";
const handleCommand = (command) => {
 // ElMessage(`正在${command}`)
  if (command === "退出登录") {
    Tokenstore.removeToken()
    router.push("/Login/welcome_login")
    ElMessage.success(`${command}成功`)
  }
}


import {useTokenStore} from '@/stores/token.js'

const Tokenstore = useTokenStore()

/*

const testselectdirvername = () => {  //获取司机表的总数

  post1('/api/index/tokenGetUserName',{
        token: "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsidXNlcm5hbWUiOiJhZG1pbiJ9LCJleHAiOjE3MTMxNTI0NjN9.xwKUiMzY9WhcUDAU0MBr-qLA6VGbudUzG1iVXwUho3E"
      },
      (message) => {
        ElMessage.success(message);
      }
  );

  /!* axios.post("/api/GetAlldrivernameNum", {}, {
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
   *!/


}


onMounted(()=>{
  testselectdirvername()
})
*/




</script>
<style scoped lang="scss">
.collapse {
  height: 100%;
  width: 2em;
  display: flex;
  align-items: center;

  svg {
    width: 2em;
    height: 2em;
  }
}

.navbar {
  width: 100%;
  height: 60px;
  overflow: hidden;
  background-color: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  padding: 0 16px;
  display: flex;
  align-items: center;
  box-sizing: border-box;
  position: relative;

  .navbar-right {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: flex-end;

    ::v-deep .navbar-item {
      display: inline-block;
      margin-left: 18px;
      font-size: 22px;
      color: #5a5e66;
      box-sizing: border-box;
      cursor: pointer;
    }
  }
}
</style>
