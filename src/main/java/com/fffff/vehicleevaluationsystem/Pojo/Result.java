 package com.fffff.vehicleevaluationsystem.Pojo;


//统一响应结果

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 @NoArgsConstructor    //无参构造方法
 @AllArgsConstructor   //全参构造方法
 @Data  //生成getset方法
public class Result<T> {   //范型T是  不固定数据类型的格式，范型就是你想要设置的数据类型
    private Integer code;//业务状态码  0-成功  1-失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)    有值就把值直接给我，直接将值返回出去
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    //快速返回操作成功响应结果             操作成功但没有值就是一个传话的，添加用户之类的执行成功就用这个
    public static Result success() {
        return new Result(0, "操作成功", null);
    }

     public static Result success(String message) {
         return new Result(0, message, null);
     }

                                    //失败后调用的方法，错误信息原封不动放进去就行
    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
