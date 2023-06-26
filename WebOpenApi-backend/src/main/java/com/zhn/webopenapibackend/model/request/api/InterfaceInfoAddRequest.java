package com.zhn.webopenapibackend.model.request.api;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户添加请求
 *
 * @author zhn
 * @version 1.0
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {
    /**
     * 接口名
     */
    private String name;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口类型
     */
    private String method;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    private static final long serialVersionUID = 1L;
}
