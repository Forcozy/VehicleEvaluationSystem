package com.fffff.vehicleevaluationsystem.Server;

import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.PageBean;
import com.fffff.vehicleevaluationsystem.Pojo.User;
import org.apache.ibatis.ognl.Evaluation;

import java.util.List;

public interface EvaluationrecordsServer {
    Integer EvaluationRecordQRCodeGeneration();  //评价记录二维码生成

    PageBean<Evaluationrecords> TimePeriodQuery(String startDate, String endDate, int pageNum, int pageSize);// 按照某一个时间段查询数据

    Integer NumberOfDriversEvaluatedToday(); // 今日被评价司机数(驾驶舱，文本信息)

    Integer QueryAllEvaluationDataForTodayAllNum(); // 查询今日所有评价数据

    List<User> QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth(); // 查询上月每天的总评价数

    Integer CreateNewCord(Evaluationrecords evaluationrecords);//插入新评价记录

    List<Evaluationrecords> GetAllData();//打印全部数据

    Integer GetAllDataAllNum();//打印全部数据的数量

    PageBean<Evaluationrecords> findAllByPage(int pageNum, int pageSize);

    /*
        List<Evaluationrecords> PaginationOfEvaluationRecords(Integer begin, Integer end); //根据起止ID值返回评价记录的分页查找结果
    */

    List<Evaluationrecords> GetPagingEVALUATIONRECORDS(int pageNum, int pageSize);//分页查询评价表

    PageBean<Evaluationrecords> QueryAllEvaluationDataForToday(int pageNum, int pageSize);  // 查询今日所有评价数据(加分页)

    PageBean<Evaluationrecords> QueryAllEvaluationDataForThisMonth(int pageNum, int pageSize); // 查询本月所有评价数据

    PageBean<DriverRanking> GetAllDriverRankingPaging(int pageNum, int pageSize); //获取评价表的全部值   从高到低排序   显示排名

    PageBean<DriverRanking> GetAllDriverRankingthismonth(int pageNum, int pageSize); //获取评价表的全部值   从高到低排序   显示排名

    PageBean<DriverRanking> GetAllDriverRankingToday(int pageNum, int pageSize); //获取评价表的全部值   从高到低排序   显示排名

    PageBean<Evaluationrecords> QueryAllDirvernameData(String Dirvername, int pageNum, int pageSize); // 查询某一个司机的全部评价数据

    PageBean<Evaluationrecords> QueryTodayDirvernameData(String Dirvername, int pageNum, int pageSize);  // 查询某一个司机的今天全部评价数据

    PageBean<Evaluationrecords> QueryThisMonthDirvernameData(String Dirvername, int pageNum, int pageSize);  // 查询某一个司机的本月全部评价数据

    List<User> QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth(); // 查询上月某天内的最大评价数(日历图)

    List<User> QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth(); // 查询上月某天内的最大评价数(日历图)

}
