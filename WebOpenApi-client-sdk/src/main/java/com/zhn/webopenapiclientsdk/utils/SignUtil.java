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
    public static String genSign(String body, String secretKey) {
        // 进行加密
        String md5 = DigestUtil.md5Hex(body + secretKey);
        return md5;
    }
}
