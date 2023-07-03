package com.zhn.webopenapiinterface.controller;

import com.zhn.webopenapiclientsdk.model.User;
import com.zhn.webopenapiclientsdk.utils.SignUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/6/29 22:05
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name) {
        return "Get 你的名字是：" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam("name") String name) {
        return "Post 你的名字是：" + name;
    }

    @PostMapping("/json")
    public String getNameByPostJson(@RequestBody User user, HttpServletRequest request) {
        //secretKey不会从前端获取，而是保存在后端配置里，保证安全性
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String body = request.getHeader("body");
        String sign = request.getHeader("sign");
        if (!"zhn".equals(accessKey)) {
            return "认证失败";
        }
        // TODO 可以对请求头中随机数和时间戳进行验证，随机数保证不会重放，时间戳进行定时清除后台保存的随机数

        //进行签名比对
        String genSign = SignUtil.genSign(body, "123");
        if (!genSign.equals(sign)) {
            return "认证失败";
        }
        return user.getName();
    }
}
