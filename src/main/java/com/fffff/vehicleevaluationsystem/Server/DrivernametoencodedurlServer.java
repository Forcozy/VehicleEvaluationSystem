package com.fffff.vehicleevaluationsystem.Server;

import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;

import java.util.List;

public interface DrivernametoencodedurlServer {
    Integer UPDATEDrivernameFindByID(Drivernametoencodedurl drivernametoencodedurl); //新增一个司机记录

    List<Drivernametoencodedurl> GetAllData(); //获取全部值

    Integer InsertNameCode(Drivernametoencodedurl drivernametoencodedurl); //新增一个司机记录

    List<Drivernametoencodedurl> FindDriverByUUID(String UUID);//根据用户UUID找到司机名

    Integer GetAlldrivernameNum();//获取总数

    PageBean<Drivernametoencodedurl> FindDriverNameByDriverData(String Drivername,int pageNum, int pageSize);//根据司机名找到司机

    Integer GenerateQRCode();//二维码生成

}
