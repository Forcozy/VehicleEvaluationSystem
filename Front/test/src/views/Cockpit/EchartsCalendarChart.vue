<!--
<template>
  &lt;!&ndash; 评价信息1占比图 &ndash;&gt;
  <div>
    <div id="myChart08" style="width: 100%; height: 400px"></div>
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


    /*

    title: {
      text: '日历图示例',
      left: 'center'
    },
    tooltip: {
      formatter: function(params) {
        return params.value[0] + '：' + params.value[1];
      }
    },
    visualMap: {
      min: 0,
      max: 20,
      type: 'piecewise',
      left: 'center',
      bottom: 20,
      calculable: true,
      orient: 'horizontal',
      inRange: {
        color: ['#e0ffff', '#006edd']
      },
      pieces: [
        { min: 0, max: 5, label: 'Level 0' },
        { min: 6, max: 10, label: 'Level 1' },
        { min: 11, max: 15, label: 'Level 2' },
        { min: 16, max: 20, label: 'Level 3' }
      ],
      textStyle: {
        color: '#000'
      }
    },
    calendar: {
      top: 'middle',
      left: 'center',
      cellSize: [60, 60],
      yearLabel: {
        show: false
      },
      dayLabel: {
        margin: 20
      },
      monthLabel: {
        nameMap: 'cn'
      },
      range: ['2022-01', '2022-01']
    },
    series: [
      {
        type: 'heatmap',
        coordinateSystem: 'calendar',
        data: data.map(item => [item.name, item.value])
      }
    ]
*/
    /*
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
    ]*/


    /*  xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [150, 230, 224, 218, 135, 147, 260],
          type: 'line'
        }
      ]*/

    /*
        calendar: [
          {
            range: '2022-01',
            startDayOfWeek: 1, // 以周几作为每周的开始，0:周日，1:周一，以此类推
            items: data
          }
        ],
        series: [
          {
            type: 'calendar',
            calendarIndex: 0,
            itemStyle: {
              normal: {
                color: '#f3f',
                borderWidth: 1,
                borderColor: '#333'
              }
            }
          }
        ]
    */


    /*   tooltip: {
        position: 'top'
      },
      visualMap: {
        min: 0,
        max: 1000,
        inRange: {
          color: ['#e0ffff', '#006edd']
        },
        orient: 'horizontal',
        left: 'center',
        bottom: 20
      },
      calendar: {
        orient: 'vertical',
        yearLabel: {
          margin: 40
        },
        dayLabel: {
          firstDay: 1,
          nameMap: 'cn',
         /!* nameMap: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
        *!/  color: '#ffffff' // y轴字体颜色为白色
        },
        monthLabel: {
          nameMap: 'cn',
          margin: 20,
          color: '#ffffff' // y轴字体颜色为白色
        },
        cellSize: 40,
        range: '2024-04'
      },
      series: {
        type: 'heatmap',
        coordinateSystem: 'calendar',
        data: getVirtualData('2024', '04')
      }
      */
    /*

        tooltip: {
          position: 'top'
        },
        visualMap: {
          min: 0,
          max: 1000,
          inRange: {
            color: ['#e0ffff', '#006edd']
          },
          orient: 'horizontal',
          left: 'center',
          bottom: 20
        },
        calendar: {
          orient: 'vertical',
          yearLabel: {
            margin: 40
          },
          dayLabel: {
            firstDay: 1,
            nameMap: 'cn', // 将星期显示为中文
            color: '#ffffff' // x轴字体颜色为白色
          },
          monthLabel: {
            nameMap: 'cn', // 将月份显示为中文
            margin: 20,
            color: '#ffffff' // y轴字体颜色为白色
          },
          cellSize: 40,
          range: '2024-04'
        },
        series: {
          type: 'heatmap',
          coordinateSystem: 'calendar',
          data: getVirtualData('2024', '04')
        }

    */

    title: {
      text: '上月评价数日历图', // 标题文本内容
      left: 'center', // 标题水平居中
      top: 'top', // 标题位于容器的顶部
      textStyle: { // 标题文本样式
        color: '#2693e0', // 字体颜色为白色
        fontSize: 18, // 字体大小为18px
        fontWeight: 'bold' // 字体粗细为加粗
      },
    },
    visualMap: {
      min: 0,
      max: 1000,
      inRange: {
        color: ['#e0ffff', '#006edd']
      },
      orient: 'horizontal',
      left: 'center',
      bottom: 20,
      textStyle: { // 视觉映射组件文本样式
        color: '#ffffff' // 字体颜色设置为白色
      }
    },
    calendar: {
      orient: 'vertical',
      yearLabel: {
        show: false, // 隐藏年份标签
        margin: 40
      },
      dayLabel: {
        firstDay: 1,
        nameMap: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'], // 将星期显示为中文
        color: '#ffffff' // x轴字体颜色为白色
      },
      monthLabel: {
        nameMap: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'], // 将月份显示为中文
        margin: 20,
        color: '#ffffff' // y轴字体颜色为白色
      },
      cellSize: 40,
      range: getPreviousMonthFormattedDate()
    },
    series: {
      type: 'heatmap',
      coordinateSystem: 'calendar',
      data: getVirtualData(2024,4)
      /*[
          ['2024-03-01', 12],
          ['2024-03-02', 20],
          ['2024-03-03', 11],
          ['2024-03-04', 12],
          ['2024-03-05', 16],
       ['2024-03-06', 16],
       ['2024-03-07', 15],
       ['2024-03-08', 10],
       ['2024-03-09', 16],
       ['2024-03-10', 13]









      ]*/
      //getVirtualData2 //getVirtualData(2024,3)
    }


  }


});


