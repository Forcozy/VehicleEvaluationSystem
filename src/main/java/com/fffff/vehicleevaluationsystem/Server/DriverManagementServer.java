package com.fffff.vehicleevaluationsystem.Server;

import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;

public interface DriverManagementServer { //司机管理表

     PageBean<Drivernametoencodedurl> GetAllDriverData(int pageNum, int pageSize);


}
