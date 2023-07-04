package com.zhn.webopenapibackend.model.vo;

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
public class InterfaceInfoVo {
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
     * 接口地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 接口状态（0-关闭，1-开启）
     */
    private Integer status;

    /**
     * 创建者Id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
