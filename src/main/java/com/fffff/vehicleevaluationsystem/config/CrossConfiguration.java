package com.fffff.vehicleevaluationsystem.config;

import com.fffff.vehicleevaluationsystem.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//拦截器配置项
/**
 * @ClassName CrossConfiguration
 * @Date 2022/3/25 11:36
 * @Version 1.0
 **/
@Configuration
public class CrossConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//配置映射
                .allowedOriginPatterns("*")//设置允许那些域来访问
                .allowedHeaders("*")//请求头字段
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")//请求方式(GET,POST,DELETE,PUT)
                .allowCredentials(true)//是否允许携带cookie
                .maxAge(3600);//设置3600秒，表示3600秒之内浏览器不必再次询问

    }


    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)  //注册拦截器，
                .excludePathPatterns("/Login", "/user/register","/VerifyToken","/tokenGetUserName",


                        "/getDriverName","/QRCodeSubmitInformation","/static/**");  //但是 有一部分资源要 不拦截 ，比如说登录注册页  验证token ,获取司机姓名，提交司机评价
    }


}
