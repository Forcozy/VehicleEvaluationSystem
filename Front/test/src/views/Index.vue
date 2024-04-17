<template>
  <!--  <h5>Index.vue</h5>  -->
  <div class="common-layout">

    <el-container>

      <!-- 侧边栏菜单 -->
      <el-aside width="auto" >
        <nav-menu :collpase="state.isCollapse"/>
      </el-aside>

      <el-container>
        <el-header>
          <nav-header v-model:collpase="state.isCollapse" />
        </el-header>

        <el-main>

          <router-view v-slot="{ Component }">
            <transition  name="el-fade-in-linear">
              <component :is="Component" style="height:100%" />
            </transition>
          </router-view>

        </el-main>
      </el-container>

    </el-container>
  </div>
</template>





<script setup lang='ts'>
import {ref, reactive } from 'vue'
import NavMenu from '../components/IndexView/index/navMenu.vue'
import NavHeader from '@/components/IndexView/index/navHeader.vue'

const state = reactive({
  isCollapse: false // 控制折叠与展开
})

</script>



<style scoped lang="scss">
.common-layout {
  width: 100%;
  height: 100%;
  .el-container {
    height: 100%;
    // 就是不折叠的时候宽度是200px，折叠的时候宽度自适应
  }
}
svg {
  width: 1em;
  height: 1em;
  margin-right: 5px;
}
</style>
