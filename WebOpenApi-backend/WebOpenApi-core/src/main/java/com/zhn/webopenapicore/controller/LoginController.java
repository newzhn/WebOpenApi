package com.zhn.webopenapicore.controller;

import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.model.domain.LoginUser;
import com.zhn.webopenapicore.model.request.user.LoginRequest;
import com.zhn.webopenapicore.service.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * 登录接口
 * @author zhn
 * @version 1.0
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public Result login(@Valid @RequestBody LoginRequest request) {
        Map<String,Object> map = loginService.login(request);
        String token = (String) map.get("token");
        LoginUser loginUser = (LoginUser) map.get("loginUser");
        return Result.success(loginUser).put("token",token);
    }

    @GetMapping("/quit")
    public Result logout() {
        loginService.logout();
        return Result.success("登出成功");
    }
}
