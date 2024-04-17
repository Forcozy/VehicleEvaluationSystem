<template>
  <div>
    <h3 style="color: #45d6f9 ; font-size: 66px; /* 设置段落文本的字体大小为16像素 */">{{ currentTime  }}</h3>
  </div>

</template>

<script setup>

// 创建一个响应式引用来存储当前时间
import {onMounted, onUnmounted, ref} from "vue";

const currentTime = ref('');

// 更新时间的函数
const updateTime = () => {
  const now = new Date();
  // 格式化时间，例如："14:30:00"
  const formattedTime = now.toLocaleTimeString('zh-CN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: false // 24小时制
  });
  currentTime.value = formattedTime;
};

// 组件挂载时，开始更新时间
onMounted(() => {
  // 立即更新时间
  updateTime();
  // 设置定时器，每秒更新时间
  const timerId = setInterval(updateTime, 1000);
  // 组件卸载时清除定时器
  onUnmounted(() => clearInterval(timerId));
});


</script>

<style scoped>

</style>
