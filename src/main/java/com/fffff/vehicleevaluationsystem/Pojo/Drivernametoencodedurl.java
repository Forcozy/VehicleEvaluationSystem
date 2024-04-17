package com.fffff.vehicleevaluationsystem.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drivernametoencodedurl {
    private Integer ID;//主键ID
    private String DRIVERNAME;//司机姓名
    private Date INDATA;//司机数据插入时间
    @JsonIgnore //转json忽略
    private String UUID;//司机名+时间戳转UUID
}







