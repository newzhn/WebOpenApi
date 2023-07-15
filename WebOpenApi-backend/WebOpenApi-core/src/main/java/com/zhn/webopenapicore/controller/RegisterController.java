package com.zhn.webopenapicore.controller;

import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.model.request.user.RegisterRequest;
import com.zhn.webopenapicore.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

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
    public Result register(@Valid @RequestBody RegisterRequest request) {
        String msg = registerService.register(request);
        return StringUtils.isEmpty(msg) ? Result.success() : Result.error(msg);
    }

    @GetMapping("/sendCode")
    public Result sendCode(@Email(message = "邮箱格式不正确")@NotBlank(message = "邮箱不能为空")
                                       String email) {
        registerService.sendCode(email);
        return Result.success();
    }
}
