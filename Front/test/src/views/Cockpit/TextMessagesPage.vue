<template>
  <div class="module-box">   <!--  盒子内水平（从左到右）分布   div内垂直(从上到下)分布-->
    <!--

        <div style="flex: 0 1 25%"> &lt;!&ndash; 这里的百分比是这一行的大小占比  有两个时两端对齐 &ndash;&gt;
          <dv-border-box-8 style="width: 100%;height: 200px;">


            <div style="flex: 0 1 25%">
              <dv-border-box-8 style="width: 100%;height: 100px;" class="center-text">

              </dv-border-box-8>
            </div>


            <div style="flex: 0 1 25%">
              <dv-border-box-8 style="width: 100%;height: 100px;">


              </dv-border-box-8>
            </div>


          </dv-border-box-8>
        </div>


        <div style="flex: 0 1 25%">
          <dv-border-box-8 style="width: 100%;height: 200px;">


          </dv-border-box-8>
        </div>
    -->

    <div style="flex: 0 1 100%">
      <dv-border-box-8 style="width: 100%;height: 200px;">

        <table style="width: 100%;height: 200px; /*border: 1px solid #24dca4*/">

          <tr>
            <!--       可以用的有点大     <td style=" padding: 10px;">
                          <div style=" background-color: #0b1325;color: #ffffff;font-size: 22px;border-radius: 10px;padding: 10px;">
                            <span style="color: #ffffff;font-size: 22px;">全部司机数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                            <span style="color: #2693e0;font-size: 33px;margin-left: 10px;"> {{ kkk }}</span>
                          </div>
                        </td>-->

            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">全部司机数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ AllDrivers }}</span>
              </div>
            </td>

            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">今日被评价司机数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ NumberOfDriversEvaluatedToday }}</span>
              </div>
            </td>

            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">本月被评价司机数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ NumberOfEvaluatedDriversInTheCurrentMonth }}</span>
              </div>
            </td>


          </tr>


          <tr>
            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">全部评价数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ TotalNumberOfEvaluations }}</span>
              </div>
            </td>

            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">今日提交评价数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ NumberOfCommentsSubmittedToday }}</span>
              </div>
            </td>

            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title"> 本月提交评价数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ NumberOfEvaluationsSubmittedInTheCurrentMonth }}</span>
              </div>
            </td>


          </tr>


          <tr>
            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">本年评价总数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ TotalNumberOfEvaluationsThisYear }}</span>
              </div>
            </td>

            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">过去7天评价总数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ TotalNumberOfReviewsInThePast7Days }}</span>
              </div>
            </td>

            <td style=" padding: 10px;">
              <div class="card_data_div">
                <span class="card_title">过去一个月评价总数:  &nbsp; &nbsp; &nbsp; &nbsp;</span>
                <span class="card_data"> {{ TotalNumberOfEvaluationsInThePastMonth }}</span>
              </div>
            </td>


          </tr>


        </table>


      </dv-border-box-8>
    </div>

    <!--

        <div style="flex: 0 1 25%">
          <dv-border-box-8 style="width: 100%;height: 200px;">

          </dv-border-box-8>
        </div>
        <div style="flex: 0 1 25%">
          <dv-border-box-8 style="width: 100%;height: 200px;">

          </dv-border-box-8>
        </div>
    -->


  </div>

</template>

<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import {useTokenStore} from '@/stores/token.js'


const Tokenstore = useTokenStore()

const AllDrivers = ref()
const NumberOfDriversEvaluatedToday = ref()
const NumberOfEvaluatedDriversInTheCurrentMonth = ref()
const TotalNumberOfEvaluations = ref()
const NumberOfCommentsSubmittedToday = ref()
const NumberOfEvaluationsSubmittedInTheCurrentMonth = ref()
const TotalNumberOfEvaluationsThisYear = ref()
const TotalNumberOfReviewsInThePast7Days = ref()
const TotalNumberOfEvaluationsInThePastMonth = ref()

const GatTotalNumberOfReviewsInThePast7Days = () => {
  console.log("TotalNumberOfReviewsInThePast7Days ==>  ")
  axios.post("/api/TotalNumberOfReviewsInThePast7Days", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    TotalNumberOfReviewsInThePast7Days.value = res.data.data
  });
}






