package com.fffff.vehicleevaluationsystem.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRanking {  //用来传递键值对，echarts用
    private String name;      //驾驶员姓名(主键)
    private Integer value;   //用户记录数
}
