package com.fffff.vehicleevaluationsystem.Controller;
//评价排名表
import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationRankingTableController {
    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServerImpl;

    //0408yyd
    @PostMapping(value = "/GetAllDriverRankingPaging")  //获取评价表的全部排名   从高到低排序
    public Result<PageBean> GetAllDriverRankingPaging(Integer pageNum,
                                                      Integer pageSize) {  // 查询本月所有评价数据(加分页)
        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());

        PageBean<DriverRanking> driverRankingPageBean = evaluationrecordsServerImpl.GetAllDriverRankingPaging(pageNum, pageSize);
        return Result.success(driverRankingPageBean);
    }


    //0408yyd
    @PostMapping(value = "/GetAllDriverRankingToday")  //获取评价表的本月全部值   从高到低排序   显示排名
    public Result<PageBean> GetAllDriverRankingToday(Integer pageNum,
                                                     Integer pageSize) {  // 查询本月所有评价数据(加分页)
        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());

        PageBean<DriverRanking> driverRankingPageBean = evaluationrecordsServerImpl.GetAllDriverRankingToday(pageNum, pageSize);
        return Result.success(driverRankingPageBean);
    }




    //0408yyd
    @PostMapping(value = "/GetAllDriverRankingthismonth")  //获取评价表的本月全部值   从高到低排序   显示排名
    public Result<PageBean> GetAllDriverRankingthismonth(Integer pageNum,
                                                         Integer pageSize) {  // 查询本月所有评价数据(加分页)
        System.out.println("pageNum =>");
        System.out.println(pageNum);
        System.out.println(pageNum.getClass());
        System.out.println("pageSize =>");
        System.out.println(pageSize);
        System.out.println(pageSize.getClass());

        PageBean<DriverRanking> driverRankingPageBean = evaluationrecordsServerImpl.GetAllDriverRankingthismonth(pageNum, pageSize);
        return Result.success(driverRankingPageBean);
    }






}
