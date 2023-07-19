package com.zhn.webopenapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import com.zhn.webopenapiclientsdk.utils.RestfulUtil;

import java.util.Map;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 17:23
 * @blog www.zhnblog.icu
 */
public class GetApiClient extends DefaultAbstractApiClient {
    public GetApiClient(String accessKey, String secretKey, String gatewayHost) {
        super(accessKey, secretKey, gatewayHost);
    }

    @Override
    public String invoke(String uri, Map<String, Object> paramMap) {
        //判断是否是restful风格的请求，请求不同，参数拼接方式也不一样
        String temp = uri;
        uri = RestfulUtil.buildUri(uri,paramMap);
        String url = gatewayHost + uri;
        return HttpRequest.get(url)
                .header("uri",temp)
                .addHeaders(getApiHeaderMap())
                .contentType("application/json;charset=UTF-8")
                .timeout(50000)
                .execute().body();
    }
}
