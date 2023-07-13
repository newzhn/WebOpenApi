package com.zhn.webopenapiclientsdk.factory;

import com.zhn.webopenapiclientsdk.client.*;
import com.zhn.webopenapiclientsdk.model.HttpMethod;

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

    public ApiClientFactory(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public ApiClient createClient(HttpMethod method) {
        ApiClient client;
        if (method == GET) {
            client = new GetApiClient(accessKey, secretKey);
        } else if (method == POST) {
            client = new PostApiClient(accessKey, secretKey);
        } else if (method == PUT) {
            client = new PutApiClient(accessKey, secretKey);
        } else if (method == DELETE) {
            client = new DeleteApiClient(accessKey, secretKey);
        } else {
            throw new RuntimeException("不支持该类型的客户端创建");
        }
        return client;
    }
}
