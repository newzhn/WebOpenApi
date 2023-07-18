package com.zhn.webopenapiinterface.model;

import com.zhn.webopenapicommon.model.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用结果返回类
 *
 * @author zhn
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class ApiResult {
    /**
     * 结果状态码
     */
    private int code;

    /**
     * 返回内容
     */
    private Object data;

    /**
     * 返回消息
     */
    private String msg;

    public static ApiResult ok(Object data) {
        return ok(data,"");
    }

    public static ApiResult ok(String msg) {
        return ok(null,msg);
    }

    public static ApiResult ok(Object data, String msg) {
        return ok(HttpStatus.SUCCESS,data,msg);
    }

    public static ApiResult ok(int code, Object data, String msg) {
        return new ApiResult(code,data,msg);
    }

    public static ApiResult fail(String msg) {
        return new ApiResult(HttpStatus.ERROR,null,msg);
    }

    public static ApiResult fail(Object data, String msg) {
        return new ApiResult(HttpStatus.ERROR,data,msg);
    }

    public static ApiResult fail(int code, String msg) {
        return new ApiResult(code,null,msg);
    }

    public static ApiResult fail(int code, Object data, String msg) {
        return new ApiResult(code,data,msg);
    }
}
