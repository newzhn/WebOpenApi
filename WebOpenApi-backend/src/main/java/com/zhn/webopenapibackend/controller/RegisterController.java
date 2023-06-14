package com.zhn.webopenapibackend.controller;

import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.model.request.user.RegisterRequest;
import com.zhn.webopenapibackend.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 注册接口
 *
 * @author zhn
 * @version 1.0
 */
@RestController
public class RegisterController {
    @Resource
    private RegisterService registerService;

    @PostMapping("/register")
    public AjaxResult register(@Valid @RequestBody RegisterRequest request) {
        String msg = registerService.register(request);
        return StringUtils.isEmpty(msg) ?AjaxResult.success() : AjaxResult.error(msg);
    }
}
