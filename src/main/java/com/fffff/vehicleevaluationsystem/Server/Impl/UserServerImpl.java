package com.fffff.vehicleevaluationsystem.Server.Impl;

import com.fffff.vehicleevaluationsystem.Mapper.UserMapper;
import com.fffff.vehicleevaluationsystem.Pojo.User;
import com.fffff.vehicleevaluationsystem.Server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServerImpl implements UserServer {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public Integer CreateNewUser(User user) {
        return userMapper.CreateNewUser(user);
    }
}
