package com.zhn.webopenapiinterface.controller;

import cn.hutool.http.HttpUtil;
import com.zhn.webopenapiinterface.model.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 文本接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/20 22:42
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/text")
public class TextController {
    @GetMapping("/poisonousChickenSoup")
    public ApiResult getAvatarUrlByPost(@RequestParam(value = "charset",required = false,defaultValue = "utf-8") String charset,
                                        @RequestParam(value = "encode",required = false,defaultValue = "text") String encode) throws Exception {
        String chickenSoupUrl = "http://api.btstu.cn/yan/api.php";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("charset", charset);
        paramMap.put("encode", encode);
        String text = HttpUtil.get(chickenSoupUrl, paramMap);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("text",text);
        return ApiResult.ok(resultMap);
    }
}
