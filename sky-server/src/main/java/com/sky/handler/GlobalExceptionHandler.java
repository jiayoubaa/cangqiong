package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;


/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex 业务异常
     * @return 错误信息
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }


    /**
     * 捕获SQL异常
     * @param e SQL异常
     * @return 错误信息
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException e){
        log.error("异常信息：{}", e.getMessage());
        if(e.getMessage().contains("Duplicate entry")) {
            String[] s = e.getMessage().split(" ");
            return Result.error(s[2] + MessageConstant.USERNAME_ALREADY_EXISTS );
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}
