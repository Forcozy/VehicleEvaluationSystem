<template>
  <!-- 折线图 -->
  <div>
    <div id="myChart03" style="width: 100%; height: 400px"></div>
  </div>
</template>

<script setup>
import {reactive, ref, onMounted} from "vue";
import * as echarts from "echarts";
import axios from "axios";

import {useTokenStore} from '@/stores/token.js'

const Tokenstore = useTokenStore()




const datatime = ref()
const datavalue = ref()


const state = reactive({

  option: {
    title: {
      text: '评价记录每月提交情况', // 标题文本内容
      left: 'center', // 标题水平居中
      top: 'top', // 标题位于容器的顶部
      textStyle: { // 标题文本样式
        color: '#2693e0', // 字体颜色为白色
        fontSize: 18, // 字体大小为18px
        fontWeight: 'bold' // 字体粗细为加粗
      },
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross'
      }
    },
    toolbox: {
      show: true,
      feature: {
        saveAsImage: {}
      }
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: datatime
    },
    yAxis: {
      type: 'value',
      axisLabel: {
        formatter: '{value} 条'
      },
      axisPointer: {
        snap: true
      }
    },
    visualMap: {
      show: false,
      dimension: 0,
      pieces: [
        {
          lte: 6,
          color: 'green'
        },
        {
          gt: 6,
          lte: 19,
          color: 'green'
        },
        {
          gt: 19,
          color: 'green'
        },
      ]
    },
    series: [
      {
        name: '当月条数',
        type: 'line',
        smooth: true,
        // prettier-ignore
        data: datavalue,
        markArea: {
          itemStyle: {
            color: 'rgba(255, 173, 177, 0.4)'
          },
        }
      }
    ]
  },


});

const initeCharts = () => {
  let myChart = echarts.init(document.getElementById("myChart03"));
  // 绘制图表
  myChart.setOption(state.option);
};


onMounted(() => {

  axios.post("/api/MonthlySubmissionTimes", {}, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(/api/MonthlySubmissionTimes,{},{  ==>")
    console.log(res)
    console.log(res.data.data)
    const resdata = res.data.data;

    console.log("1拿到的后端返回数据resdata ==>")
    console.log(resdata)

    // 使用map函数从对象数组中提取name和value到两个新数组
    datatime.value = resdata.map(item => item.name);
    datavalue.value = resdata.map(item => item.value);

    console.log("datatime =>");
    console.log(datatime);

    console.log("datavalue =>");
    console.log(datavalue);

    initeCharts();    //生成图表函数放到post成功内，在没数据时就不会显示图表


    /*  const datatime = ref([])
        const datavalue = ref([])
     */
/*

    // const datatime1 = resdata.value.map(item => item.name);
    const datatime1 = ref(resdata.value.map(item => item.name));
    // const datavalue1 = resdata.value.map(item => item.value);
    const datavalue1 = ref(resdata.value.map(item => item.value));

    console.log("datatime1 =>"); // 输出: ["不及格", "及格", "优秀", "良好"]
    console.log(datatime1); // 输出: ["不及格", "及格", "优秀", "良好"]



    console.log("datatime 存放前，里面是空的，是[]只有这个的不是null的=>");
    console.log(datatime);

    datatime.value = datatime1.value
    datavalue.value = datavalue1.value

    console.log("datatime 存放后，里面不应该是空的 =>");
    console.log(datatime);


    console.log("datavalue1 =>"); // 输出: [81, 74, 69, 79]
    console.log(datavalue1); // 输出: [81, 74, 69, 79]

*/

  })


});


</script>

<style lang="scss" scoped></style>

