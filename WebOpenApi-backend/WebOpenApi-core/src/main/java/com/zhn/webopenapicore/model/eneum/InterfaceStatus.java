package com.zhn.webopenapicore.model.eneum;

/**
 * 接口状态枚举类
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/3 19:12
 * @blog www.zhnblog.icu
 */
public enum InterfaceStatus {
    /**
     * 接口具体状态
     */
    ONLINE("上线",1),
    OFFLINE("下线",0),;

    /**
     * 接口状态名
     */
    private final String statusName;

    /**
     * 接口状态值
     */
    private final Integer status;

    InterfaceStatus(String statusName, Integer status) {
        this.statusName = statusName;
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public Integer getStatus() {
        return status;
    }
}
