package com.fffff.vehicleevaluationsystem.Server;

import com.fffff.vehicleevaluationsystem.Pojo.User;

import java.util.List;

public interface UserServer {
    List<User> findUserByUsername(String username);//根据用户名（手机号）查询用户信息

    Integer CreateNewUser(User user);//插入新评价记录
}
