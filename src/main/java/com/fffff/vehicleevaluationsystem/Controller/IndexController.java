package com.fffff.vehicleevaluationsystem.Controller;

import com.fffff.vehicleevaluationsystem.Mapper.DrivernametoencodedurlMapper;
import com.fffff.vehicleevaluationsystem.Mapper.EvaluationrecordsMapper;
import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Server.Impl.DrivernametoencodedurlServerImpl;
import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
import com.fffff.vehicleevaluationsystem.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.*;

@RestController
public class IndexController {

    @Autowired
    private DrivernametoencodedurlServerImpl drivernametoencodedurlServer;

    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServerImpl;


    //0408yyd
    @PostMapping(value = "/GetAlldrivernameNum")
    @ResponseBody
    public Result<Integer> GetAlldrivernameNum() {  //获取司机表的总数
        Integer integer = drivernametoencodedurlServer.GetAlldrivernameNum();
        System.out.println("GetAlldrivernameNum ==>");
        System.out.println("integer =>");
        System.out.println(integer);
        return Result.success(integer);
    }


    //0408yyd
    @PostMapping(value = "/GetAllEvaluationNum")
    @ResponseBody
    public Result<Integer> GetAllEvaluationNum() {  //获取评价表的总数
        Integer integer = evaluationrecordsServerImpl.GetAllDataAllNum();

        System.out.println("GetAllEvaluationNum ==>");
        System.out.println("integer =>");
        System.out.println(integer);

        return Result.success(integer);
    }

    //0408yyd
    @PostMapping(value = "/VerifyToken")
    @ResponseBody
    public Result<Boolean> VerifyToken(String token) {//验证token是否有效
        System.out.println("VerifyToken =>");
        System.out.println("token =>");
        System.out.println(token);

        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        System.out.println("username =");
        System.out.println(username);
        return Result.success(true);
    }


    @PostMapping(value = "/tokenGetUserName")
    @ResponseBody
    public String tokenGetUserName(String token) {//token获取用户名
        System.out.println("tokenGetUserName =>");
        System.out.println("token =>");
        System.out.println(token);

        Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        System.out.println("username =");
        System.out.println(username);

        return username;
    }

    @PostMapping("/daterange_EvaluationInformation")
    public Result<PageBean> daterange_EvaluationInformation(@RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize
                                  ) {
        System.out.println("startDate =>");
        System.out.println(startDate);
        System.out.println("endDate =>");
        System.out.println(endDate);
        // 在这里处理startDate和endDate
      //  return "Received date range: " + startDate + " to " + endDate;
        PageBean<Evaluationrecords> evaluationrecordsPageBean = evaluationrecordsServerImpl.TimePeriodQuery(startDate, endDate, pageNum, pageSize);
        return Result.success(evaluationrecordsPageBean);

    }

    @Autowired
    private DrivernametoencodedurlMapper drivernametoencodedurlMapper;
    @Autowired
    private EvaluationrecordsMapper evaluationrecordsMapper;
    @PostMapping("/jsjjk")
    public String daterange_EvaluationInformation() { //假数据接口，测完就删
        System.out.println("gety12131 ==>");
        //1获取司机表信息的uuid，变成一个全部uuid列表
        //2随机选一个uuid，根据业务填充值
        //（读取全部司机表数据，单独取出uuid，变成list，插入评价记录）
        List<Drivernametoencodedurl> l = drivernametoencodedurlMapper.GetAllData(); //获取全部值
        System.out.println("l.size() =>");
        System.out.println(l.size());
        List<String> ppp = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {  //将uuid单独提出来
            ppp.add(l.get(i).getUUID());
        }
        System.out.println("ppp.size() =>");
        System.out.println(ppp.size());

        for (int i = 0; i < 60000; i++) {
            System.out.println(i);

            Random r = new Random();
            int randomNum = r.nextInt(ppp.size());//随机一个uuid
            String Uuid = ppp.get(randomNum);
            //  System.out.println("Uuid =>");
            //  System.out.println(Uuid);
            List<Drivernametoencodedurl> kkk = drivernametoencodedurlMapper.FindDriverByUUID(Uuid);//根据用户UUID找到司机名

            String drivername = kkk.get(0).getDRIVERNAME();//测试内司机一定有就不判断了

            int e1 = r.nextInt(4) + 1;  //随机数1~4
            int e2 = r.nextInt(4) + 1;  //随机数1~4

            String phonenum = "14212345678";

            //随机时间
            // 定义时间段的起始和结束点
            LocalDateTime startDate = LocalDateTime.of(2023, 1, 1, 0, 0); // 2023年1月1日 00:00
            LocalDateTime endDate = LocalDateTime.of(2024, 4, 15, 23, 59); // 2023年12月31日 23:59

            Random rand = new Random();
            long secondsBetween = ChronoUnit.SECONDS.between(startDate, endDate);  // 计算两个日期之间的秒数
            long randomSeconds = rand.nextLong() % secondsBetween;// 生成一个随机秒数
            LocalDateTime randomDateTime = startDate.plusSeconds(randomSeconds);// 将随机秒数加到起始日期上，得到随机时间点
            ZoneId zoneId = ZoneId.systemDefault();// 获取系统默认时区
            ZonedDateTime zonedDateTime = randomDateTime.atZone(zoneId);// 将LocalDateTime转换为ZonedDateTime
            Instant instant = zonedDateTime.toInstant();// 将ZonedDateTime转换为Instant
            Date date = Date.from(instant);// 将Instant转换为Date

            Evaluationrecords e = new Evaluationrecords();
            e.setDRIVERNAME(drivername);
            e.setUUID(Uuid);
            e.setEVALUATIONINDICATOR1(e1);
            e.setEVALUATIONINDICATOR2(e2);
            e.setCONTACTNUMBEROFEVALUATOR(phonenum);
            e.setEvaluationTime(date);
            //  System.out.println(e.toString());
            //  System.out.println("Integer o = evaluationrecordsMapper.CreateNewCord2(e);//插入新评价记录 =>");
            Integer o = evaluationrecordsMapper.CreateNewCord2(e);//插入新评价记录

        }



        return "yes";
    }



}
