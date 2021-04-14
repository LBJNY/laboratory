package com.zj.laboratory.advice;


import com.zj.laboratory.enums.ResultEnum;
import com.zj.laboratory.exception.LaboratoryException;
import com.zj.laboratory.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

/**
 * 定义统一异常处理
 *
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    /**
     * 统一处理 BlogException
     *
     * @param exception
     */
    @ExceptionHandler(LaboratoryException.class)
    @ResponseBody
    public Result<Object> exceptionHandler(LaboratoryException exception) {
        log.error("统一异常处理：", exception);
        return new Result<>(exception.getErrorCode(), exception.getMessage());
    }
    @ExceptionHandler(AuthenticationException.class)
    public Result<?> AuthenticationHanlder(AuthenticationException exception){
        log.error("权限不足异常处理",exception);
        return new Result<>(ResultEnum.AUTH_NOT_FOUND);
    }
}

