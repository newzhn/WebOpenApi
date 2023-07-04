package com.zhn.webopenapibackend.model.request.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "接口名不能为空")
    private String name;

    /**
     * 接口描述
     */
    @NotBlank(message = "接口描述不能为空")
    private String description;

    /**
     * 接口类型
     */
    @NotBlank(message = "接口类型不能为空")
    private String method;

    /**
     * 接口地址
     */
    @NotBlank(message = "接口地址不能为空")
    private String url;

    /**
     * 请求参数
     */
    @NotBlank(message = "接口请求参数不能为空")
    private String requestParams;

    /**
     * 请求头
     */
    @NotBlank(message = "请求头不能为空")
    private String requestHeader;

    /**
     * 响应头
     */
    @NotBlank(message = "响应头不能为空")
    private String responseHeader;

    private static final long serialVersionUID = 1L;
}
