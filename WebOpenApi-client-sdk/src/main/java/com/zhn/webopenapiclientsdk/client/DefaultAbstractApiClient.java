package com.zhn.webopenapiclientsdk.client;

import java.util.HashMap;
import java.util.Map;

import static com.zhn.webopenapiclientsdk.utils.SignUtil.genSign;

/**
 * 默认的抽象客户端类
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 16:52
 * @blog www.zhnblog.icu
 */
public abstract class DefaultAbstractApiClient implements ApiClient {
    protected static final String GATEWAY_HOST = "http://127.0.0.1:8849";

    private final String accessKey;

    private final String secretKey;

    public DefaultAbstractApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    protected Map<String,String> getApiHeaderMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("accessKey",accessKey);
        //时间戳
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        map.put("timestamp",timestamp);
        //签名认证
        map.put("sign",genSign(timestamp,secretKey));
        return map;
    }
}
