import{_ as m,k as _,a as r,c as g,d as v,e as s,l as c,v as i,p as h,m as k,E as d,g as w}from"./index-DXB3kBi2.js";import{p as f}from"./index-DXExZLUB.js";import{a as x}from"./axios-Cm0UX6qg.js";const T=t=>(h("data-v-e44de394"),t=t(),k(),t),U={class:"container"},y={class:"login-wrapper"},I=T(()=>s("div",{class:"header"},"车辆评价系统后台",-1)),L={class:"form-wrapper"},S={__name:"LoginPage",setup(t){const a=_(),n=r(""),l=r("");r("");const u=()=>{console.log("x"),console.log("input_name  =>"),console.log(n.value),console.log("input_password  =>"),console.log(l.value),console.log("用封装的post  ==post  ==>"),f("/api/Login",{username:n.value,password1:l.value},e=>{console.log("message ="),console.log(e),d.success("登录成功"),a.setToken(e),x.post("/api/tokenGetUserName",{token:a.token},{headers:{Authorization:a.token,"Content-Type":"application/x-www-form-urlencoded"}}).then(o=>{console.log('axios.post("/api/tokenGetUserName", {  ==>'),console.log(o.data),a.setUsername(o.data)}),w.push("/home/")},e=>{console.log("message =",e),d.error(e)})};return(e,o)=>(g(),v("div",U,[s("div",y,[I,s("div",L,[c(s("input",{type:"text",name:"username",placeholder:"请输入用户名",class:"input-item","onUpdate:modelValue":o[0]||(o[0]=p=>n.value=p)},null,512),[[i,n.value]]),c(s("input",{type:"password",name:"password",placeholder:"请输入密码",class:"input-item","onUpdate:modelValue":o[1]||(o[1]=p=>l.value=p)},null,512),[[i,l.value]])]),s("button",{class:"btn",style:{border:"none",color:"#3198fa","font-size":"16px"},onClick:u}," 登陆")])]))}},N=m(S,[["__scopeId","data-v-e44de394"]]);export{N as default};
