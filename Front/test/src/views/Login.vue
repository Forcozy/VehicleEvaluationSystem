<template>
<!--  <h5>login.vue</h5>-->
  <router-view v-slot="{ Component }">
    <transition  name="el-fade-in-linear">
      <component :is="Component" style="height:100%" />
    </transition>
  </router-view>

</template>


<script setup>

</script>


<style scoped>

</style>
