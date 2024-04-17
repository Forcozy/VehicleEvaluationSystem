package com.fffff.vehicleevaluationsystem.Controller;
//驾驶舱

import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.Result;
import com.fffff.vehicleevaluationsystem.Pojo.User;
import com.fffff.vehicleevaluationsystem.Server.Impl.CockpitServerImpl;
import com.fffff.vehicleevaluationsystem.Server.Impl.EvaluationrecordsServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CockpitController {

    @Autowired
    private CockpitServerImpl cockpitServerImpl;
    @Autowired
    private EvaluationrecordsServerImpl evaluationrecordsServerImpl;

    //0408yyd
    @PostMapping(value = "/GetAllDriverRanking")
    public Result<List<DriverRanking>> GetAllDriverRanking() {
        List<DriverRanking> driverRankings = cockpitServerImpl.GetAllDriverRanking();
        return Result.success(driverRankings);
    }

    //List<Evaluationrecords> QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth();

    @PostMapping(value = "/QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth")
    public Result<List<User>> QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth() {// 查询上月每天的总评价数
        List<User> users = evaluationrecordsServerImpl.QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth();


        return Result.success(users);
    }

    /*  List<User> QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
      List<User> QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
  */
    @PostMapping(value = "/QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth")
    public Result<Integer> QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth() {// 查询上月某天内的最大评价数(日历图)
        List<User> users = evaluationrecordsServerImpl.QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
        if (users.isEmpty()) {
            return Result.success(0);
        } else {
            return Result.success(Integer.parseInt(users.get(0).getPassword1()));
        }


    }

   /* @PostMapping(value = "/QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth")
    public Result<Integer> QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth() {// 查询上月某天内的最大评价数(日历图)
        List<User> users = evaluationrecordsServerImpl.QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth();
        if (users.isEmpty()) {
            return Result.success(0);
        } else {
            return Result.success(Integer.parseInt(users.get(0).getPassword1()));
        }


    }
*/
/*
    @PostMapping(value = "/QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth")
    public Result<Integer> QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth() {// 查询上月某天内的最大评价数(日历图)
        List<User> users = evaluationrecordsServerImpl.QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth();


        return Result.success(users);
    }
*/


    //0408yyd
    @PostMapping(value = "/GetAllNumberOfOccurrencesOfEvaluation1")
    public Result<List<DriverRanking>> GetAllNumberOfOccurrencesOfEvaluation1() {    //获取 评价表的评价信息1的  4个选项的出现次数
        List<DriverRanking> driverRankings = cockpitServerImpl.GetAllNumberOfOccurrencesOfEvaluation1();
        System.out.println(driverRankings.size());

        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            if (driverRankings.get(i).getName().equals("1")) {
                driverRankings.get(i).setName("不及格");
            } else if (driverRankings.get(i).getName().equals("2")) {
                driverRankings.get(i).setName("及格");
            } else if (driverRankings.get(i).getName().equals("3")) {
                driverRankings.get(i).setName("良好");
            } else if (driverRankings.get(i).getName().equals("4")) {
                driverRankings.get(i).setName("优秀");
            }
        }
        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            System.out.println(driverRankings.get(i));
        }
        return Result.success(driverRankings);
    }


    //0408yyd
    @PostMapping(value = "/GetAllNumberOfOccurrencesOfEvaluation2")
    public Result<List<DriverRanking>> GetAllNumberOfOccurrencesOfEvaluation2() {    //获取 评价表的评价信息2的  4个选项的出现次数
        List<DriverRanking> driverRankings = cockpitServerImpl.GetAllNumberOfOccurrencesOfEvaluation2();
        System.out.println(driverRankings.size());

        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            if (driverRankings.get(i).getName().equals("1")) {
                driverRankings.get(i).setName("不及格");
            } else if (driverRankings.get(i).getName().equals("2")) {
                driverRankings.get(i).setName("及格");
            } else if (driverRankings.get(i).getName().equals("3")) {
                driverRankings.get(i).setName("良好");
            } else if (driverRankings.get(i).getName().equals("4")) {
                driverRankings.get(i).setName("优秀");
            }
        }
        for (int i = 0; i < driverRankings.size(); i++) {  //替换掉
            System.out.println(driverRankings.get(i));
        }
        return Result.success(driverRankings);
    }

    //0408yyd
    @PostMapping(value = "/MonthlySubmissionTimes")
    public Result<List<DriverRanking>> MonthlySubmissionTimes() {    //获取
        List<DriverRanking> driverRankings = cockpitServerImpl.MonthlySubmissionTimes();
        return Result.success(driverRankings);
    }

    //0408yyd
    @PostMapping(value = "/TotalDataInThePastWeek")
    public Result<List<DriverRanking>> TotalDataInThePastWeek() {     // 统计近七天内每天提交的数据总数量
        List<DriverRanking> driverRankings = cockpitServerImpl.TotalDataInThePastWeek();
        System.out.println(driverRankings);
        System.out.println(driverRankings.size());
        for (int i = 0; i < driverRankings.size(); i++) {
            System.out.println(driverRankings.get(i));

        }
        return Result.success(driverRankings);
    }


    //0408yyd
    @PostMapping(value = "/NumberOfDriversEvaluatedToday")
    @ResponseBody
    public Result<Integer> NumberOfDriversEvaluatedToday() {  // 查询今日所有评价数据的总数(第四行)
        Integer integer = evaluationrecordsServerImpl.QueryAllEvaluationDataForTodayAllNum();
        System.out.println("// 查询今日所有评价数据的总数(第四行) ==>");
        System.out.println(integer);
        return Result.success(integer);
    }

    @PostMapping(value = "/NumberOfEvaluationsSubmittedInTheCurrentMonth")
    @ResponseBody
    public Result<Integer> NumberOfEvaluationsSubmittedInTheCurrentMonth() {   //本月提交评价数
        Integer integer = cockpitServerImpl.NumberOfEvaluationsSubmittedInTheCurrentMonth();
        System.out.println("// 查询今日所有评价数据的总数(第四行) ==>");
        System.out.println(integer);
        return Result.success(integer);
    }

    @PostMapping(value = "/TotalNumberOfEvaluationsThisYear")
    @ResponseBody
    public Result<Integer> TotalNumberOfEvaluationsThisYear() {   //本月提交评价数
        Integer integer = cockpitServerImpl.TotalNumberOfEvaluationsThisYear();
        System.out.println("// 查询今日所有评价数据的总数(第四行) ==>");
        System.out.println(integer);
        return Result.success(integer);
    }
    @PostMapping(value = "/TotalNumberOfEvaluationsInThePastMonth")
    @ResponseBody
    public Result<Integer> TotalNumberOfEvaluationsInThePastMonth() {   //过去一个月评价总数
        Integer integer = cockpitServerImpl.TotalNumberOfEvaluationsInThePastMonth();
        System.out.println("// 查询今日所有评价数据的总数(第四行) ==>");
        System.out.println(integer);
        return Result.success(integer);
    }


    //  Integer TotalNumberOfReviewsInThePast7Days();//过去7天评价总数:
    @PostMapping(value = "/TotalNumberOfReviewsInThePast7Days")
    @ResponseBody
    public Result<Integer> TotalNumberOfReviewsInThePast7Days() {   //过去一个月评价总数
        Integer integer = cockpitServerImpl.TotalNumberOfReviewsInThePast7Days();
        System.out.println("// 查询今日所有评价数据的总数(第四行) ==>");
        System.out.println(integer);
        return Result.success(integer);
    }


    @PostMapping(value = "/NumberOfEvaluatedDriversThisMonth")
    @ResponseBody
    public Result<Integer> NumberOfEvaluatedDriversThisMonth() {  // 本月被评价司机数
        Integer integer = cockpitServerImpl.NumberOfEvaluatedDriversThisMonth();
        System.out.println("// 本月被评价司机数 ==>");
        System.out.println(integer);
        return Result.success(integer);
    }


    @PostMapping(value = "/NumberOfDriversEvaluatedTodayss")
    @ResponseBody
    public Result<Integer> NumberOfDriversEvaluatedTodayss() {  // public Integer NumberOfDriversEvaluatedToday() {  //今日被评价司机数:
        Integer integer = evaluationrecordsServerImpl.NumberOfDriversEvaluatedToday();
        System.out.println(integer);
        return Result.success(integer);
    }



}
