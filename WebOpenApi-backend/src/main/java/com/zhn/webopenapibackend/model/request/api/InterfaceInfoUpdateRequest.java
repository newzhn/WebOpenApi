package com.zhn.webopenapibackend.model.request.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "接口Id修改时不能为空")
    private Long id;

    /**
     * 接口名
     */
    @NotNull(message = "接口名不能为空")
    private String name;

    /**
     * 接口描述
     */
    @NotNull(message = "接口描述不能为空")
    private String description;

    /**
     * 接口类型
     */
    @NotNull(message = "接口类型不能为空")
    private String method;

    /**
     * 接口地址
     */
    @NotNull(message = "接口地址不能为空")
    private String url;

    /**
     * 请求参数
     */
    @NotBlank(message = "接口请求参数不能为空")
    private String requestParams;

    /**
     * 请求头
     */
    @NotNull(message = "请求头不能为空")
    private String requestHeader;

    /**
     * 响应头
     */
    @NotNull(message = "响应头不能为空")
    private String responseHeader;

    private static final long serialVersionUID = 1L;
}
