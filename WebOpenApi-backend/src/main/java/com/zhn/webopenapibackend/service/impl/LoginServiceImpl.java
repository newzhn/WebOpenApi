package com.zhn.webopenapibackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zhn.webopenapibackend.constant.CacheConstant;
import com.zhn.webopenapibackend.constant.UserConstant;
import com.zhn.webopenapibackend.exception.ThrowUtils;
import com.zhn.webopenapibackend.model.domain.LoginUser;
import com.zhn.webopenapibackend.model.domain.User;
import com.zhn.webopenapibackend.model.request.user.LoginRequest;
import com.zhn.webopenapibackend.service.LoginService;
import com.zhn.webopenapibackend.service.UserService;
import com.zhn.webopenapibackend.utils.JwtUtil;
import com.zhn.webopenapibackend.utils.redis.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhn
 * @version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisCache redisCache;

    @Override
    public Map<String,Object> login(LoginRequest request) {
        // 将登陆信息封装成Authentication的实现类
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUserAccount(), request.getUserPassword());
        // 调用manager查询数据库进行信息比对
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        ThrowUtils.throwIf(ObjectUtil.isNull(authentication),"账户名或密码错误");
        // 登录成功则根据用户Id生成Jwt
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        // 将登陆用户信息存入Redis
        redisCache.setCacheObject(CacheConstant.USER_LOGIN,userId,loginUser);
        //返回token和登录用户信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("loginUser",loginUser);
        return map;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if (loginUser == null) {
            throw new RuntimeException("用户未登录");
        }
        String key = CacheConstant.USER_LOGIN.getKeyPrefix() + loginUser.getUser().getId();
        redisCache.deleteObject(key);
    }
}
