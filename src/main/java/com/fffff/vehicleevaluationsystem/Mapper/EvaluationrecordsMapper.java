package com.fffff.vehicleevaluationsystem.Mapper;

import com.fffff.vehicleevaluationsystem.Pojo.Evaluationrecords;
import com.fffff.vehicleevaluationsystem.Pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EvaluationrecordsMapper { //评价表


    @Insert("insert into EVALUATIONRECORDS(DRIVERNAME,UUID,EvaluationTime,EVALUATIONINDICATOR1,EVALUATIONINDICATOR2,CONTACTNUMBEROFEVALUATOR)\n" +
            "values (#{DRIVERNAME},#{UUID},sysdate,#{EVALUATIONINDICATOR1},#{EVALUATIONINDICATOR2},#{CONTACTNUMBEROFEVALUATOR})"
    )
    Integer CreateNewCord(Evaluationrecords evaluationrecords);//插入新评价记录


    @Select("select * from EVALUATIONRECORDS  order by ID ")
    List<Evaluationrecords> GetAllData();//打印全部数据

    @Select("select count(*) from EVALUATIONRECORDS  order by ID ")
    Integer GetAllDataAllNum();//打印全部数据的数量


    @Insert("insert into EVALUATIONRECORDS(DRIVERNAME,UUID,EvaluationTime,EVALUATIONINDICATOR1,EVALUATIONINDICATOR2,CONTACTNUMBEROFEVALUATOR)\n" +
            "values (#{DRIVERNAME},#{UUID},#{EvaluationTime},#{EVALUATIONINDICATOR1},#{EVALUATIONINDICATOR2},#{CONTACTNUMBEROFEVALUATOR})"
    )
    Integer CreateNewCord2(Evaluationrecords evaluationrecords);//插入新评价记录(时间自己给的，用来放假数据，测试完成就删除)


    @Select("select * from (\n" +
            "               select a.*, rownum rn\n" +
            "               from (\n" +
            "                     select * from evaluationrecords  \n" +
            "                     ORDER BY ID -- 排序条件，根据需要替换  \n" +
            "                     )a\n" +
            "                where rownum   <=  #{endNum}               --控制结束的行数（包含）\n" +
            "              )\n" +
            " where rn   >=   #{beginNum}                                  --控制开始的行数(包含)\n")
    List<Evaluationrecords> PagingAllData(@Param("beginNum") int beginNum, @Param("endNum") int endNum);


    @Select("SELECT *  \n" +
            "FROM EVALUATIONRECORDS   \n" +
            "WHERE TRUNC(EVALUATIONTIME) = TRUNC(SYSDATE)  \n" +
            "order by EVALUATIONRECORDS.EVALUATIONTIME")
    List<Evaluationrecords> QueryAllEvaluationDataForToday(); // 查询今日所有评价数据

    @Select("SELECT count(*)  \n" +
            "FROM EVALUATIONRECORDS  \n" +
            "WHERE TRUNC(EVALUATIONTIME) = TRUNC(SYSDATE) \n" +
            "order by EVALUATIONRECORDS.EVALUATIONTIME")
    Integer QueryAllEvaluationDataForTodayAllNum(); // 查询今日所有评价数据的总数(x)


    @Select("select  count(DISTINCT t.drivername )\n" +
            "    from EVALUATIONRECORDS t\n" +
            "    WHERE TRUNC(EVALUATIONTIME) = TRUNC(SYSDATE)")
    Integer NumberOfDriversEvaluatedToday(); // 今日被评价司机数(驾驶舱，文本信息)

    @Select("SELECT COUNT(*) as password1, TRUNC(EVALUATIONTIME) as username \n" +
            "FROM EVALUATIONRECORDS\n" +
            "WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'YYYY-MM') \n" +
            "GROUP BY TRUNC(EVALUATIONTIME)\n" +
            "ORDER BY username")
    List<User> QueryTheTotalTNumberOfDailyReviewsForThePreviousMonth(); // 查询上月每天的总评价数(日历图)


    @Select("SELECT COUNT(*) as password1, TRUNC(EVALUATIONTIME) AS username \n" +
            "FROM EVALUATIONRECORDS\n" +
            "WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'YYYY-MM') \n" +
            "GROUP BY TRUNC(EVALUATIONTIME)\n" +
            "ORDER BY COUNT(*) DESC")
    List<User> QueryTheMaximumNumberOfReviewsWithinACertainDayOfThePreviousMonth(); // 查询上月某天内的最大评价数(日历图)

    @Select("SELECT COUNT(*) as password1, TRUNC(EVALUATIONTIME) AS username \n" +
            "FROM EVALUATIONRECORDS\n" +
            "WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(ADD_MONTHS(SYSDATE, -1), 'YYYY-MM') \n" +
            "GROUP BY TRUNC(EVALUATIONTIME)\n" +
            "ORDER BY COUNT(*)")
    List<User> QueryTheMinimumNumberOfReviewsWithinACertainDayOfThePreviousMonth(); // 查询上月某天内的最小评价数(日历图)


    @Select("SELECT *   \n" +
            "FROM EVALUATIONRECORDS   \n" +
            "WHERE TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM')    \n" +
            "order by EVALUATIONRECORDS.EVALUATIONTIME")
    List<Evaluationrecords> QueryAllEvaluationDataForThisMonth(); // 查询本月所有评价数据


    @Select("SELECT *   \n" +
            "FROM EVALUATIONRECORDS   \n" +
            "WHERE EVALUATIONRECORDS.DRIVERNAME = #{Dirvername}\n" +
            "order by EVALUATIONRECORDS.EVALUATIONTIME\n")
    List<Evaluationrecords> QueryAllDirvernameData(String Dirvername);  // 查询某一个司机的全部评价数据

    @Select("SELECT *   \n" +
            "FROM EVALUATIONRECORDS   \n" +
            "WHERE EVALUATIONRECORDS.DRIVERNAME = #{Dirvername} and TRUNC(EVALUATIONTIME) = TRUNC(SYSDATE) \n" +
            "order by EVALUATIONRECORDS.EVALUATIONTIME\n")
    List<Evaluationrecords> QueryTodayDirvernameData(String Dirvername);  // 查询某一个司机的今天全部评价数据

    @Select("SELECT *   \n" +
            "FROM EVALUATIONRECORDS   \n" +
            "WHERE EVALUATIONRECORDS.DRIVERNAME = #{Dirvername}  and TO_CHAR(EVALUATIONTIME, 'YYYY-MM') = TO_CHAR(SYSDATE, 'YYYY-MM') \n" +
            "order by EVALUATIONRECORDS.EVALUATIONTIME\n")
    List<Evaluationrecords> QueryThisMonthDirvernameData(String Dirvername);  // 查询某一个司机的本月全部评价数据

    @Select("SELECT *   \n" +
            "FROM EVALUATIONRECORDS  t   \n" +
            "WHERE t.evaluationtime BETWEEN   \n" +
            "    TO_TIMESTAMP( #{startDate}, 'YYYY-MM-DD HH24:MI:SS') AND   \n" +
            "    TO_TIMESTAMP( #{endDate}, 'YYYY-MM-DD HH24:MI:SS')")
    List<Evaluationrecords> TimePeriodQuery(String startDate,String endDate);  // 按照某一个时间段查询数据




}
