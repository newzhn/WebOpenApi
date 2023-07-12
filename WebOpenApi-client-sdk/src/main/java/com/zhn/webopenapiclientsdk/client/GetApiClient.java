package com.zhn.webopenapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 17:23
 * @blog www.zhnblog.icu
 */
public class GetApiClient extends DefaultAbstractApiClient {
    public GetApiClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    @Override
    public String invoke(String uri, Map<String, Object> paramMap) {
        //判断是否是restful风格的请求，请求不同，参数拼接方式也不一样

        String url = GATEWAY_HOST + uri;
        String body = JSONUtil.toJsonStr(paramMap);
        return HttpRequest.get(url)
                .addHeaders(getHeaderMap(body))
                .contentType("application/json;charset=UTF-8")
                .form(paramMap)
                .timeout(50000)
                .execute().body();
    }
}
