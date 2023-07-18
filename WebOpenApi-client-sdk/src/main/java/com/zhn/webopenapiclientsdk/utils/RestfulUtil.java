package com.zhn.webopenapiclientsdk.utils;

import java.util.Map;

/**
 * The type Restful util.
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/12 17:23
 * @blog www.zhnblog.icu
 */
public class RestfulUtil {
    private static final String FLAG1 = "/";
    private static final String FLAG2 = "{";
    private static final String FLAG3 = "}";

    /**
     * 判断是否是RestFul风格的uri路径
     *
     * @param uri the uri
     * @return the boolean
     */
    public static boolean isRestfulPath(String uri) {
        // 以斜杠开头的路径
        if (!uri.startsWith(FLAG1)) {
            return false;
        }
        // 按照斜杠分隔路径
        String[] pathSegments = uri.split(FLAG1);
        // 判断每个路径段是否符合要求
        for (int i = pathSegments.length - 1; i >= 0; i--) {
            String segment = pathSegments[i];
            // 跳过空路径段
            if (segment.isEmpty()) {
                continue;
            }
            // 路径段不符合要求，不是合法的RESTful风格路径
            // 判断路径段是否是动态参数，格式为 {param}
            if (segment.startsWith(FLAG2) && segment.endsWith(FLAG3)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对满足RestFul风格的uri进行参数替换，不满足的进行参数拼接
     *
     * @param uri the uri
     * @param map the map
     * @return the string
     */
    public static String buildUri(String uri, Map<String, Object> map) {
        if (isRestfulPath(uri)) {
            // RESTful 风格的路径
            StringBuilder sb = new StringBuilder();
            // 按照斜杠分隔路径
            String[] pathSegments = uri.split(FLAG1);
            for (String segment : pathSegments) {
                // 跳过空路径段
                if (segment.isEmpty()) {
                    continue;
                }
                Object value = segment;
                if (segment.startsWith(FLAG2) && segment.endsWith(FLAG3)) {
                    String key = segment.substring(1,segment.length() - 1);
                    value = map.get(key);
                    if (value == null) {
                        throw new IllegalArgumentException("Value for placeholder '" + key + "' not found");
                    }
                }
                sb.append(FLAG1).append(value);
            }
            return sb.toString();
        } else {
            // 非 RESTful 风格的路径
            StringBuilder sb = new StringBuilder(uri);
            if (!map.isEmpty()) {
                sb.append("?");
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    sb.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
                }
                // 移除末尾的多余的 "&"
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }
}
