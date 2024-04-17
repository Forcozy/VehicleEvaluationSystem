<!--
<script setup>

</script>

<template>
&lt;!&ndash;
  &lt;!&ndash;<h3>驾驶舱</h3>&ndash;&gt;
  <div class="conter">
  <dv-full-screen-container>
    <dv-border-box-2 :color="['#2f54eb', '#F2BD27FF']">dv-border-boxssssssss<br><br><br><br><br>sssssssssss-2</dv-border-box-2>
    &lt;!&ndash;
        <div class="row">
          <div class="cell">1</div>
          <div class="cell">2</div>
          <div class="cell">3</div>
        </div>
        <div class="row">
          <div class="cell">4</div>
          <div class="cell">5</div>
          <div class="cell">6</div>
        </div>

    &ndash;&gt;

    <dv-border-box-2>dv-border-box-2</dv-border-box-2>
  </dv-full-screen-container>
  </div>
  &ndash;&gt;
  <template>
    &lt;!&ndash;  我是大屏&ndash;&gt;
    <dv-full-screen-container style="background-color: black">

      <div class="module-box">
        <div style="flex: 0 1 30%">
          <div class="flex flex-direction">
            <dv-border-box-5 style="height: 20vh;">dv-border-box-8</dv-border-box-5>
            <dv-border-box-6 style="height: 20vh;">dv-border-box-8</dv-border-box-6>
            <dv-border-box-11 style="height: 20vh;">dv-border-box-8</dv-border-box-11>
          </div>
        </div>
        <div style="flex: 0 1 40%">
          <dv-border-box-11 style="height: 60vh;width: 100%" title="dv-border-box-11">
          </dv-border-box-11>
        </div>
        <div style="flex: 0 1 30%">
          <div class="flex flex-direction"></div>
          <dv-border-box-11 style="height: 30vh;" title="dv-border-box-11">
          </dv-border-box-11>
          <dv-border-box-11 style="height: 30vh;" title="dv-border-box-11"></dv-border-box-11>
        </div>
      </div>
      <div class="module-box">
        <div style="flex: 0 1 50%">
          <dv-border-box-11 style="height: 38vh;" title="dv-border-box-11">
          </dv-border-box-11>
        </div>
        <div style="flex: 0 1 50%">
          <dv-border-box-11 style="height: 38vh;" title="dv-border-box-11"></dv-border-box-11>
        </div>
      </div>
    </dv-full-screen-container>

  </template>



</template>

<style scoped lang="sass">


</style>
-->

<!--
//代码可用，但是是ts版本的，要改成js的
<template>
  <dv-full-screen-container>
<dv-border-box-1>dv-border-box-1</dv-border-box-1>
  </dv-full-screen-container>


</template>

<script lang='ts'>
import {defineComponent} from 'vue'

export default defineComponent({
  name: '',
  data() {
    return {
      config: {
        header: ['列1', '列2', '列3'],
        data: [
          ['行1列1', '行1列2', '行1列3'],
          ['行2列1', '行2列2', '行2列3'],
          ['行3列1', '行3列2', '行3列3'],
          ['行4列1', '行4列2', '行4列3'],
          ['行5列1', '行5列2', '行5列3'],
          ['行6列1', '行6列2', '行6列3'],
          ['行7列1', '行7列2', '行7列3'],
          ['行8列1', '行8列2', '行8列3'],
          ['行9列1', '行9列2', '行9列3'],
          ['行10列1', '行10列2', '行10列3']
        ]
      },
    }
  }
})
</script>

<style>

body{
  background-color: black;
  margin :0;
}
</style>
-->

<template>
  <div class="background-black">
<!--  <div style="background-image: url('@/assets/images/pageBg.png') ">-->
  <dv-full-screen-container class="background-black">
    <div style="background-color: black;">
    <dv-border-box-10><!-- 全局边框 -->

<!--          第一行，可视化标题    -->
      <CockpitTitle/>   <!--可视化标题 -->




      <!--     第二行，一堆格子的那种   -->
      <div class="module-box">

        <middle-ranking-table/>  <!-- 正中间排名表 -->

        <!--  折线图  -->
        <div style="flex: 0 1 50%">
          <dv-border-box-8 style="width: 100%;height: 400px; ">
            <EchartsLineChart/>

          </dv-border-box-8>
        </div>
      </div>




      <!--     第三行，一堆格子的那种   -->
      <div class="module-box">


        <div style="flex: 0 1 25%">
          <dv-border-box-8 style="width: 100%;height: 400px;">
            <EchartsPieChart01/>  <!-- 评价信息1饼图 -->
          </dv-border-box-8>
        </div>

        <div style="flex: 0 1 25%">
          <dv-border-box-8 style="width: 100%;height: 400px;">
            <EchartsPieChart02/>   <!-- 评价信息2饼图 -->
          </dv-border-box-8>
        </div>


        <div style="flex: 0 1 25%">
          <dv-border-box-8 style="width: 100%;height: 400px;">
            <EchatrsBarChart/>


          </dv-border-box-8>
        </div>

        <div style="flex: 0 1 25%">
          <dv-border-box-8 style="width: 100%;height: 400px;">

