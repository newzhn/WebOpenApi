package com.zhn.webopenapibackend.exception;

import com.zhn.webopenapibackend.common.HttpStatus;

/**
 * 自定义异常类
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4998560032471494599L;
    /**
     * 错误码
     */
    private final int code;

    public BusinessException(String message) {
        super(message);
        this.code = HttpStatus.ERROR;
    }

    public BusinessException(String message,Throwable cause) {
        super(message, cause);
        this.code = HttpStatus.ERROR;
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(int code, String message,Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
