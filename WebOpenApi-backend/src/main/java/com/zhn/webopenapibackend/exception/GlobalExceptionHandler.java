package com.zhn.webopenapibackend.exception;

import com.alibaba.druid.wall.violation.ErrorCode;
import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.common.HttpStatus;
import kotlin.Result;
import lombok.extern.slf4j.Slf4j;
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

    @ExceptionHandler(BusinessException.class)
    public AjaxResult businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return AjaxResult.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return AjaxResult.error(HttpStatus.ERROR, "系统错误");
    }
}
