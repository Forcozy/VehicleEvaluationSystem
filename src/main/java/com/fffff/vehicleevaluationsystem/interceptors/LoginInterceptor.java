package com.fffff.vehicleevaluationsystem.interceptors;


import com.fffff.vehicleevaluationsystem.Utils.JwtUtil;
import com.fffff.vehicleevaluationsystem.Utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //令牌验证   （这里根据  request 对象获得）
        String token = request.getHeader("Authorization");  //获取到token
        //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);  //token不对这里就会报错,不报错就正常处理业务数据即可
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            //验证成功，放行
            return true;
        } catch (Exception e) {
            response.setStatus(401);//设置状态码
            //验证失败，不放行
            return false;
        }
    }



    /* 请求结束后，数据就不会再使用，请求结束删除线程的临时变量的数据 */

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
