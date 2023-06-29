package com.zhn.webopenapibackend.controller;

import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.constant.UserConstant;
import com.zhn.webopenapibackend.model.domain.LoginUser;
import com.zhn.webopenapibackend.model.request.user.LoginRequest;
import com.zhn.webopenapibackend.service.LoginService;
import com.zhn.webopenapibackend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public AjaxResult login(@Valid @RequestBody LoginRequest request) {
        Map<String,Object> map = loginService.login(request);
        String token = (String) map.get("token");
        LoginUser loginUser = (LoginUser) map.get("loginUser");
        return AjaxResult.success(loginUser).put("token",token);
    }

    @GetMapping("/quit")
    public AjaxResult logout() {
        loginService.logout();
        return AjaxResult.success("登出成功");
    }
}
