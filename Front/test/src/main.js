/*
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "element-plus/dist/index.css";







const app = createApp(App)

import * as echarts from "echarts";
app.config.globalProperties.$echarts = echarts







//引入 ElementPlus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
app.use(ElementPlus)


//安装Element Plus图标插件
import * as ElIcon from '@element-plus/icons-vue'
for (let iconName in ElIcon){
    app.component(iconName, ElIcon[iconName])
}








import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)
/!*
// 将自动注册所有组件为全局组件
import dataV from '@jiaminghi/data-view'
app.use(dataV)
*!/


import DataVVue3 from '@kjgl77/datav-vue3'
app.use(DataVVue3)


app.use(router)
app.mount('#app')
*/


import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import '@/styles/index.scss'  //导入sass文件
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";



const app = createApp(App)
//放在const app = createApp(App)这行的下面
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}


import ECharts from "vue-echarts";
import 'echarts';



// main.ts中全局引入
import DataVVue3 from '@kjgl77/datav-vue3'

app.use(DataVVue3)





//import * as echarts from 'echarts';
//app.config.globalProperties.$echarts = echarts

import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
app.use(pinia)


app.use(router)
app.use(ElementPlus)
app.component('ECharts',ECharts)
app.mount("#app")
