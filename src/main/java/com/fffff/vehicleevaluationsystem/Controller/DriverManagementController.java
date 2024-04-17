package com.fffff.vehicleevaluationsystem.Controller;

import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Server.Impl.DriverManagementServerImpl;
import com.fffff.vehicleevaluationsystem.Server.Impl.DrivernametoencodedurlServerImpl;
import com.fffff.vehicleevaluationsystem.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
public class DriverManagementController {

    @Autowired
    private DriverManagementServerImpl driverManagementServer;

    @Autowired
    private DrivernametoencodedurlServerImpl drivernametoencodedurlServerImpl;

    @PostMapping(value = "/InsertNewDriver")   //插入新的司机
    public Result InsertNewDriver(String drivername) {
        // 获取当前时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        String formattedDateTime = currentDateTime.format(formatter);
        // 获取当前时间（Date类型）
        Date currentDate = new Date();

        // 定义时间格式
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        String formattedDateTime1 = formatter1.format(currentDate);


        System.out.println("drivername  =>");
        System.out.println(drivername);
        Drivernametoencodedurl drivernametoencodedurl = new Drivernametoencodedurl();
        drivernametoencodedurl.setDRIVERNAME(drivername);
        drivernametoencodedurl.setINDATA(currentDate);
        drivernametoencodedurl.setUUID(UUIDUtils.getUUID());
        Integer integer = drivernametoencodedurlServerImpl.InsertNameCode(drivernametoencodedurl);

        if (integer == 1) {
            return Result.success();
        } else {
            return Result.error("");
        }


    }


    @PostMapping(value = "/GetAllDriverData")   //查询全部的司机信息（不查询）
    public Result<PageBean> GetAllDriverData(Integer pageNum,
                                             Integer pageSize) {
        // List<DriverRanking> driverRankings = cockpitServerImpl.GetAllDriverRanking();
        /*  GetAllDriverData  */
      /*  PageBean<Drivernametoencodedurl> l = driverManagementServer.GetAllDriverData(1,10);
        return Result.success();*/
        PageBean<Drivernametoencodedurl> driverRankingPageBean = driverManagementServer.GetAllDriverData(pageNum, pageSize);//evaluationrecordsServerImpl.GetAllDriverRankingPaging(pageNum, pageSize);
        System.out.println("driverRankingPageBean =>");
        System.out.println(driverRankingPageBean);
        return Result.success(driverRankingPageBean);
    }


    @Autowired
    private DrivernametoencodedurlServerImpl drivernametoencodedurlServer;

    @PostMapping(value = "/DriverNameQuery")   //查询全部的司机信息（不查询）
    public Result<PageBean> DriverNameQuery(Integer pageNum, String DriverName,
                                            Integer pageSize) {

        DriverName = DriverName.trim();
        // List<DriverRanking> driverRankings = cockpitServerImpl.GetAllDriverRanking();
        /*  GetAllDriverData  */
      /*  PageBean<Drivernametoencodedurl> l = driverManagementServer.GetAllDriverData(1,10);
        return Result.success();*/
        PageBean<Drivernametoencodedurl> drivernametoencodedurlPageBean = drivernametoencodedurlServer.FindDriverNameByDriverData(DriverName, pageNum,
                pageSize);
/*
        PageBean<Drivernametoencodedurl> driverRankingPageBean = driverManagementServer.FindDriverNameByDriverData(pageNum,pageSize);//evaluationrecordsServerImpl.GetAllDriverRankingPaging(pageNum, pageSize);
*/
        System.out.println("drivernametoencodedurlPageBean =>");
        System.out.println(drivernametoencodedurlPageBean);


        return Result.success(drivernametoencodedurlPageBean);
    }

    @PostMapping(value = "/UPDATEDrivernameFindByID")   //查询全部的司机信息（不查询）
    public Result UPDATEDrivernameFindByID(Drivernametoencodedurl drivernametoencodedurl) {
        // List<DriverRanking> driverRankings = cockpitServerImpl.GetAllDriverRanking();
        /*  GetAllDriverData  */
      /*  PageBean<Drivernametoencodedurl> l = driverManagementServer.GetAllDriverData(1,10);
        return Result.success();*/
       /* PageBean<Drivernametoencodedurl> driverRankingPageBean = driverManagementServer.GetAllDriverData(pageNum, pageSize);//evaluationrecordsServerImpl.GetAllDriverRankingPaging(pageNum, pageSize);
        System.out.println("driverRankingPageBean =>");
        System.out.println(driverRankingPageBean);*/

       /* System.out.println("drivernametoencodedurl =>");
        System.out.println(drivernametoencodedurl);

        System.out.println("drivernametoencodedurl =>");
        System.out.println(drivernametoencodedurl.getID());
        System.out.println(drivernametoencodedurl.getID().getClass());
        System.out.println("drivernametoencodedurl =>");
        System.out.println(drivernametoencodedurl.getDRIVERNAME());
        System.out.println(drivernametoencodedurl.getDRIVERNAME().getClass());

        */




        Drivernametoencodedurl drivernametoencodedurl1 = new Drivernametoencodedurl();
        drivernametoencodedurl1.setID(drivernametoencodedurl.getID());
        drivernametoencodedurl1.setDRIVERNAME(drivernametoencodedurl.getDRIVERNAME());
        Integer integer1 = drivernametoencodedurlServerImpl.UPDATEDrivernameFindByID(drivernametoencodedurl1);


        if (integer1 == 1) {
            return Result.success();
        } else {
            return Result.error("");
        }
    }

}
