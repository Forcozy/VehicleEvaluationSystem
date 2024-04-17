<template>
  <!--  正中间排名表  -->
  <div style="flex: 0 1 50%">
    <dv-border-box-11 style="width: 100%;height: 400px; " title="全部评价数排名">

      <div v-if="scrollranking.length>0">
        <br>
        <dv-scroll-ranking-board :config="configscrollranking" style="width:95%;height:325px;"
                                 class="Ranking-margin"/>
      </div>

      <div v-else style="color:#fff;width: 100%;height: 400px;">   <!-- 没数据就显示 -->
        <div class="tc box-middle parent-element" style="text-align: center;">
          No Data
        </div>
      </div>


    </dv-border-box-11>
  </div>

</template>




<script setup>
import {useTokenStore} from '@/stores/token.js'
const Tokenstore = useTokenStore()
import {onMounted, ref} from "vue";
import axios from "axios";

const scrollranking = ref([]) //中间排名动态存储数组
const configscrollranking = {       //中间排名动态给组件的格式
  data: [],
  unit: '条' //unit:单位
}
const getscrollranking = () => {  //获取中间排名的数据
  axios.post("/api/GetAllDriverRanking", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("GetAllDriverRanking ==>")
    console.log("打印出返回的json ==>  ")
    console.log(res)
    console.log("list  ==>")
    console.log(res.data.data)
    scrollranking.value = res.data.data
    console.log("scrollranking.value  ==>")
    console.log(scrollranking.value)

    // 一个函数用于复制 scrollranking 数据到 configscrollranking.data
    function copyData() {
      configscrollranking.data = scrollranking.value.map(item => ({...item}));
    }

    // 在组件加载时复制数据   （执行函数）
    copyData()
  })
}


onMounted(() => {
  getscrollranking()  //初始化获取值
})
</script>








<style scoped>

</style>
