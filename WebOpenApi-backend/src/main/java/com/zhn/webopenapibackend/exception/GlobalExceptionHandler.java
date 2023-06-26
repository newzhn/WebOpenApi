package com.zhn.webopenapibackend.exception;

import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.common.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
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

    @ExceptionHandler(BusinessException.class)
    public AjaxResult businessExceptionHandler(BusinessException e) {
        log.error("业务逻辑出现异常！{}", e);
        return AjaxResult.error(e.getCode(),e.getMessage());
    }

    /** 添加校验参数异常处理 */
    @ExceptionHandler(BindException.class)
    public AjaxResult bindExceptionHandler(BindException e) {
        log.error("参数校验出现了异常! {}", e);
        return AjaxResult.error(HttpStatus.ERROR,e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 全局异常会比自定义权限处理器先捕获到异常，所以要进行抛出
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        throw e;
    }

    @ExceptionHandler(RuntimeException.class)
    public AjaxResult runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return AjaxResult.error(HttpStatus.ERROR, "系统错误");
    }
}
