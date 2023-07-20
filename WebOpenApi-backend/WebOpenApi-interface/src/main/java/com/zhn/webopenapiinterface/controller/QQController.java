package com.zhn.webopenapiinterface.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhn.webopenapiinterface.constant.RegexConstant;
import com.zhn.webopenapiinterface.model.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * QQ接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/20 22:40
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/qq")
public class QQController {
    @GetMapping("/isOnline/{qqNumber}")
    public ApiResult getQQIsOnline(@PathVariable("qqNumber") String qqNumber) {
        //校验QQ号
        if (StrUtil.isEmpty(qqNumber) || RegexConstant.QQ_REGEX.matches(qqNumber)) {
            return ApiResult.fail("QQ号格式错误，请重新输入");
        }
        String qqUrl = "http://api.btstu.cn/qqol/api.php";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("qq", qqNumber);
        String result = HttpUtil.get(qqUrl, paramMap);
        Map<String, Object> resultMap;
        try {
            Gson gson = new Gson();
            // 使用Gson库将JSON字符串转换为Map
            resultMap = gson.fromJson(result,
                    new TypeToken<Map<String, Object>>() {}.getType());
        } catch (Exception e) {
            throw new RuntimeException("QQ查询结果格式转换失败",e);
        }
        Object msg = resultMap.get("msg");
        resultMap.remove("code");
        resultMap.remove("msg");
        resultMap.put(qqNumber,msg);
        return ApiResult.ok(resultMap);
    }
}
