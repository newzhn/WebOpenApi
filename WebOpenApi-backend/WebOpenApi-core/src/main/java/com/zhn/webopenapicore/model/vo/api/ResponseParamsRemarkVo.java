package com.zhn.webopenapicore.model.vo.api;

import lombok.Data;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/17 15:12
 * @blog www.zhnblog.icu
 */
@Data
public class ResponseParamsRemarkVo {
    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 说明
     */
    private String remark;
}
