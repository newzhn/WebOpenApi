package com.zhn.webopenapiclientsdk.model;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 17:53
 * @blog www.zhnblog.icu
 */
public enum HttpMethod {
    /**
     * 请求类型
     */
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    /**
     * 请求类型值
     */
    private String value;

    public String getValue() {
        return value;
    }

    HttpMethod(String value) {
        this.value = value;
    }
}