const GatTotalNumberOfEvaluationsInThePastMonth = () => {
  console.log("11111111111TotalNumberOfEvaluationsThisYear ==>  ")
  axios.post("/api/TotalNumberOfEvaluationsInThePastMonth", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    TotalNumberOfEvaluationsInThePastMonth.value = res.data.data
  });
}

const GatTotalNumberOfEvaluationsThisYear = () => {
  console.log("11111111111TotalNumberOfEvaluationsThisYear ==>  ")
  axios.post("/api/TotalNumberOfEvaluationsThisYear", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    TotalNumberOfEvaluationsThisYear.value = res.data.data
  });
}

const GatNumberOfEvaluationsSubmittedInTheCurrentMonth = () => {
  console.log("11111111111GatAllDrivers ==>  ")
  axios.post("/api/NumberOfEvaluationsSubmittedInTheCurrentMonth", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    NumberOfEvaluationsSubmittedInTheCurrentMonth.value = res.data.data
  });
}


const GatNumberOfEvaluatedDriversInTheCurrentMonth = () => {
  console.log("11111111111GatAllDrivers ==>  ")
  axios.post("/api/NumberOfEvaluatedDriversThisMonth", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    NumberOfEvaluatedDriversInTheCurrentMonth.value = res.data.data
  });
}


const GatAllDrivers = () => {
  console.log("11111111111GatAllDrivers ==>  ")
  axios.post("/api/GetAlldrivernameNum", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    AllDrivers.value = res.data.data
  });
}


const GatNumberOfDriversEvaluatedTodayss = () => {
  console.log("NumberOfDriversEvaluatedTodayss ==>  ")
  axios.post("/api/NumberOfDriversEvaluatedTodayss", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    NumberOfDriversEvaluatedToday.value = res.data.data
  });
}

const GatNumberOfCommentsSubmittedToday = () => {
  // console.log("NumberOfDriversEvaluatedToday ==>  ")
  axios.post("/api/NumberOfDriversEvaluatedToday", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    // console.log("11111111打印出返回的json ==>  ")
    //console.log(res)
    NumberOfCommentsSubmittedToday.value = res.data.data
  });
}


const GatTotalNumberOfEvaluations = () => {
  console.log("GatTotalNumberOfEvaluations ==>  ")
  axios.post("/api/GetAllEvaluationNum", {}, {
        headers: {
          'Authorization': Tokenstore.token,
          "Content-Type": "application/x-www-form-urlencoded"
        }
      }
  ).then((res) => {
    console.log("11111111打印出返回的json ==>  ")
    console.log(res)
    TotalNumberOfEvaluations.value = res.data.data
  });
}


onMounted(() => {
  GatAllDrivers()
  GatNumberOfDriversEvaluatedTodayss()
  GatNumberOfCommentsSubmittedToday()
  GatTotalNumberOfEvaluations()
  GatNumberOfEvaluatedDriversInTheCurrentMonth()
  GatNumberOfEvaluationsSubmittedInTheCurrentMonth()
  GatTotalNumberOfEvaluationsThisYear()
  GatTotalNumberOfEvaluationsInThePastMonth()
  GatTotalNumberOfReviewsInThePast7Days()
})

</script>

<style scoped>

.center-block {
  width: 50%;
  margin-left: auto;
  margin-right: auto;
}

.card_data_div {
  background-color: #0b1325;
  color: #ffffff;
  font-size: 22px;
  border-radius: 10px;
  padding: 8px;
}

.card_title {
  color: #ffffff;
  font-size: 15px;
}

.card_data {
  color: #2693e0;
  font-size: 23px;
  margin-left: 10px;
}

/* .card_data_div{
  background-color: #0b1325;
  color: #ffffff;
  font-size: 22px;
  border-radius: 10px;
  padding: 10px;
}
.card_title{
  color: #ffffff;
  font-size: 22px;
}
.card_data{
  color: #2693e0;
  font-size: 33px;
  margin-left: 10px;
} */
</style>