function getPreviousMonthFormattedDate() {
  const date = new Date();
  // 设置日期为月初，比如3月1日
  date.setDate(1);
  // 减去一个月
  date.setMonth(date.getMonth() - 1);
  // 获取上个月的年和月
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 月份从0开始，所以加1，然后格式化为两位数
  // 拼接字符串
  const previousMonthFormattedDate = `${year}-${month}`;

  return previousMonthFormattedDate;
}

console.log(getPreviousMonthFormattedDate()); // 输出类似 '2024-03'


function getVirtualData(year, month) {
  const startDate = +echarts.time.parse(year + '-' + month + '-01');
  const endDate = +echarts.time.parse(year + '-' + (parseInt(month) + 1) + '-01');
  const dayTime = 3600 * 24 * 1000;
  const data = [];
 for (let time = startDate; time < endDate; time += dayTime) {
    data.push([
      echarts.time.format(time, '{yyyy}-{MM}-{dd}', false),
      Math.floor(Math.random() * 1000)
    ]);
  }

  return data;
}

function getVirtualData2() {
  const data5 = [];
  const tuplesArray = resdata.value.map(user => [user.username, parseInt(user.password1, 10)]);
  console.log("q2222121112111qqqqqqqqqqqqqqresdata.value =>")
  console.log(tuplesArray)
  console.log(tuplesArray)
  console.log("tuplesArray.value.length ==")
  console.log(tuplesArray.value.length)
  for (let i = 0; i < resdata.value.length; i++) {
    data2.push(tuplesArray[i])
  }



  axios.post("/api/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth", {}, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("1111111111111axios.post(/api/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth,{},{  ==>")
    console.log(res)
    console.log(res.data.data)
    resdata.value = res.data.data;
    console.log("qqqqqqqqqqqqqqqresdata.value =>")
    console.log(resdata.value)


    const tuplesArray = resdata.value.map(user => [user.username, parseInt(user.password1, 10)]);
    console.log("q2222121112111qqqqqqqqqqqqqqresdata.value =>")
    console.log(tuplesArray)
    for (let i = 0; i < resdata.value.length; i++) {
      data2.push(tuplesArray[i])
    }
    console.log("q333333321112111qqqqqqqqqqqqqqresdata.value =>")
    console.log(data2)

  })
  return data2;
}




const initeCharts = () => {
  let myChart = echarts.init(document.getElementById("myChart08"));
  // 绘制图表
  myChart.setOption(state.option);
};




const data2 = [];


onMounted(() => {
  console.log("zzzzzzzzzzzzzzzzzzz=================================zzzzzzzzzzzzzzzzzz");

  const year1 = '2024';
  const month1 = '03';
  const data1 = getVirtualData(year1, month1);
  console.log(" 51155555555555555const data1 = getVirtualData(year1, month1);   =>")
  console.log(data1);
  console.log(typeof data1);
  console.log(" 52255555555555555const data1 = getVirtualData(year1, month1);   =>")
  const data2 = getVirtualData2();
  console.log(data2);
  console.log(typeof data2);



  axios.post("/api/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth", {}, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("1111111111111axios.post(/api/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth,{},{  ==>")
    console.log(res)
    console.log(res.data.data)
    resdata.value = res.data.data;
    console.log("qqqqqqqqqqqqqqqresdata.value =>")
    console.log(resdata.value)




    for (let i = 0; i < resdata.value.length; i++) {
   data2.push(tuplesArray[i])
    }
    console.log("q333333321112111qqqqqqqqqqqqqqresdata.value =>")
    console.log(data2)

  })


  initeCharts();    //生成图表函数放到post成功内，在没数据时就不会显示图表

});

