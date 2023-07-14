package com.zhn.webopenapicore.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * 返回给前端的接口信息封装类
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/25 19:32
 * @blog www.zhnblog.icu
 */
@Data
public class InterfaceInfoStoreVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 接口名
     */
    private String name;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口类型
     */
    private String method;

    /**
     * 接口uri
     */
    private String uri;

    /**
     * 接口申请时的可调用次数
     */
    private Integer applyNum;

    /**
     * 是否可申请
     */
    private boolean applyFlag;

    /**
     * 接口状态（0-关闭，1-开启）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

}
