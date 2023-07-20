package com.zhn.webopenapiinterface.controller;

import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhn.webopenapiinterface.model.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 百度接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/20 22:35
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/baidu")
public class BaiDuController {
    @GetMapping("/hot_search")
    public ApiResult getBaiduHotSearch(@RequestParam(value = "size",required = false,defaultValue = "10")
                                                Integer size) {
        //校验size
        if (size <= 0) {
            return ApiResult.fail("获取热搜失败，参数size不符合要求");
        }
        String baiduUrl = "https://www.coderutil.com/api/resou/v1/baidu";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("access-key", "2a73055beafb826cf0aaf0d284d9eede");
        paramMap.put("secret-key", "3fe196bd0a439eef303155b3870b71d5");
        paramMap.put("size", size);
        String result = HttpUtil.get(baiduUrl, paramMap);
        Map<String, Object> resultMap;
        try {
            Gson gson = new Gson();
            // 使用Gson库将JSON字符串转换为Map
            resultMap = gson.fromJson(result,
                    new TypeToken<Map<String, Object>>() {}.getType());
        } catch (Exception e) {
            throw new RuntimeException("百度热搜结果格式转换失败",e);
        }
        Object data = resultMap.get("data");
        return ApiResult.ok(data);
    }
}
