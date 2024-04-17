package com.fffff.vehicleevaluationsystem.Server;

import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;

import java.util.List;

public interface CockpitServer {
    Integer TotalNumberOfReviewsInThePast7Days();//过去7天评价总数:

    Integer TotalNumberOfEvaluationsInThePastMonth();//本年评价总数
    Integer TotalNumberOfEvaluationsThisYear();//本年评价总数
    Integer   NumberOfEvaluationsSubmittedInTheCurrentMonth();//本月提交评价数
    Integer  NumberOfEvaluatedDriversThisMonth();//本月被评价司机数
    List<DriverRanking> GetAllDriverRanking(); //获取全部值
    List<DriverRanking> GetAllNumberOfOccurrencesOfEvaluation1(); //获取 评价表的评价信息1的  4个选项的出现次数
    List<DriverRanking> GetAllNumberOfOccurrencesOfEvaluation2(); //获取 评价表的评价信息2的  4个选项的出现次数
    List<DriverRanking> MonthlySubmissionTimes(); //获取 每月提交次数

    List<DriverRanking> TotalDataInThePastWeek(); // 统计近七天内每天提交的数据总数量
}
