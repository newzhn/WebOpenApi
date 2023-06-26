package com.zhn.webopenapibackend.model.request.api;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户修改请求
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/25 19:06
 * @blog www.zhnblog.icu
 */
@Data
public class InterfaceInfoUpdateRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

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

    /**
     * 接口状态（0-关闭，1-开启）
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}
