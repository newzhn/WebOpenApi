package com.zhn.webopenapicommon.utils;

import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;

/**
 * 抛异常工具类
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class ThrowUtil {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param message
     */
    public static void throwIf(boolean condition, String message) {
        throwIf(condition, new BusinessException(HttpStatus.ERROR,message));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param code
     * @param message
     */
    public static void throwIf(boolean condition, int code, String message) {
        throwIf(condition, new BusinessException(code, message));
    }
}
