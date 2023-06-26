package com.zhn.webopenapibackend.service.impl;

import com.zhn.webopenapibackend.constant.UserConstant;
import com.zhn.webopenapibackend.model.domain.User;
import com.zhn.webopenapibackend.model.request.user.RegisterRequest;
import com.zhn.webopenapibackend.service.RegisterService;
import com.zhn.webopenapibackend.service.UserService;
import com.zhn.webopenapibackend.utils.QQUtil;
import com.zhn.webopenapibackend.utils.bean.BeanUtils;
import com.zhn.webopenapibackend.utils.string.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.Charset;
import java.util.Collections;

/**
 * @author zhn
 * @version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RestTemplate restTemplate;

    @Override
    public String register(RegisterRequest request) {
        User user = BeanUtils.copy(request, User.class);
        //校验用户名是否重复
        if (userService.checkUserAccount(user.getUserAccount())) {
            return "账户名已被注册";
        }
        //校验邮箱是否重复
        if (userService.checkUserAccount(user.getEmail())) {
            return "邮箱已被使用";
        }
        //校验QQ是否重复
        if (userService.checkUserAccount(user.getUserAccount())) {
            return "账户名已被使用";
        }
        //密码加密
        String password = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(password);
        //默认角色为普通用户
        user.setUserRole(UserConstant.DEFAULT_ROLE);
        //根据QQ获取昵称和头像
        QQUtil.setAvatarUrlAndNickname(user,restTemplate);
        //创建用户
        if (!userService.save(user)) {
            return "系统出错，注册失败";
        }
        return "";
    }
}
