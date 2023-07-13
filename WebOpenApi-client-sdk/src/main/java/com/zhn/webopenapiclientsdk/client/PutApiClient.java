package com.zhn.webopenapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/13 17:35
 * @blog www.zhnblog.icu
 */
public class PutApiClient extends DefaultAbstractApiClient {
    public PutApiClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public String invoke(String uri, Map<String, Object> paramMap) {
        String url = GATEWAY_HOST + uri;
        String body = JSONUtil.toJsonStr(paramMap);
        return HttpRequest.put(url)
                .header("uri",uri)
                .addHeaders(getApiHeaderMap())
                .contentType("application/json;charset=UTF-8")
                .body(body)
                .timeout(50000)
                .execute().body();
    }
}
