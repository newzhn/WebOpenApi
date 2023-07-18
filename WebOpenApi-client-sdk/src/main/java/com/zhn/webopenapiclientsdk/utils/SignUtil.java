package com.zhn.webopenapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * 签名工具类
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/1 21:36
 * @blog www.zhnblog.icu
 */
public class SignUtil {
    /**
     * 获取加密密钥
     * @param timestamp 时间戳
     * @param secretKey secretKey
     * @return sign
     */
    public static String genSign(String timestamp, String secretKey) {
        // 进行加密
        return DigestUtil.md5Hex(timestamp + secretKey);
    }
}
