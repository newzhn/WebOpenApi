package com.zhn.webopenapicore.service;

/**
 * 邮件相关服务
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/29 12:26
 * @blog www.zhnblog.icu
 */
public interface EmailService {
    /**
     * 向目标邮箱发送验证码
     * @param toEmail
     * @param code
     */
    void sendVerificationCode(String toEmail,String code);
}
