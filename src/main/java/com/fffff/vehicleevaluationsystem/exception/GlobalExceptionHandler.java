

package com.fffff.vehicleevaluationsystem.exception;


import com.fffff.vehicleevaluationsystem.Pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();//打印到控制台上
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");  //有一些异常对象没有处理信息，有信息就打印，没有就返回操作失败
    }
}


