package com.zhn.webopenapiinterface.exception;

import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result runtimeExceptionHandler(Exception e) {
        log.error("Exception", e);
        if (e.getCause() instanceof BusinessException) {
            // 如果 cause 是 BusinessException 异常，则抛出该异常
            throw (BusinessException) e.getCause();
        }
        return Result.error(HttpStatus.ERROR, "系统出现错误");
    }

    @ExceptionHandler(BusinessException.class)
    public Result businessExceptionHandler(BusinessException e) {
        log.error("业务逻辑出现异常！{}", e);
        return Result.error(e.getCode(),e.getMessage());
    }

    /** 添加校验参数异常处理 */
    @ExceptionHandler(BindException.class)
    public Result bindExceptionHandler(BindException e) {
        log.error("参数校验出现了异常! {}", e);
        return Result.error(HttpStatus.ERROR,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }
}
