package com.zhn.webopenapiclientsdk.client;

import cn.hutool.http.HttpRequest;
import com.zhn.webopenapiclientsdk.utils.RestfulUtil;

import java.util.Map;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/13 17:38
 * @blog www.zhnblog.icu
 */
public class DeleteApiClient extends DefaultAbstractApiClient {
    public DeleteApiClient(String accessKey, String secretKey, String gatewayHost) {
        super(accessKey, secretKey, gatewayHost);
    }

    @Override
    public String invoke(String uri, Map<String, Object> paramMap) {
        //判断是否是restful风格的请求，请求不同，参数拼接方式也不一样
        String tempUri = uri;
        uri = RestfulUtil.buildUri(uri,paramMap);
        String url = gatewayHost + uri;
        return HttpRequest.delete(url)
                .header("uri",tempUri)
                .addHeaders(getApiHeaderMap())
                .contentType("application/json;charset=UTF-8")
                .timeout(50000)
                .execute().body();
    }
}
