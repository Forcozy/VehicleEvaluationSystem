
import axios from "axios";
import {ElMessage} from "element-plus";

const defaultError = () => ElMessage.error('发生一些错误，请联系管理员')
const defaultFailure = (message) => ElMessage.warning(message)


/*
import {useTokenStore} from '@/stores/token.js'
const Tokenstore2 = useTokenStore()
https://blog.csdn.net/dnsjseben/article/details/137237202?ops_request_misc=&request_id=&biz_id=102&utm_term=pinia.js&spm=1018.2226.3001.4187
*/


import {useTokenStore} from '@/stores/token.js'

import { createPinia } from 'pinia'

let userStore = useTokenStore(createPinia());


//封装post
function post1(url, data, success, failure = defaultFailure, error = defaultError) {
    console.log("function post1(url, data, success, failure = defaultFailure, error = defaultError) { userStore.token,======>")
    console.log( userStore.token)
    axios.post(url, data, {
        //因为是通过表单提交的 所以需要将Content-Type改成表单的形式
        headers: {
            'Authorization': userStore.token,
            "Content-Type": "application/x-www-form-urlencoded"
        },
        //发起请求是否携带Cookie
        //withCredentials: true
        //then 处理数据
    }).then(({data}) => {
        console.log("data = ",data)
        if (data.code===0) {
        success(data.message, data.status)
    }
else
    {
        failure(data.message, data.status)
    }
}
).
catch(error)
}



/*
//封装get
function get(url, success, failure =  defaultFailure , error =  defaultError) {
    axios.get(url, {
        //发起请求是否携带Cookie
        withCredentials: true
        //then 处理数据
    }).then(({data}) => {
        if (data.success)
            success(data.message, data.status)
        else
            failure(data.message, data.status)
    }).catch(error)
}

//导出去
export {get, post}
*/



/*

// 导入axios库
import axios from "axios";
// 导入Element Plus UI库中的ElMessage组件，用于显示消息提示
import { ElMessage } from "element-plus";

// 定义默认的错误处理函数，当发生错误时显示提示信息
const defaultError = () => ElMessage.error('发生一些错误，请联系管理员');
// 定义默认的失败处理函数，当请求失败时显示提示信息
const defaultFailure = (message) => ElMessage.warning(message);


*/



// 封装POST请求函数
function post(url, data, success, failure = defaultFailure, error = defaultError) {
    // 发起POST请求，设置请求头为表单形式，携带Cookie
    axios.post(url, data, {
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        withCredentials: true
    }).then(({data}) => { // 请求成功后的处理
        console.log("data = ", data); // 在控制台打印响应数据
        if (data.code === 0) { // 如果响应码为0，表示成功
            success(data.message, data.status); // 调用成功的回调函数
        } else { // 如果响应码不为0，表示失败
            failure(data.message, data.status); // 调用失败的回调函数
        }
    }).catch(error); // 请求失败后的处理
}

// 封装GET请求函数
function get(url, success, failure = defaultFailure, error = defaultError) {
    // 发起GET请求，携带Cookie
    axios.get(url, {
        withCredentials: true
    }).then(({data}) => { // 请求成功后的处理
        if (data.success) { // 如果响应数据中的success属性为true，表示成功
            success(data.message, data.status); // 调用成功的回调函数
        } else { // 如果响应数据中的success属性不为true，表示失败
            failure(data.message, data.status); // 调用失败的回调函数
        }
    }).catch(error); // 请求失败后的处理
}

// 导出get和post函数
export { get, post ,post1};
