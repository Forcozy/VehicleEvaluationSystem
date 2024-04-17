package com.fffff.vehicleevaluationsystem.Mapper;

import com.fffff.vehicleevaluationsystem.Pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USERTABLE where username = #{username}")
    List<User> findUserByUsername(String username);//根据用户名（手机号）查询用户信息

    @Insert("insert into USERTABLE(username,password1) values( #{username},  #{password1})")
    Integer CreateNewUser(User user);//插入新评价记录






}
