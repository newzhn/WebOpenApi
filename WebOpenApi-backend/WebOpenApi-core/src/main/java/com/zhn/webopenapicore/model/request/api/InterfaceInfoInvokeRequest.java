package com.zhn.webopenapicore.model.request.api;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 接口调用请求
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/4 22:30
 * @blog www.zhnblog.icu
 */
@Data
public class InterfaceInfoInvokeRequest {
    /**
     * 主键
     */
    @NotNull(message = "接口Id调用时不能为空")
    private Long id;

    /**
     * 用户传入接口参数
     */
    @NotBlank(message = "接口参数不能为空")
    private String userRequestParams;
}
