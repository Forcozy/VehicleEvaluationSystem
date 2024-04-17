package com.fffff.vehicleevaluationsystem.Mapper;

import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CockpitMapper { //数据分析
    @Select("select DRIVERNAME as name, COUNT(*) as value from EVALUATIONRECORDS T group by T.DRIVERNAME order by COUNT(*) DESC\n")
    List<DriverRanking> GetAllDriverRanking(); //获取评价表的全部值   从高到低排序   显示排名

    @Select("select DRIVERNAME as name, COUNT(*) as value \n" +
            "from EVALUATIONRECORDS T \n" +
            "WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM')\n" +
            "group by T.DRIVERNAME \n" +
            "order by COUNT(*) DESC")
    List<DriverRanking> GetAllDriverRankingthismonth(); //获取评价表的本月全部值   从高到低排序   显示排名


    @Select("select DRIVERNAME as name, COUNT(*) as value \n" +
            "from EVALUATIONRECORDS T \n" +
            "WHERE TRUNC(EVALUATIONTIME) = TRUNC(SYSDATE)\n" +
            "group by T.DRIVERNAME \n" +
            "order by COUNT(*) DESC\n")
    List<DriverRanking> GetAllDriverRankingToday(); //获取评价表的今日全部值   从高到低排序   显示排名


    @Select("SELECT E.EVALUATIONINDICATOR1 as name, COUNT(*) AS value\n" +
            "FROM EVALUATIONRECORDS E\n" +
            "GROUP BY E.EVALUATIONINDICATOR1")
    List<DriverRanking> GetAllNumberOfOccurrencesOfEvaluation1(); //获取 评价表的评价信息1的  4个选项的出现次数


    @Select("SELECT E.EVALUATIONINDICATOR2 as name, COUNT(*) AS value\n" +
            "FROM EVALUATIONRECORDS E\n" +
            "GROUP BY E.EVALUATIONINDICATOR2")
    List<DriverRanking> GetAllNumberOfOccurrencesOfEvaluation2(); //获取 评价表的评价信息2的  4个选项的出现次数


    @Select("SELECT\n" +
            "  TO_CHAR(EVALUATIONRECORDS.EVALUATIONTIME, 'YYYY-MM') AS name,\n" +
            "  COUNT(*) AS value\n" +
            "FROM\n" +
            "  EVALUATIONRECORDS\n" +
            "GROUP BY\n" +
            "  TO_CHAR(EVALUATIONRECORDS.EVALUATIONTIME, 'YYYY-MM')\n" +
            "ORDER BY\n" +
            "  name")
    List<DriverRanking> MonthlySubmissionTimes(); //获取 每月提交次数


    @Select("select  count(DISTINCT t.drivername )\n" +
            "from EVALUATIONRECORDS t\n" +
            "WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM')")
    Integer NumberOfEvaluatedDriversThisMonth();//本月被评价司机数


    @Select("select  count(*)\n" +
            "from EVALUATIONRECORDS t\n" +
            "WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM')")
    Integer NumberOfEvaluationsSubmittedInTheCurrentMonth();//本月提交评价数


    @Select("select count(*)\n" +
            "    from EVALUATIONRECORDS t\n" +
            "    WHERE TO_CHAR(EVALUATIONTIME, 'YYYY') = TO_CHAR(SYSDATE, 'YYYY')")
    Integer TotalNumberOfEvaluationsThisYear();//本年评价总数


    @Select("select count(*)\n" +
            "    from EVALUATIONRECORDS t\n" +
            "    WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'YYYY-MM')")
    Integer TotalNumberOfEvaluationsInThePastMonth();//本年评价总数



    @Select("SELECT COUNT(*) \n" +
            "FROM EVALUATIONRECORDS E\n" +
            "WHERE E.EVALUATIONTIME >= SYSDATE - 7\n")
    Integer TotalNumberOfReviewsInThePast7Days();//过去7天评价总数:




    @Select("SELECT\n" +
            "TO_CHAR(E.EVALUATIONTIME, 'MM-DD') AS name,\n" +
            "COUNT(*) AS value \n" +
            "FROM\n" +
            "EVALUATIONRECORDS E\n" +
            "WHERE\n" +
            "E.EVALUATIONTIME >= SYSDATE - 7\n" +
            "GROUP BY\n" +
            "TO_CHAR(E.EVALUATIONTIME, 'MM-DD')\n" +
            "ORDER BY\n" +
            "name")
    List<DriverRanking> TotalDataInThePastWeek(); // 统计近七天内每天提交的数据总数量


}
