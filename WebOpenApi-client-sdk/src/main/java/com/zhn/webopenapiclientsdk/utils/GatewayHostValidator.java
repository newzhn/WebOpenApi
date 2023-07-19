package com.zhn.webopenapiclientsdk.utils;

import java.util.regex.Pattern;

/**
 * 网关URL路径校验
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 18:01
 * @blog www.zhnblog.icu
 */
public class GatewayHostValidator {

    private static final String HTTP_PATTERN = "^http://([a-zA-Z0-9.-]+(:\\d+)?|\\d+\\.\\d+\\.\\d+\\.\\d+(:\\d+)?)$";
    private static final String HTTPS_PATTERN = "^https://([a-zA-Z0-9.-]+(:\\d+)?|\\d+\\.\\d+\\.\\d+\\.\\d+(:\\d+)?)$";

    public static boolean isValidGatewayHost(String gatewayHost) {
        return Pattern.matches(HTTP_PATTERN, gatewayHost) || Pattern.matches(HTTPS_PATTERN, gatewayHost);
    }
}
