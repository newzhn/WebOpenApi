package com.zhn.webopenapibackend.constant;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.HOURS;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/6/29 12:35
 * @blog www.zhnblog.icu
 */
public enum CacheConstant {
    /**
     * 缓存key常量
     */
    NULL_DATA("",60L,SECONDS),
    REGISTER_CODE("web_open_api:user_register:code:",3L,MINUTES),
    USER_LOGIN("web_open_api:user_login:",60L,MINUTES);

    /**
     * key前缀
     */
    private final String keyPrefix;

    /**
     * 过期时间
     */
    private final Long ttl;

    /**
     * 时间单位
     */
    private final TimeUnit unit;

    CacheConstant(String keyPrefix, Long ttl, TimeUnit unit) {
        this.keyPrefix = keyPrefix;
        this.ttl = ttl;
        this.unit = unit;
    }

    /**
     * Gets key prefix.
     *
     * @return the key prefix
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Gets ttl.
     *
     * @return the ttl
     */
    public Long getTtl() {
        return ttl;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public TimeUnit getUnit() {
        return unit;
    }
}
