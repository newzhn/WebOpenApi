package com.zhn.webopenapicore.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapicore.constant.UserConstant;
import com.zhn.webopenapicore.model.eneum.CacheEnums;
import com.zhn.webopenapicore.model.request.user.RegisterRequest;
import com.zhn.webopenapicore.service.EmailService;
import com.zhn.webopenapicore.service.RegisterService;
import com.zhn.webopenapicore.service.UserService;
import com.zhn.webopenapicore.utils.QQUtil;
import com.zhn.webopenapicore.utils.bean.BeanUtils;
import com.zhn.webopenapicore.utils.redis.RedisCache;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import static com.zhn.webopenapicore.constant.CommonConstant.SALT;

/**
 * @author zhn
 * @version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private UserService userService;
    @Resource
    private EmailService emailService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisCache redisCache;

    @Override
    public String register(RegisterRequest request) {
        //校验验证码是否正确
        String code = request.getVerificationCode();
        String cacheCode = redisCache.getCacheObject(
                CacheEnums.REGISTER_CODE.getKeyPrefix() + request.getEmail());
        if (!code.equals(cacheCode)) {
            return "验证码错误";
        }
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
        //分配api签名 TODO 后续更改为用户自己申请
        String accessKey = DigestUtil.md5Hex(SALT + user.getUserAccount() +
                RandomUtil.randomNumbers(4));
        String secretKey = DigestUtil.md5Hex(SALT + user.getUserAccount() +
                RandomUtil.randomNumbers(8));
        user.setAccessKey(accessKey);
        user.setSecretKey(secretKey);
        //根据QQ获取昵称和头像
        QQUtil.setAvatarUrlAndNickname(user,restTemplate);
        //创建用户
        if (!userService.save(user)) {
            return "系统出错，注册失败";
        }
        return "";
    }

    @Override
    public void sendCode(String email) {
        //创建验证码
        String code = RandomUtil.randomNumbers(6);
        //保存验证码到Redis，设置有效期，用邮箱做key
        redisCache.setCacheObject(CacheEnums.REGISTER_CODE,email,code);
        //发送验证码
        emailService.sendVerificationCode(email,code);
    }
}
