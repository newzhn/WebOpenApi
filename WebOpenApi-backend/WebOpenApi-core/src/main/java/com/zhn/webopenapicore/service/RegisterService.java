package com.zhn.webopenapicore.service;

import com.zhn.webopenapicore.model.request.user.RegisterRequest;

/**
 * 注册服务
 *
 * @author zhn
 * @version 1.0
 */
public interface RegisterService {

    /**
     * 注册.
     *
     * @param request the request
     * @return the string
     */
    String register(RegisterRequest request);

    /**
     * 发送验证码服务.
     *
     * @param email the email
     */
    void sendCode(String email);
}