</script>

<style lang="scss" scoped></style>

-->
<template>
  <!-- 评价信息1占比图 -->
<!--  <h3>test</h3>-->
  <div>
    <div id="myChart08" style="width: 100%; height: 400px"></div>
  </div>
</template>

<script setup>
import {reactive, ref, onMounted} from "vue";
import * as echarts from "echarts";
import axios from "axios";

import {useTokenStore} from '@/stores/token.js'

const Tokenstore = useTokenStore()

const maxdatavalue = ref(0)
const resdata = ref()

const datawmrea = reactive([])

const state = reactive({
  option: {
    tooltip: {
      position: 'top',
      formatter: function (params) { // tooltip 格式化器，用于自定义提示框内容
        // return params.name + '评价数: ' + params.value;
        return  params.value[0] + ' 评价数: ' + params.value[1];
      }
    },
    title: {
      text: '上月评价数', // 标题文本内容
      left: 'center', // 标题水平居中
      top: 'top', // 标题位于容器的顶部
      textStyle: { // 标题文本样式
        color: '#2693e0', // 字体颜色为白色
        fontSize: 18, // 字体大小为18px
        fontWeight: 'bold' // 字体粗细为加粗
      },
    },
    visualMap: {
      min: 0,
      max: maxdatavalue,
      inRange: {
        color: ['#e0ffff', '#006edd']
      },
      orient: 'horizontal',
      left: 'center',
      bottom: 20,
      textStyle: { // 视觉映射组件文本样式
        color: '#ffffff' // 字体颜色设置为白色
      }
    },
    calendar: {
      orient: 'vertical',
      yearLabel: {
        show: false, // 隐藏年份标签
        margin: 40
      },
      dayLabel: {
        firstDay: 1,
        nameMap: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'], // 将星期显示为中文
        color: '#ffffff' // x轴字体颜色为白色
      },
      monthLabel: {
        nameMap: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'], // 将月份显示为中文
        margin: 20,
        color: '#ffffff' // y轴字体颜色为白色
      },
      cellSize: 40,
      range: getPreviousMonthFormattedDate()
    },
    series: {
      type: 'heatmap',
      coordinateSystem: 'calendar',
      data: datawmrea
    }
  }
});

function getPreviousMonthFormattedDate() { //获取上个月的时间格式
  const date = new Date();
  // 设置日期为月初，比如3月1日
  date.setDate(1);
  // 减去一个月
  date.setMonth(date.getMonth() - 1);
  // 获取上个月的年和月
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0'); // 月份从0开始，所以加1，然后格式化为两位数
  // 拼接字符串
  const previousMonthFormattedDate = `${year}-${month}`;
  return previousMonthFormattedDate;
}





const initeCharts = () => {
  let myChart = echarts.init(document.getElementById("myChart08"));
  // 绘制图表
  myChart.setOption(state.option);
};







onMounted(() => {
  console.log("上个月的时间格式：",getPreviousMonthFormattedDate()); // 输出类似 '2024-03'

  axios.post("/api/QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth", {}, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(\"/api/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth\", {}, {")
    console.log(res)
    console.log(res.data.data)
    maxdatavalue.value = res.data.data;



  })






  axios.post("/api/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth", {}, {
    headers: {
      'Authorization': Tokenstore.token,
      "Content-Type": "application/x-www-form-urlencoded"
    },
  }).then((res) => {
    console.log("axios.post(\"/api/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth\", {}, {")
    console.log(res)
    console.log(res.data.data)
    resdata.value = res.data.data;
    console.log("resdata.value =>")
    console.log(resdata.value)

    const tuplesArray = resdata.value.map(user => [user.username, parseInt(user.password1, 10)]);
    console.log("tuplesArray.value =>")
    console.log(tuplesArray)

    for (let i = 0; i < resdata.value.length; i++) {
      datawmrea.push([
        echarts.time.format(tuplesArray[i][0], '{yyyy}-{MM}-{dd}', false),
        tuplesArray[i][1]
      ]);
    }

    initeCharts();    //生成图表函数放到post成功内，在没数据时就不会显示图表
  })

});


</script>

<style lang="scss" scoped></style>

