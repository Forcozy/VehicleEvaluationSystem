package com.fffff.vehicleevaluationsystem.Controller;

import com.fffff.vehicleevaluationsystem.Pojo.Drivernametoencodedurl;
import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Server.Impl.DrivernametoencodedurlServerImpl;
import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QRCodeController {  //扫描二维码对接的

    @Autowired
    private DrivernametoencodedurlServerImpl drivernametoencodedurlServerImpl;
    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServer;

    //0408yyd
    @PostMapping(value = "/getDriverName")
    public Result<String> getDriverName(String UUID) {  //根据UUID查找司机是否有记录
        System.out.println("getDriverName ==>");
        System.out.println("UUID ==>");
        System.out.println(UUID);
        System.out.println(UUID.getClass());

        List<Drivernametoencodedurl> drivernametoencodedurls = drivernametoencodedurlServerImpl.FindDriverByUUID(UUID);
        System.out.println("drivernametoencodedurls.size()  ==>");
        System.out.println(drivernametoencodedurls.size());

        System.out.println();
        if (drivernametoencodedurls.isEmpty()) { //查询结果为空，没有这个人，不能填评价
            return Result.error("查无此人");
        } else if (drivernametoencodedurls.size() > 1) {
            return Result.error("UUID重复");
        } else {
            return Result.success(drivernametoencodedurls.get(0).getDRIVERNAME());
        }
    }

    public static boolean isPositiveIntegerLessThanFive(String str) {
        // 检查字符串是否为空或只包含数字
        if (str == null || !str.matches("\\d+")) {
            return false;
        }

        // 将字符串转换为整数
        int number = Integer.parseInt(str);

        // 检查数值是否在1到4的范围内（包含1和4）
        return number > 0 && number < 5;
    }


    //0408yyd
    @PostMapping(value = "/QRCodeSubmitInformation")
    public Result<String> QRCodeSubmitInformation(/*@RequestBody Evaluationrecords evaluationrecords */
            String uuid,
            String evaluationindicator1,
            String evaluationindicator2,
            String contactnumberofevaluator
    ) {  //提交信息接口
        if (!isPositiveIntegerLessThanFive(evaluationindicator1)) { //评价数值不对时不允许插入
            return Result.error("插入失败，评价信息格式错误");
        }
        if (!isPositiveIntegerLessThanFive(evaluationindicator1)) {
            return Result.error("插入失败，评价信息格式错误");
        }
        System.out.println("QRCodeSubmitInformation ==>");
        System.out.println("uuid =>");
        System.out.println(uuid);
        System.out.println("evaluationindicator1 =>");
        System.out.println(evaluationindicator1);
        System.out.println("evaluationindicator2 =>");
        System.out.println(evaluationindicator2);
        System.out.println("contactnumberofevaluator =>");
        System.out.println(contactnumberofevaluator);

        Evaluationrecords evaluationrecords = new Evaluationrecords();
        evaluationrecords.setUUID(uuid);
        evaluationrecords.setEVALUATIONINDICATOR1(Integer.parseInt(evaluationindicator1));
        evaluationrecords.setEVALUATIONINDICATOR2(Integer.parseInt(evaluationindicator2));
        evaluationrecords.setCONTACTNUMBEROFEVALUATOR(contactnumberofevaluator);

        System.out.println("evaluationrecords.toString() =>");
        System.out.println(evaluationrecords.toString());
        //uuid再去查一遍保证安全，无id返回插入失败，系统无该司机记录
        List<Drivernametoencodedurl> drivernametoencodedurls2 = drivernametoencodedurlServerImpl.FindDriverByUUID(evaluationrecords.getUUID());
        if (drivernametoencodedurls2.isEmpty()) { //查询结果为空，没有这个人，不能填评价
            return Result.error("无该司机信息");
        } else if (drivernametoencodedurls2.size() > 1) {
            return Result.error("UUID重复");
        }
        String submitdrivername = drivernametoencodedurls2.get(0).getDRIVERNAME(); //去司机表内拿司机名
        evaluationrecords.setDRIVERNAME(submitdrivername);
        Integer i = evaluationrecordsServer.CreateNewCord(evaluationrecords);
        System.out.println("i = ");
        System.out.println(i);
        if (i == 1) {
            return Result.success("插入成功");
        } else {
            return Result.error("插入失败");
        }
    }


}
