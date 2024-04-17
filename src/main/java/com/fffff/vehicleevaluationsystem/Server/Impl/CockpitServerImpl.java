package com.fffff.vehicleevaluationsystem.Server.Impl;

import com.fffff.vehicleevaluationsystem.Mapper.CockpitMapper;
import com.fffff.vehicleevaluationsystem.Pojo.DriverRanking;
import com.fffff.vehicleevaluationsystem.Server.CockpitServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CockpitServerImpl implements CockpitServer {
    @Autowired
    private CockpitMapper cockpitMapper;


    @Override
    public Integer TotalNumberOfReviewsInThePast7Days() {
        //  Integer TotalNumberOfReviewsInThePast7Days();//过去7天评价总数:
        return cockpitMapper.TotalNumberOfReviewsInThePast7Days();
    }

    @Override
    public Integer TotalNumberOfEvaluationsInThePastMonth() {
        // Integer NumberOfEvaluatedDriversThisMonthTotalNumberOfEvaluationsInThePastMonth();//本年评价总数
        return cockpitMapper.TotalNumberOfEvaluationsInThePastMonth();
    }

    @Override
    public Integer TotalNumberOfEvaluationsThisYear() {
        return cockpitMapper.TotalNumberOfEvaluationsThisYear();
    }

    @Override
    public Integer NumberOfEvaluationsSubmittedInTheCurrentMonth() {
        return cockpitMapper.NumberOfEvaluationsSubmittedInTheCurrentMonth();
       //本月提交评价数
    }

    @Override
    public Integer NumberOfEvaluatedDriversThisMonth() {
        return cockpitMapper.NumberOfEvaluatedDriversThisMonth();
    }

    @Override
    public List<DriverRanking> GetAllDriverRanking() {
        return cockpitMapper.GetAllDriverRanking();
    }




    @Override
    public List<DriverRanking> GetAllNumberOfOccurrencesOfEvaluation1() {
        return cockpitMapper.GetAllNumberOfOccurrencesOfEvaluation1();
    }

    @Override
    public List<DriverRanking> GetAllNumberOfOccurrencesOfEvaluation2() {
        return cockpitMapper.GetAllNumberOfOccurrencesOfEvaluation2();
    }

    @Override
    public List<DriverRanking> MonthlySubmissionTimes() {
        return cockpitMapper.MonthlySubmissionTimes();
    }

    @Override
    public List<DriverRanking> TotalDataInThePastWeek() {
        return cockpitMapper.TotalDataInThePastWeek();
    }
}
