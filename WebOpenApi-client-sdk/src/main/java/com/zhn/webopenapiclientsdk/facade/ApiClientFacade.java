package com.zhn.webopenapiclientsdk.facade;

import cn.hutool.http.HttpRequest;
import com.zhn.webopenapiclientsdk.client.ApiClient;
import com.zhn.webopenapiclientsdk.factory.ApiClientFactory;
import com.zhn.webopenapiclientsdk.model.HttpMethod;
import javafx.geometry.Pos;

import java.util.HashMap;
import java.util.Map;

import static com.zhn.webopenapiclientsdk.model.HttpMethod.*;

/**
 * api客户端门面类
 * 封装调用逻辑
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 17:49
 * @blog www.zhnblog.icu
 */
public class ApiClientFacade {
    private Map<String, ApiClient> clientMap;

    public ApiClientFacade(String accessKey, String secretKey) {
        //初始化客户端
        ApiClientFactory factory = new ApiClientFactory(accessKey, secretKey);
        ApiClient getClient = factory.createClient(GET);
        ApiClient postClient = factory.createClient(POST);
        this.clientMap = new HashMap<>();
        this.clientMap.put(GET.getValue(),getClient);
        this.clientMap.put(POST.getValue(),postClient);
    }

    public String invoke(String method, String uri, Map<String, Object> paramMap) {
        method = method.toUpperCase();
        ApiClient client = clientMap.get(method);
        return client.invoke(uri, paramMap);
    }
}
