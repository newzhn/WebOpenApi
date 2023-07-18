package com.zhn.webopenapiinterface.controller;

import com.zhn.webopenapiinterface.model.ApiResult;
import com.zhn.webopenapiinterface.model.User;
import org.springframework.web.bind.annotation.*;

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
    public ApiResult getNameByGet(@RequestParam("name") String name) {
        return ApiResult.ok("/get 接口测试成功：" + name);
    }

    @GetMapping("/get/{name}")
    public ApiResult getNameByGetRest(@PathVariable("name") String name) {
        return ApiResult.ok("/get/{name} 接口测试成功：" + name);
    }

    @PostMapping("/post")
    public ApiResult getNameByPost(@RequestBody User user) {
        return ApiResult.ok("/post 接口测试成功：" + user.getName());
    }

    @PutMapping("/put")
    public ApiResult getNameByPut(@RequestBody User user) {
        return ApiResult.ok("/put 接口测试成功：" + user.getName());
    }

    @DeleteMapping("/delete")
    public ApiResult getNameByDelete(@RequestParam("name") String name) {
        return ApiResult.ok("/delete 接口测试成功：" + name);
    }

    @DeleteMapping("/delete/{name}")
    public ApiResult getNameByDeleteRest(@PathVariable("name") String name) {
        return ApiResult.ok("/delete/{name} 接口测试成功：" + name);
    }
}
