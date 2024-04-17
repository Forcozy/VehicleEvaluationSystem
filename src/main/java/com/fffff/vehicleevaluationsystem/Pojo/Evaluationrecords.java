package com.fffff.vehicleevaluationsystem.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evaluationrecords {    //评价记录表
    private Integer ID;//主键ID
    @JsonIgnore //转json忽略
    private String UUID;//司机ID
    private String DRIVERNAME;//司机姓名
    private Date EvaluationTime;//评价时间
    private Integer EVALUATIONINDICATOR1;//评价指标1
    private Integer EVALUATIONINDICATOR2;//评价指标2
    private String CONTACTNUMBEROFEVALUATOR;//评价人联系电话
}
