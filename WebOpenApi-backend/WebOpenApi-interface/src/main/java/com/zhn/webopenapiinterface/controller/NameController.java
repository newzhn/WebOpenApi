package com.zhn.webopenapiinterface.controller;

import com.zhn.webopenapiclientsdk.model.User;
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
    @GetMapping("/get")
    public String getNameByGet(String name) {
        return "Get 你的名字是：" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam("name") String name) {
        return "Post 你的名字是：" + name;
    }

    @PostMapping("/json")
    public String getNameByPostJson(@RequestBody User user, HttpServletRequest request) {
        return user.getName();
    }
}
