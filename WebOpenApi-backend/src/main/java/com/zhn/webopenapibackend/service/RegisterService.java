package com.zhn.webopenapibackend.service;

import com.zhn.webopenapibackend.model.request.user.RegisterRequest;

/**
 * 注册服务
 * @author zhn
 * @version 1.0
 */
public interface RegisterService {

    String register(RegisterRequest request);
}
