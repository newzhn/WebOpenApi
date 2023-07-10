package com.zhn.webopenapiinterface.controller;

import com.zhn.webopenapiclientsdk.model.User;
import com.zhn.webopenapicommon.model.Result;
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
    public Result getNameByGet(String name) {
        return Result.success("Get 你的名字是：" + name);
    }

    @PostMapping("/post")
    public Result getNameByPost(@RequestParam("name") String name) {
        return Result.success("Post 你的名字是：" + name);
    }

    @PostMapping("/json")
    public Result getNameByPostJson(@RequestBody User user, HttpServletRequest request) {
        return Result.success(user.getName());
    }
}
