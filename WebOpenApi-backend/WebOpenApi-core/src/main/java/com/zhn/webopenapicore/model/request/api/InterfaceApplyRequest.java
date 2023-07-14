package com.zhn.webopenapicore.model.request.api;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 申请请求
 *
 * @author zhn
 * @version 1.0
 */
@Data
public class InterfaceApplyRequest implements Serializable {
    /**
     * 申请接口Id
     */
    @NotNull(message = "申请接口Id不能为空")
    @Min(value = 1,message = "该接口不存在")
    private Long interfaceInfoId;

    /**
     * 接口申请的次数
     */
    private Integer applyNum;

    private static final long serialVersionUID = 1L;
}
