package com.zhn.webopenapicore.model.request.user_api;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加请求
 *
 * @author zhn
 * @version 1.0
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {
    /**
     * 被调用接口Id
     */
    @NotNull(message = "接口Id不能为空")
    @Min(value = 1,message = "接口不存在")
    private Long interfaceInfoId;

    private static final long serialVersionUID = 1L;
}
