package com.zhn.webopenapiclientsdk.factory;

import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapiclientsdk.client.*;
import com.zhn.webopenapiclientsdk.model.HttpMethod;
import com.zhn.webopenapiclientsdk.utils.GatewayHostValidator;

import static com.zhn.webopenapiclientsdk.model.HttpMethod.*;

/**
 * api客户端工厂类
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 18:01
 * @blog www.zhnblog.icu
 */
public class ApiClientFactory {
    private final String accessKey;

    private final String secretKey;

    private final String gatewayHost;

    private static final String DEFAULT_GATEWAY_HOST = "https://gateway.openapi.zhnblog.icu";

    public ApiClientFactory(String accessKey, String secretKey) {
        this(accessKey, secretKey, DEFAULT_GATEWAY_HOST);
    }

    public ApiClientFactory(String accessKey, String secretKey, String gatewayHost) {
        if (StrUtil.isBlank(gatewayHost)) {
            gatewayHost = DEFAULT_GATEWAY_HOST;
        }
        if (!GatewayHostValidator.isValidGatewayHost(gatewayHost)) {
            throw new RuntimeException("网关URL不符合规范，创建客户端失败");
        }
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.gatewayHost = gatewayHost;
    }

    public ApiClient createClient(HttpMethod method) {
        ApiClient client;
        if (method == GET) {
            client = new GetApiClient(accessKey, secretKey, gatewayHost);
        } else if (method == POST) {
            client = new PostApiClient(accessKey, secretKey, gatewayHost);
        } else if (method == PUT) {
            client = new PutApiClient(accessKey, secretKey, gatewayHost);
        } else if (method == DELETE) {
            client = new DeleteApiClient(accessKey, secretKey, gatewayHost);
        } else {
            throw new RuntimeException("不支持该类型的客户端创建");
        }
        return client;
    }
}
