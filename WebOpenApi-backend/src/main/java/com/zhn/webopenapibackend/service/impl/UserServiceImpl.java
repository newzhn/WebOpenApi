package com.zhn.webopenapibackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapibackend.exception.ThrowUtils;
import com.zhn.webopenapibackend.model.domain.LoginUser;
import com.zhn.webopenapibackend.model.domain.User;
import com.zhn.webopenapibackend.service.UserService;
import com.zhn.webopenapibackend.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-06-13 22:21:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService, UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public boolean checkUserAccount(String userAccount) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUserAccount, userAccount);
        return userMapper.selectOne(wrapper) != null;
    }

    @Override
    public boolean checkEmail(String email) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getEmail, email);
        return userMapper.selectOne(wrapper) != null;
    }

    @Override
    public boolean checkQQ(String qq) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getQq, qq);
        return userMapper.selectOne(wrapper) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getUserAccount,username);
        User user = userMapper.selectOne(wrapper);
        ThrowUtils.throwIf(ObjectUtil.isNull(user),
                HttpStatus.HTTP_NOT_FOUND,"用户信息不存在");
        return new LoginUser(user, Collections.singletonList(user.getUserRole()));
    }
}




