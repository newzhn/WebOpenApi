package com.zhn.webopenapibackend.controller;

import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.model.request.user.RegisterRequest;
import com.zhn.webopenapibackend.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;

/**
 * 注册接口
 *
 * @author zhn
 * @version 1.0
 */
@RestController
@Validated
public class RegisterController {
    @Resource
    private RegisterService registerService;

    @PostMapping("/register")
    public AjaxResult register(@Valid @RequestBody RegisterRequest request) {
        String msg = registerService.register(request);
        return StringUtils.isEmpty(msg) ?AjaxResult.success() : AjaxResult.error(msg);
    }

    @GetMapping("/sendCode")
    public AjaxResult sendCode(@Email(message = "邮箱格式不正确") String email) {
        registerService.sendCode(email);
        return AjaxResult.success();
    }
}