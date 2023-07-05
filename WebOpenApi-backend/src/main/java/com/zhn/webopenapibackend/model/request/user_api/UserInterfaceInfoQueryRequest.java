package com.zhn.webopenapibackend.model.request.user_api;

import com.zhn.webopenapibackend.model.request.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/25 19:07
 * @blog www.zhnblog.icu
 */
@Data
public class UserInterfaceInfoQueryRequest extends PageRequest implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 调用者Id
     */
    private Long userId;

    /**
     * 被调用接口Id
     */
    private Long interfaceInfoId;

    /**
     * 状态，0是正常，1是关闭
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}