<!-- 切割成4*2的 -->
<!--
            <div style="flex: 0 1 25%">

                <div class="module-box_nomargin">
                  <dv-border-box-8 style="width: 50%;height: 100px;">
                     <br><br>
                    <h2 style="color: #46ceff; align-items: center;">&nbsp;&nbsp;hhhhhhh</h2>
                  </dv-border-box-8>
                  <dv-border-box-8 style="width: 50%;height: 100px;">
                  </dv-border-box-8>
                </div>


            </div>

            <div style="flex: 0 1 25%">
              <dv-border-box-8 style="width: 100%;height: 100px;">


              </dv-border-box-8>
            </div>

            <div style="flex: 0 1 25%">
              <dv-border-box-8 style="width: 100%;height: 100px;">


              </dv-border-box-8>
            </div>

            <div style="flex: 0 1 25%">
              <dv-border-box-8 style="width: 100%;height: 100px;">


              </dv-border-box-8>
            </div>
-->
 <EchartsCalendarChart/>

          </dv-border-box-8>
        </div>

      </div><!-- 第三行结束点 -->
      <!-- 第四行开始 -->

<TextMessagesPage/>


    </dv-border-box-10>

  </div>
  </dv-full-screen-container>
<!--  </div>-->
  </div>
</template>


<script setup>
import {reactive, ref, onMounted, watchEffect, onUnmounted} from "vue";
import axios from "axios";

import {useTokenStore} from '@/stores/token.js'

import { useRoute } from 'vue-router';

const route = useRoute();
console.log("route.path  = ")
console.log(route.path )



const Tokenstore = useTokenStore()

import CockpitTitle from "@/views/Cockpit/CockpitTitle.vue";
import EchartsPieChart01 from "@/views/Cockpit/EchartsPieChart01.vue";
import EchartsPieChart02 from "@/views/Cockpit/EchartsPieChart02.vue";
import MiddleRankingTable from "@/views/Cockpit/MiddleRankingTable.vue";
import EchartsLineChart from "@/views/Cockpit/EchartsLineChart.vue";
import DisplayTime from "@/views/Cockpit/DisplayTime.vue";
import EchatrsBarChart from "@/views/Cockpit/EchatrsBarChart.vue";
import EchartsCalendarChart from "@/views/Cockpit/EchartsCalendarChart.vue";
import TextMessagesPage from "@/views/Cockpit/TextMessagesPage.vue";
import {ElMessage} from "element-plus";



// 全屏API
const requestFullscreen = (element) => {
  if (element.requestFullscreen) {
    element.requestFullscreen();
  } else if (element.mozRequestFullScreen) { // Firefox
    element.mozRequestFullScreen();
  } else if (element.webkitRequestFullscreen) { // Chrome and Safari
    element.webkitRequestFullscreen();
  } else if (element.msRequestFullscreen) { // IE/Edge
    element.msRequestFullscreen();
  }
};

const exitFullscreen = () => {
  if (document.exitFullscreen) {
    document.exitFullscreen();
  } else if (document.mozCancelFullScreen) { // Firefox
    document.mozCancelFullScreen();
  } else if (document.webkitExitFullscreen) { // Chrome and Safari
    document.webkitExitFullscreen();
  } else if (document.msExitFullscreen) { // IE/Edge
    document.msExitFullscreen();
  }
};

// 引用全屏状态
const isFullscreen = ref(false);

// 组件挂载时进入全屏
onMounted(() => {
  if (!isFullscreen.value) {
    requestFullscreen(document.documentElement);
    isFullscreen.value = true;
  }
});

// 组件卸载时退出全屏
onUnmounted(() => {
  if (isFullscreen.value) {
    exitFullscreen();
    isFullscreen.value = false;
  }
});
import router from "@/router";

watchEffect(() => {           //校验token
  if (Tokenstore.token === '') {     //token为空时，就是没登陆，闭着眼睛去登录
    console.log("onMounted ========   if (Tokenstore.token === ''){   ===========> ")
    ElMessage.error("登录信息已失效，请重新登录")
    router.push('/Login/welcome_login')
  }
  axios.post("/api/VerifyToken", {  //token不为空时，将token发送给后端校验，校验失败就算过期，重新登陆
        token: Tokenstore.token
      }, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("打印出返回的json ==>  ")
    console.log(res)
    if (res.data.code === 0) {
      console.log("token有效") //0成功1失败
    } else {
      Tokenstore.removeToken()
      ElMessage.error("登录信息已过期，请重新登录")
      router.push('/Login/welcome_login')
      console.log("token无效") //0成功1失败
    }
  });
})






const waterv = ref(30)  //水位图的
const config = {
  data: [waterv.value]
};


/*
const allScreen = ()=>{  //全屏函数
  let domElement = document.documentElement;
  if (domElement.requestFullscreen) {
    domElement.requestFullscreen();
  } else if (domElement.mozRequestFullScreen) {
    domElement.mozRequestFullScreen();
  } else if (domElement.webkitRequestFullScreen) {
    domElement.webkitRequestFullScreen();
  }
}

onMounted(()=>{
  allScreen()
})
*/

</script>


<style>
/*
body {
  background-color: black;
  margin: 0;
  width: 100%;
  height: 90%;
}
*/
.background-black {
  background-color: #01050b;
}
.module-box {
  display: flex;
  justify-content: space-between; /* 两端对齐 */
  margin: 5px;
}
.module-box_nomargin {
  display: flex;
  justify-content: space-between; /* 两端对齐 */
}
.box-middle {
  align-self: center;
}

.d-flax {
  display: flex;
}

.tc {
  text-align: center;
}

.parent-element { /* No Data居中对齐 */
  height: 200px;
  line-height: 200px;
  text-align: center;
}

.Ranking-margin { /*  中间的排名与框的边距   */
  margin-left: 25px; /* 左边距 */
  margin-top: 30px; /* 上边距 */
}
</style>
