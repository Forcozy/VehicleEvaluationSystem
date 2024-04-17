<template>
  <!-- 评价信息1占比图 -->
  <div>
    <div id="myChart05" style="width: 100%; height: 400px"></div>
  </div>
</template>

<script setup>
import {reactive, ref, onMounted} from "vue";
import * as echarts from "echarts";
import axios from "axios";

import {useTokenStore} from '@/stores/token.js'
const Tokenstore = useTokenStore()

/*const resdata = ref()*/

const datatimeBar = ref()  //时间x数值y
const datavalueBar = ref()



const state = reactive({
/*  option: {   //echarts复制过来的
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
  },*/

//测试代码
/*  option : {
    xAxis: {
      type: 'category',
      data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        data: [120, 200, 150, 80, 70, 110, 130],
        type: 'bar',
        showBackground: true,
        backgroundStyle: {
          color: 'rgba(180, 180, 180, 0.2)'
        }
      }
    ]
  },*/


  option : {
    title: {
      text: '近七天提交情况图', // 标题文本内容
      left: 'center', // 标题水平居中
      top: 'top', // 标题位于容器的顶部
      textStyle: { // 标题文本样式
        color: '#2693e0', // 字体颜色为白色
        fontSize: 18, // 字体大小为18px
        fontWeight: 'bold' // 字体粗细为加粗
      },
    },
    tooltip: {
      trigger: 'axis', // 触发类型，'axis' 表示触发方式为坐标轴触发
      axisPointer: {
        type: 'shadow' // 指示器类型，'shadow' 表示阴影指示器
      },
      formatter: '{b} : {c}' // 格式化提示框显示的内容
    },
    xAxis: {
      type: 'category',
      data: datatimeBar
    },
    yAxis: {
      type: 'value',
      axisLabel: {
        formatter: '{value} 条'
      },
    },
    series: [
      {
        data: datavalueBar,
        type: 'bar',
        showBackground: true,
        backgroundStyle: {
          color: 'rgba(180, 180, 180, 0.2)'
        },
        itemStyle: {
          color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
            { offset: 0, color: '#83bff6' },
            { offset: 0.5, color: '#188df0' },
            { offset: 1, color: '#188df0' }
          ])
        },
        emphasis: {
          itemStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: '#2378f7' },
              { offset: 0.7, color: '#2378f7' },
              { offset: 1, color: '#83bff6' }
            ])
          }
        },

      }
    ]
  },

});

const initeCharts = () => {
  let myChart = echarts.init(document.getElementById("myChart05"));
  // 绘制图表
  myChart.setOption(state.option);
};



onMounted(() => {

  axios.post("/api/TotalDataInThePastWeek", {}, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/TotalDataInThePastWeek,{},{  ==>")
    console.log(res)
    console.log(res.data.data)
    //resdata.value = res.data.data;

    const resdata = res.data.data;

    // 使用map函数从对象数组中提取name和value到两个新数组
    datatimeBar.value = resdata.map(item => item.name);
    datavalueBar.value = resdata.map(item => item.value);






    initeCharts();    //生成图表函数放到post成功内，在没数据时就不会显示图表
  })


});


</script>

<style lang="scss" scoped></style>

