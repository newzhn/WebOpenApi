package com.zhn.webopenapicore.service;

import com.zhn.webopenapicore.model.request.user.RegisterRequest;

/**
 * 注册服务
 * @author zhn
 * @version 1.0
 */
public interface RegisterService {

    String register(RegisterRequest request);

    void sendCode(String email);
}
