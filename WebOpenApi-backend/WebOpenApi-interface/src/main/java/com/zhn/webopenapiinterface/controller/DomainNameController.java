package com.zhn.webopenapiinterface.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhn.webopenapiinterface.constant.RegexConstant;
import com.zhn.webopenapiinterface.model.ApiResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 域名接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/20 22:37
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/domainName")
public class DomainNameController {
    @GetMapping("/isRegistered/{domain}")
    public ApiResult getDomainNameByPost(@PathVariable("domain") String domain) throws Exception {
        //校验域名
        if (StrUtil.isEmpty(domain)) {
            return ApiResult.fail("获取域名注册状态失败，参数domain为空");
        }
        if (RegexConstant.DOMAIN_NAME_REGEX.matches(domain)) {
            return ApiResult.fail("获取域名注册状态失败，参数domain是非法域名");
        }
        String domainUrl = "http://api.btstu.cn/dmreg/api.php";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("domain", domain);
        String result = HttpUtil.get(domainUrl, paramMap);
        Map<String, Object> resultMap;
        try {
            Gson gson = new Gson();
            // 使用Gson库将JSON字符串转换为Map
            resultMap = gson.fromJson(result,
                    new TypeToken<Map<String, Object>>() {}.getType());
        } catch (Exception e) {
            throw new RuntimeException("域名查询结果格式转换失败",e);
        }
        Object msg = resultMap.get("msg");
        resultMap.remove("code");
        resultMap.remove("msg");
        resultMap.remove("domain");
        resultMap.put(domain,msg);
        return ApiResult.ok(resultMap);
    }
}
