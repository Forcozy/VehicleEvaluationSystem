package com.fffff.vehicleevaluationsystem.Controller;

import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Pojo.User;
import com.fffff.vehicleevaluationsystem.Server.Impl.UserServerImpl;
import com.fffff.vehicleevaluationsystem.Utils.JwtUtil;
import com.fffff.vehicleevaluationsystem.Utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
public class LoginRegistrationController {
    @Autowired
    private UserServerImpl userServerimpl;

    //0408yyd
    @PostMapping(value = "/Login")
    @ResponseBody
    public Result<String> Login(User u) {   // 登录成功要返回token
        System.out.println("Login =>");

        System.out.println(u.toString());

        List<User> userByUsername = userServerimpl.findUserByUsername(u.getUsername());
        if (userByUsername.size() == 0) {  //查询这个用户名若没有
            System.out.println("return Result.error(\"无该用户\"); ==");
            return Result.error("无该用户");
        } else if (userByUsername.size() > 1) {
            System.out.println(" return Result.error(\"用户名重复，系统错误，请联系管理员\"); ==");
            return Result.error("用户名重复，系统错误，请联系管理员");
        }
        if (Md5Util.getMD5String(u.getPassword1()).equals(userByUsername.get(0).getPassword1())) {//（加密了）用户输入的密码和数据库内的密码对比
            System.out.println("  return Result.success(\"登录成功\");  ==");

            Map<String, Object> claims = new HashMap<>();
            claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);
            System.out.println("登录成功的token =");
            System.out.println(token);

            return Result.success(token);
        } else {
            System.out.println("else  return Result.success(\"用户名或密码输入错误\");  ==");
            return Result.error("用户名或密码输入错误");
        }
    }






}
