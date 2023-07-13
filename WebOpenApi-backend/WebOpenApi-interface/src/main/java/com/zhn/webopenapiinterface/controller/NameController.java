package com.zhn.webopenapiinterface.controller;

import com.zhn.webopenapicommon.model.Result;
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
    public Result getNameByGet(@RequestParam("name") String name) {
        return Result.success("/get 接口测试成功：" + name);
    }

    @GetMapping("/get/{name}")
    public Result getNameByGetRest(@PathVariable("name") String name) {
        return Result.success("/get/{name} 接口测试成功：" + name);
    }

    @PostMapping("/post")
    public Result getNameByPost(@RequestBody User user) {
        return Result.success("/post 接口测试成功：" + user.getName());
    }

    @PutMapping("/put")
    public Result getNameByPut(@RequestBody User user) {
        return Result.success("/put 接口测试成功：" + user.getName());
    }

    @DeleteMapping("/delete")
    public Result getNameByDelete(@RequestParam("name") String name) {
        return Result.success("/delete 接口测试成功：" + name);
    }

    @DeleteMapping("/delete/{name}")
    public Result getNameByDeleteRest(@PathVariable("name") String name) {
        return Result.success("/delete/{name} 接口测试成功：" + name);
    }
}
