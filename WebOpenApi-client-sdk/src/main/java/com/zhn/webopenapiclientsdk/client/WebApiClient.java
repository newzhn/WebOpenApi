package com.zhn.webopenapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zhn.webopenapiclientsdk.model.User;

import java.util.HashMap;
import java.util.Map;

import static com.zhn.webopenapiclientsdk.utils.SignUtil.genSign;

/**
 * 调用第三方接口的客户端
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/1 14:15
 * @blog www.zhnblog.icu
 */
public class WebApiClient {
    private final String accessKey;
    private final String secretKey;

    public WebApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.get("http://localhost:9999/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result= HttpUtil.post("http://localhost:9999/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPostJson(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse response = HttpRequest.post("http://localhost:9999/api/name/json")
                .addHeaders(getHeaderMap(json))
                .contentType("application/json;charset=UTF-8")
                .body(json)
                .execute();
        String result = response.body();
        System.out.println(result);
        return result;
    }

    private Map<String,String> getHeaderMap(String body) {
        HashMap<String, String> map = new HashMap<>();
        map.put("accessKey",accessKey);
        //随机数
        map.put("nonce", RandomUtil.randomNumbers(100));
        //时间戳
        map.put("timestamp",String.valueOf(System.currentTimeMillis() / 1000));
        //请求传参
        map.put("body",body);
        //签名认证
        map.put("sign",genSign(body,secretKey));
        return map;
    }
}
