import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/Index.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/QRCode/:id',
      name: 'QRCode',
      component: () => import('@/components/QRCodeView/QRCodePage.vue'),
    },
    {
      path: '/QRCodeThanks',
      name: 'QRCodeThanks',
      component: () => import('@/components/QRCodeView/ThanksPage.vue'),
    },
    {
      path: '/NoDataQRCode',
      name: 'NoDataQRCode',
      component: () => import('@/components/QRCodeView/NoDataDriverName.vue'),
    },
    {
      path: '/Login',
      name: 'Login',
      component: () => import('../views/Login.vue'),
      children: [
        {
          path: '/Login/welcome_login',
          name: 'welcome_login',
          component: () => import('@/components/LoginView/LoginPage.vue')
        },
      ]
    },
    {
      path: '/home',
      name: '主页',
      component: () => import('../views/Index.vue'),
      children:[
        {
          path: '/home/:id?',
          name: '',
          component: () => import('@/components/IndexView/index/index.vue')
        },
        {
          path: '/home/EvaluationInformationTablePage',
          name: '评价信息汇总表',
          component: () => import('@/components/EvaluationInformationTable/EvaluationInformationTablePage.vue'),
        },
        {
          path: 'DriverRecordForm/:id?',
          name: '司机评价记录查询表',
          component: () => import('@/components/EvaluationRankingTable/DriverEvaluationRecordQueryTablePage.vue'),
        },
        {
          path: '/home/EvaluationRankingTablePage',
          name: '评价排名表',
          component: () => import('@/components/EvaluationRankingTable/EvaluationRankingTablePage.vue')
        },
        {
          path: '/home/DriverManagementTablePage',
          name: '司机管理表',
          component: () => import('@/components/DriverManagement/DriverManagementTablePage.vue'),
        },
        {
          path: '/home/DriverManagementTablePagEdit',
          name: '修改司机管理表',
          component: () => import('@/components/DriverManagement/DriverManagementTablePagEdit.vue'),
        },

      ]
    },
    {
      path: '/home/Cockpit',
      name: 'Cockpit',
      component: () => import('@/views/Cockpit/Cockpit.vue')
    },
    {
      path: '',
      name: 'hello',
      component: () => import('@/components/welcome.vue')
    },
  ]
})


export default router
