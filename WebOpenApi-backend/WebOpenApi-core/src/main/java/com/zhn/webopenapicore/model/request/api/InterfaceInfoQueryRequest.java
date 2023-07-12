package com.zhn.webopenapicore.model.request.api;

import com.zhn.webopenapicore.model.request.PageRequest;
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
public class InterfaceInfoQueryRequest extends PageRequest implements Serializable {
    /**
     * 接口名
     */
    private String name;

    /**
     * 接口类型
     */
    private String method;

    /**
     * 接口路径
     */
    private String uri;

    /**
     * 接口状态（0-关闭，1-开启）
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}
