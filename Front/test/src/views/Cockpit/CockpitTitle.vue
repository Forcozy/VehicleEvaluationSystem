<template>
<!-- 驾驶舱标题 -->

  <!--    第一行，可视化标题    -->
  <div class="module-box">
    <div style="flex: 0 1 30%">
      <dv-decoration-10 style="height: 5px"/>
<!--      <el-button  color="#626aef">返回</el-button>-->
<!--      <el-icon><Coin /></el-icon>-->
<!--      <dv-decoration-7 style="width:150px;height:30px;"  >-->
     <br/> &nbsp; &nbsp;
      <el-button  color="#000" @click="router.push({path: '/home/1',params: {id: 1}})" >返回</el-button>
<!--      </dv-decoration-7>-->


    </div>
    <div style="flex: 0 1 40%">
      <div class="d-flax">
        <!--三等分 -->
        <!-- 左          -->
        <dv-decoration8 style="height: 50px;flex: 1"/>
        <!--   中           -->
        <dv-decoration-11  style="width:200px;height:60px;color: greenyellow;font-weight: bold; ">车辆评价系统数据中心
        </dv-decoration-11>
        <!--              <dv-decoration11 style="flex: 1;color: white" class="tc box-middle ">车辆评价系统数据中心</dv-decoration11>-->
        <!--              <div style="flex: 1;color: white" class="tc box-middle " >车辆评价系统数据中心</div>-->
        <!--   右           -->
        <dv-decoration-8 :reverse="true" style="height: 50px;flex: 1"/>
      </div>
    </div>
    <div style="flex: 0 1 30%">
      <dv-decoration-10 style="height: 5px;transform: rotateY(180deg)"></dv-decoration-10>
    </div>
  </div>


</template>

<style scoped>

</style>
<script setup>
import {Coin} from "@element-plus/icons-vue";
import router from "@/router/index.js";

const gohome = () => {
 router.push({ path: '/home/' });
}



</script>


<!--


<template>
  &lt;!&ndash; 驾驶舱标题 &ndash;&gt;

  &lt;!&ndash;    第一行，可视化标题    &ndash;&gt;
  <div class="module-box">
    <div style="flex: 0 1 30%">
      <dv-decoration-10 style="height: 5px"/>
      &lt;!&ndash;      <el-button  color="#626aef">返回</el-button>&ndash;&gt;
    </div>
    <div style="flex: 0 1 40%">
      <div class="d-flax">
        &lt;!&ndash;三等分 &ndash;&gt;
        &lt;!&ndash; 左          &ndash;&gt;
        <dv-decoration8 style="height: 50px;flex: 1"/>
        &lt;!&ndash;   中           &ndash;&gt;
        <dv-decoration-11  style="width:200px;height:60px;color: greenyellow;font-weight: bold; ">车辆评价系统数据中心
        </dv-decoration-11>
        &lt;!&ndash;              <dv-decoration11 style="flex: 1;color: white" class="tc box-middle ">车辆评价系统数据中心</dv-decoration11>&ndash;&gt;
        &lt;!&ndash;              <div style="flex: 1;color: white" class="tc box-middle " >车辆评价系统数据中心</div>&ndash;&gt;
        &lt;!&ndash;   右           &ndash;&gt;
        <dv-decoration-8 :reverse="true" style="height: 50px;flex: 1"/>
      </div>
    </div>
    <div style="flex: 0 1 30%">
      <dv-decoration-10 style="height: 5px;transform: rotateY(180deg)"></dv-decoration-10>
    </div>
  </div>


</template>

<style scoped>

</style>
<script setup>
</script>
-->
