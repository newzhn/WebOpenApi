package com.zhn.webopenapibackend.controller;

import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.model.request.user.LoginRequest;
import com.zhn.webopenapibackend.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

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
        String token = loginService.login(request);
        return AjaxResult.success().put("token",token);
    }
}
