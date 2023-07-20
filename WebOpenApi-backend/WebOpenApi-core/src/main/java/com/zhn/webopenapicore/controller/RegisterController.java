package com.zhn.webopenapicore.controller;

import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.model.request.user.RegisterRequest;
import com.zhn.webopenapicore.service.RegisterService;
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

    /**
     * 注册.
     *
     * @param request the request
     * @return the result
     */
    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterRequest request) {
        String msg = registerService.register(request);
        return StrUtil.isEmpty(msg) ? Result.success() : Result.error(msg);
    }

    /**
     * 发送注册邮箱验证码.
     * TODO 目前上线阿里云后无法发送，暂时不用验证码（未解决）
     *
     * @param email the email
     * @return the result
     */
    @GetMapping("/sendCode")
    public Result sendCode(@Email(message = "邮箱格式不正确")@NotBlank(message = "邮箱不能为空")
                                       String email) {
        registerService.sendCode(email);
        return Result.success();
    }
}
