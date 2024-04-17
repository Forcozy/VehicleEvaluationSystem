<template>
<!-- 评价信息1占比图 -->
  <div>
    <div id="myChart01" style="width: 100%; height: 400px"></div>
  </div>
</template>

<script setup>
import {reactive, ref, onMounted} from "vue";
import * as echarts from "echarts";
import axios from "axios";

import {useTokenStore} from '@/stores/token.js'
const Tokenstore = useTokenStore()

const resdata = ref()

const state = reactive({
  option: {   //echarts复制过来的
    title: {
      text: '评价信息1占比图', // 标题文本内容
      left: 'center', // 标题水平居中
      top: 'top', // 标题位于容器的顶部
      textStyle: { // 标题文本样式
        color: '#2693e0', // 字体颜色为白色
        fontSize: 18, // 字体大小为18px
        fontWeight: 'bold' // 字体粗细为加粗
      },
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '5%',
      left: 'center',
      textStyle: {
        color: '#ccc'
      }
    },
    series: [
      {
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 40,
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: resdata
      }
    ]
  },
});

const initeCharts = () => {
  let myChart = echarts.init(document.getElementById("myChart01"));
  // 绘制图表
  myChart.setOption(state.option);
};



onMounted(() => {

  axios.post("/api/GetAllNumberOfOccurrencesOfEvaluation1", {}, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/GetAllNumberOfOccurrencesOfEvaluation1,{},{  ==>")
    console.log(res)
    console.log(res.data.data)
    resdata.value = res.data.data;
    initeCharts();    //生成图表函数放到post成功内，在没数据时就不会显示图表
  })


});


</script>

<style lang="scss" scoped></style>

