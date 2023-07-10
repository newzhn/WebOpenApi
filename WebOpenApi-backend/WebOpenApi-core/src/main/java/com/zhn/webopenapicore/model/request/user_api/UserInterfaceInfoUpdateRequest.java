package com.zhn.webopenapicore.model.request.user_api;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 修改请求
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/25 19:06
 * @blog www.zhnblog.icu
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {
    /**
     * 主键
     */
    @NotNull(message = "Id不能为空")
    @Min(value = 1,message = "接口不存在")
    private Long id;

    /**
     * 总调用次数
     */
    @Min(value = 1,message = "总调用次数不能小于0")
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    @Min(value = 1,message = "剩余调用次数不能小于0")
    private Integer surplusNum;

    /**
     * 状态，0是正常，1是关闭
     */
    @Size(max = 1,message = "接口状态错误")
    private Integer status;

    private static final long serialVersionUID = 1L;
}
