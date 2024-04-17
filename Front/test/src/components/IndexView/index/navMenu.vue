<template>
<!-- 主页侧边菜单栏组件 -->
  <el-menu
    class="el-menu-vertical-demo"
    :collapse="props.collpase"
    router="router"
    active-text-color="#4e06fb"
    background-color="#e8f0fe"
  >
<!-- background-color:  e8f0fe（淡蓝色）   94e2ff（深蓝色）  e8ebff（淡紫色） cee581（明亮绿）-->

    <el-menu-item index="/home/">
      <el-icon><House /></el-icon>
      <template #title>主页</template>
    </el-menu-item>

<!--
    <el-menu-item index="/home/Map">
      <el-icon><Menu /></el-icon>
      <template #title>地图</template>
    </el-menu-item>
-->

    <el-sub-menu >
      <template #title>     <!--  一级菜单表头   -->
        <el-icon><Histogram /></el-icon>
        <span>数据可视化</span>
      </template>


<!--      <el-menu-item index="/home/BSP">建筑结构 + 户型分布</el-menu-item>-->
      <el-menu-item index="/home/EvaluationInformationTablePage">评价信息</el-menu-item>
      <el-menu-item index="/home/EvaluationRankingTablePage">评价排名</el-menu-item>
      <el-menu-item index="/home/DriverManagementTablePage">司机管理</el-menu-item>



      <el-menu-item @click="$router.push({ name: 'Cockpit' })">驾驶舱</el-menu-item>








    </el-sub-menu>

  </el-menu>

</template>

<script setup lang='ts'>
import {defineProps} from 'vue'
import {Histogram} from "@element-plus/icons-vue";
const props = defineProps<{
  collpase: boolean
}>()
</script>


<style scoped lang="scss">
  svg {
    width: 1em;
    height: 1em;
    margin-right: 5px;
  }
  // 记得要有这个，控制侧边菜单宽度，意思是折叠的时候，宽度自适应，不着折叠的时候宽度为200px
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
  }
  .el-menu {
    height: 100%;
  }
</style>
