package com.zhn.webopenapicore.model.request.api;

import com.zhn.webopenapicore.model.vo.api.RequestParamsRemarkVO;
import com.zhn.webopenapicore.model.vo.api.ResponseParamsRemarkVo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * 添加请求
 *
 * @author zhn
 * @version 1.0
 */
@Data
public class InterfaceAddRequest implements Serializable {
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
    private String host;

    /**
     * 接口路径
     */
    @NotBlank(message = "接口路径不能为空")
    private String uri;

    /**
     * 接口申请时的可调用次数
     */
    private Integer applyNum;

    /**
     * 请求参数
     */
    @NotBlank(message = "接口请求参数不能为空")
    private String requestParams;

    /**
     * 请求参数说明
     */
    private List<RequestParamsRemarkVO> requestParamsRemark;

    /**
     * 响应参数说明
     */
    private List<ResponseParamsRemarkVo> responseParamsRemark;

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
