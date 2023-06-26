package com.zhn.webopenapibackend.service;

import com.zhn.webopenapibackend.model.request.user.LoginRequest;

/**
 * @author zhn
 * @version 1.0
 */
public interface LoginService {
    /**
     * 登录服务
     * @param request
     * @return
     */
    String login(LoginRequest request);

    /**
     * 登出服务
     */
    void logout();
}
