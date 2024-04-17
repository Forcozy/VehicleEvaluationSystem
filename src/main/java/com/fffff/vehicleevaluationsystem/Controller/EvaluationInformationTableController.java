package com.fffff.vehicleevaluationsystem.Controller;
//评价信息表
import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationInformationTableController {
    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServerImpl;



    //(0408yyd)
    @PostMapping(value = "/SortingOfEvaluationInformation")
    public Result<PageBean> SortingOfEvaluationInformation(Integer pageNum,
                                                           Integer pageSize) {  //分页获取评价信息表的list

        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());
        PageBean<Evaluationrecords> allByPage = evaluationrecordsServerImpl.findAllByPage(pageNum, pageSize);
        return Result.success(allByPage);
    }

    //0408yyd
    @PostMapping(value = "/QueryAllEvaluationDataForToday")
    public Result<PageBean> QueryAllEvaluationDataForToday(Integer pageNum,
                                                           Integer pageSize) {  // 查询今日所有评价数据(加分页)

        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());
        PageBean<Evaluationrecords> allByPage = evaluationrecordsServerImpl.QueryAllEvaluationDataForToday(pageNum, pageSize);
        return Result.success(allByPage);
    }

    //0408yyd
    @PostMapping(value = "/QueryAllEvaluationDataForThisMonth")
    public Result<PageBean> QueryAllEvaluationDataForThisMonth(Integer pageNum,
                                                               Integer pageSize) {  // 查询本月所有评价数据(加分页)

        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());
        PageBean<Evaluationrecords> allByPage = evaluationrecordsServerImpl.QueryAllEvaluationDataForThisMonth(pageNum, pageSize);
        return Result.success(allByPage);
    }





}
