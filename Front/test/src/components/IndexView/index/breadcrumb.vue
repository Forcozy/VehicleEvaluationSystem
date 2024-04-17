<!--
<template>
  <el-breadcrumb separator="/">&lt;!&ndash; 分隔符 &ndash;&gt;
    <el-breadcrumb-item v-for="(item,index) in breadcrumbList" :key="index">
&lt;!&ndash; 遍历 breadcrumbList 数组，生成面包屑的每一项。 &ndash;&gt;

      <span class="no-redirect" v-if="index===breadcrumbList.length-1">
&lt;!&ndash; 判断当前是否是最后一个面包屑项 &ndash;&gt;
           {{ item.name }}
      </span>
      <span class="redirect" v-else @click="handleRedirect(item.path)">
           {{ item.name }}
      </span>

    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import {useRoute, useRouter} from "vue-router";
import {ref, watch} from "vue";

const route = useRoute()
const router = useRouter()


console.log("route.matched =>")
console.log(route.matched)


const breadcrumbList = ref([])  //存储面包屑数据

const initBreadcrumbList = () => {
  breadcrumbList.value = route.matched //route.matched（当前路由匹配到的所有嵌套路径段）
  console.log("breadcrumb_item.value=")
  console.log(breadcrumbList.value)
}


const handleRedirect = (path) => {
  router.push(path)
}


watch(route, () => {
  console.log("route.matched =>")
  console.log(route.matched)
  initBreadcrumbList()
}, {deep: true, immediate: true})  //immediate:是否刚开始就执行


</script>
-->

<template>
  <el-breadcrumb>
    <el-breadcrumb-item
        v-for="(item, index) in breadcrumbList"
        :key="index"
        :separator="index < breadcrumbList.length - 1 ? '/' : ''"
    >
      <span class="redirect" @click="handleRedirect(item.path)">
        {{ item.name }}
      </span>
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';
import { ref, watch } from 'vue';

const route = useRoute();
const router = useRouter();

const breadcrumbList = ref([]);

const initBreadcrumbList = () => {
  breadcrumbList.value = route.matched;
};

const handleRedirect = (path) => {
  router.push(path);
};

watch(route, () => {
  initBreadcrumbList();
}, { deep: true, immediate: true });
</script>

<style scoped>
/* 在这里添加你的样式 */
</style>








<style scoped>
.no-redirect {
  color: #97a8be;
  cursor: text;
}

.redirect {
  color: #666;
  font-weight: 600;
  cursor: pointer;

}
</style>
