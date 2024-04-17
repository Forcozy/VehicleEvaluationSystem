import{a as b}from"./axios-Cm0UX6qg.js";import{z as Z}from"./zh-cn-BQvgwFPn.js";import{e as ee}from"./en-HEJrwB0q.js";import{_ as oe,k as ae,a,B as te,C as le,o as ne,b as l,c as k,d as T,f as t,w as n,F as A,e as y,h as N,G as se,H as re,t as ce,A as ue,s as ie,p as de,m as pe,E,g as B,i as ge}from"./index-DXB3kBi2.js";const me=[{label:"表内序号",prop:"id"},{label:"司机名",prop:"drivername"},{label:"司机信息存入时间",prop:"indata"}],F=g=>(de("data-v-89012432"),g=g(),pe(),g),ve=F(()=>y("h6",null,null,-1)),_e={class:"container1"},fe={class:"container"},he={key:0},be=F(()=>y("br",null,null,-1)),ke={__name:"DriverManagementTablePage",setup(g){const u=ae(),M=a("zh-cn"),Q=te(()=>M.value==="zh-cn"?Z:ee),V=a(!1),$=a(!1),G=a(!1),d=a(10),i=a(1),C=a(0),p=a(null),m=a(""),S=a("按照名字查找司机"),L=()=>{b({url:"/api/file/download",method:"GET",responseType:"blob",headers:{Authorization:u.token}}).then(e=>{const o=window.URL.createObjectURL(new Blob([e.data])),r=document.createElement("a");r.href=o,r.setAttribute("download","全部司机对应的二维码.zip"),document.body.appendChild(r),r.click()}).catch(e=>{console.error("Error downloading file: ",e)})},U=()=>{console.log("每页显示条数改变  handleSizeChange  ==>"),console.log("最新每页显示条数  pageSize =>",d.value),console.log("最新当前页码pageNum =>",i.value),v()},I=()=>{console.log("当前页码数改变  handleCurrentChange  ==>"),console.log("最新每页显示条数  pageSize =>",d.value),console.log("最新当前页码pageNum =>",i.value),v()},v=()=>{s.value?(console.log("请求条件判断器 查询司机  if (DriverNameQuery_buttonStatus){   ==>"),H()):(console.log("请求条件判断器 无标志位 查询全部   ==>"),O())},P=()=>{i.value=1,s.value=!1,_.value.backgroundColor="#409eff"};le(()=>{u.token===""&&(console.log("onMounted ========   if (Tokenstore.token === ''){   ===========> "),E.error("登录信息已失效，请重新登录"),B.push("/Login/welcome_login")),b.post("/api/VerifyToken",{token:u.token},{headers:{Authorization:u.token,"Content-Type":"application/x-www-form-urlencoded"}}).then(e=>{console.log("打印出返回的json ==>  "),console.log(e),e.data.code===0?console.log("token有效"):(u.removeToken(),E.error("登录信息已过期，请重新登录"),B.push("/Login/welcome_login"),console.log("token无效"))})});function R(e){const o=new Date(e),r=o.getFullYear(),w=(o.getMonth()+1).toString().padStart(2,"0"),z=o.getDate().toString().padStart(2,"0"),f=o.getHours().toString().padStart(2,"0"),h=o.getMinutes().toString().padStart(2,"0"),D=o.getSeconds().toString().padStart(2,"0");return`${r}-${w}-${z} ${f}:${h}:${D}`}const x=()=>{for(let e=0;e<p.value.length;e++)p.value[e].indata=R(p.value[e].indata)},_=a({backgroundColor:"#409eff",color:"#fff",padding:"10px 20px",border:"none",borderRadius:"4px",cursor:"pointer"}),s=a(!1),j=()=>{console.log("const DriverNameQuery_handleButtonClick = () => {    //查询今日的按钮函数    ====>");let e=s.value;P(),s.value=e.valueOf(),s.value=!s.value,s.value?(_.value.backgroundColor="#67c23a",S.value="查看全部"):(_.value.backgroundColor="#409eff",m.value="",S.value="按照名字查找司机"),v()},O=()=>{b.post("/api/GetAllDriverData",{pageSize:d.value,pageNum:i.value},{headers:{Authorization:u.token,"Content-Type":"application/x-www-form-urlencoded"}}).then(e=>{console.log("axios.post(/api/GetAllDriverData,{},{  ==>"),console.log(e),console.log(e.data.data),p.value=e.data.data.items,C.value=e.data.data.total,x()})};ne(()=>{console.log("onMounted ===================> "),v()});const H=()=>{b.post("/api/DriverNameQuery",{DriverName:m.value,pageSize:d.value,pageNum:i.value},{headers:{Authorization:u.token,"Content-Type":"application/x-www-form-urlencoded"}}).then(e=>{console.log("axios.post(/api/QueryAllEvaluationDataForToday,{},{  ==>"),console.log(e.data.data),p.value=e.data.data.items,C.value=e.data.data.total,x()})};return(e,o)=>{const r=l("el-input"),w=l("Check"),z=l("el-icon"),f=l("el-button"),h=l("el-col"),D=l("el-row"),q=l("el-table-column"),Y=l("el-table"),J=l("el-pagination"),K=l("el-config-provider"),W=l("el-card");return k(),T(A,null,[ve,t(W,null,{default:n(()=>[t(D,{gutter:20,class:"header"},{default:n(()=>[t(h,{span:7},{default:n(()=>[y("div",_e,[t(r,{modelValue:m.value,"onUpdate:modelValue":o[0]||(o[0]=c=>m.value=c),style:{width:"240px"},placeholder:"请输入司机名",clearable:""},null,8,["modelValue"]),N("     "),y("div",fe,[t(f,{onClick:j,style:se(_.value)},{default:n(()=>[s.value?(k(),T("div",he,[t(z,{class:"el-icon-check"},{default:n(()=>[t(w)]),_:1})])):re("",!0),N(" "+ce(S.value),1)]),_:1},8,["style"])])])]),_:1}),t(h,{span:7},{default:n(()=>[t(f,{type:"primary",onClick:L},{default:n(()=>[N("导出全部司机的二维码")]),_:1})]),_:1})]),_:1}),t(Y,{data:p.value,border:"",stripe:"",style:{width:"100%"}},{default:n(()=>[(k(!0),T(A,null,ue(ie(me),(c,X)=>(k(),ge(q,{prop:c.prop,label:c.label,key:X},null,8,["prop","label"]))),128))]),_:1},8,["data"]),be,t(K,{locale:Q.value},{default:n(()=>[t(J,{"current-page":i.value,"onUpdate:currentPage":o[1]||(o[1]=c=>i.value=c),"page-size":d.value,"onUpdate:pageSize":o[2]||(o[2]=c=>d.value=c),"page-sizes":[10,20,30,40],small:V.value,disabled:G.value,background:$.value,layout:"total, sizes, prev, pager, next, jumper",total:C.value,onSizeChange:U,onCurrentChange:I},null,8,["current-page","page-size","small","disabled","background","total"])]),_:1},8,["locale"])]),_:1})],64)}}},ze=oe(ke,[["__scopeId","data-v-89012432"]]);export{ze as default};