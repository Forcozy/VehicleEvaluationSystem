package com.fffff.vehicleevaluationsystem.Server.Impl;

import com.fffff.vehicleevaluationsystem.Mapper.DrivernametoencodedurlMapper;
import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Server.DriverManagementServer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverManagementServerImpl implements DriverManagementServer {
    @Autowired
    private DrivernametoencodedurlMapper drivernametoencodedurlMapper;

    @Override
    public PageBean<Drivernametoencodedurl> GetAllDriverData(int pageNum, int pageSize) {
        //获取司机信息的分页值

        //1.创建PageBean对象
        PageBean<Drivernametoencodedurl> pb = new PageBean<>();

        //2.开启分页查询 PageHelper
        PageHelper.startPage(pageNum, pageSize);


        List<Drivernametoencodedurl> as = drivernametoencodedurlMapper.GetAllData();
        //Page中提供了方法,可以获取PageHelper分页查询后 得到的总记录条数和当前页数据
        Page<Drivernametoencodedurl> p = (Page<Drivernametoencodedurl>) as;
        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());

        //   return new PageInfo<>(yourEntityMapper.selectAll());
        return pb;
    }

}
