package com.zhn.webopenapicore.service.impl;

import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicore.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

/**
 * 邮件相关服务
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/29 12:26
 * @blog www.zhnblog.icu
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendVerificationCode(String toEmail, String code) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject("[WebOpenApi]用户注册");
            message.setText("本次请求的邮件验证码为:" + code + "\n\n本验证码 3 分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                    + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");
            javaMailSender.send(mimeMessage);
            log.info("发送验证码成功：[{}] => [{}]",code,toEmail);
        } catch (Exception e) {
            log.error("发送验证码失败：[注册用户：{}]",toEmail,e);
            throw new BusinessException(HttpStatus.ERROR,"发送验证码失败，请检查邮箱输入是否正确");
        }
    }
}
