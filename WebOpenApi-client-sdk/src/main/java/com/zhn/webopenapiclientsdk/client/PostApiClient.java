package com.zhn.webopenapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 17:18
 * @blog www.zhnblog.icu
 */
public class PostApiClient extends DefaultAbstractApiClient{
    public PostApiClient(String accessKey, String secretKey, String gatewayHost) {
        super(accessKey, secretKey, gatewayHost);
    }

    @Override
    public String invoke(String uri, Map<String, Object> paramMap) {
        String url = gatewayHost + uri;
        String body = JSONUtil.toJsonStr(paramMap);
        return HttpRequest.post(url)
                .header("uri",uri)
                .addHeaders(getApiHeaderMap())
                .contentType("application/json;charset=UTF-8")
                .body(body)
                .timeout(50000)
                .execute().body();
    }
}
