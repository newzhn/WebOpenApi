package com.zhn.webopenapiinterface.controller;

import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapiinterface.model.ApiResult;
import org.springframework.web.bind.annotation.*;

/**
 * 测试接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/29 22:05
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/test")
public class NameController {
    @GetMapping("/get/{name}")
    public ApiResult getNameByGetRest(@PathVariable("name") String name) {
        return ApiResult.ok("/get/{name} 接口测试成功：" + name);
    }

    @PostMapping("/post/name")
    public ApiResult getNameByPostRest(@RequestBody User user) {
        return ApiResult.ok("/post name 接口测试成功：" + user.getUserName());
    }
}
