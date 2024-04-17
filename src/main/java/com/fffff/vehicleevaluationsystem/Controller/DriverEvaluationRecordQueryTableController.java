package com.fffff.vehicleevaluationsystem.Controller;
//司机评价记录查询表
import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverEvaluationRecordQueryTableController {

    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServerImpl;


    //0408yyd
    //获取分页好的数据和总数 （查询全部）
    @PostMapping(value = "/QueryAllDirvernameData")
    public Result<PageBean> QueryAllDirvernameData(String name,
                                                   Integer pageNum,
                                                   Integer pageSize) {
        System.out.println("QueryAllDirvernameData ==>");
        // 查询某一个司机的全部评价数据
        System.out.println("name =>");
        System.out.println(name);
        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());
        PageBean<Evaluationrecords> evaluationrecordsPageBean =
                evaluationrecordsServerImpl.QueryAllDirvernameData(name, pageNum, pageSize);
        return Result.success(evaluationrecordsPageBean);
    }

    //0408yyd
    @PostMapping(value = "/QueryTodayDirvernameData")
    public Result<PageBean> QueryTodayDirvernameData(String name,
                                                     Integer pageNum,
                                                     Integer pageSize) {
        System.out.println("QueryAllDirvernameData ==>");
        // 查询某一个司机的全部评价数据
        System.out.println("name =>");
        System.out.println(name);
        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());
        PageBean<Evaluationrecords> evaluationrecordsPageBean =
                evaluationrecordsServerImpl.QueryTodayDirvernameData(name, pageNum, pageSize);
        return Result.success(evaluationrecordsPageBean);
    }

    //0408yyd（x2）
    @PostMapping(value = "/QueryThisMonthDirvernameData")
    public Result<PageBean> QueryThisMonthDirvernameData(String name,
                                                         Integer pageNum,
                                                         Integer pageSize) {
        System.out.println("QueryThisMonthDirvernameData ==>");
// 查询某一个司机的本月全部评价数据
        System.out.println("name =>");
        System.out.println(name);
        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());
        PageBean<Evaluationrecords> evaluationrecordsPageBean =
                evaluationrecordsServerImpl.QueryThisMonthDirvernameData(name, pageNum, pageSize);
        return Result.success(evaluationrecordsPageBean);
    }








}
