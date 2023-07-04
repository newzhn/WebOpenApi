package com.zhn.webopenapibackend.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapibackend.model.eneum.CacheEnums;
import com.zhn.webopenapibackend.constant.UserConstant;
import com.zhn.webopenapibackend.exception.BusinessException;
import com.zhn.webopenapibackend.exception.ThrowUtils;
import com.zhn.webopenapibackend.model.domain.LoginUser;
import com.zhn.webopenapibackend.model.domain.User;
import com.zhn.webopenapibackend.model.request.user.UserAddRequest;
import com.zhn.webopenapibackend.model.request.user.UserQueryRequest;
import com.zhn.webopenapibackend.model.request.user.UserUpdateRequest;
import com.zhn.webopenapibackend.model.vo.UserVo;
import com.zhn.webopenapibackend.service.UserService;
import com.zhn.webopenapibackend.mapper.UserMapper;
import com.zhn.webopenapibackend.utils.JwtUtil;
import com.zhn.webopenapibackend.utils.QQUtil;
import com.zhn.webopenapibackend.utils.bean.BeanUtils;
import com.zhn.webopenapibackend.utils.redis.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Collections;

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
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisCache redisCache;

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
    public boolean addUser(UserAddRequest request) {
        User user = BeanUtils.copy(request, User.class);
        //密码加密
        String password = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(password);
        //默认角色为普通用户
        user.setUserRole(UserConstant.DEFAULT_ROLE);
        //根据QQ获取昵称和头像
        QQUtil.setAvatarUrlAndNickname(user,restTemplate);
        return this.save(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.removeById(id);
    }

    @Override
    public boolean updateUser(UserUpdateRequest request) {
        User user = BeanUtils.copy(request, User.class);
        return this.updateById(user);
    }

    @Override
    public UserVo getVoById(Long id) {
        User user = this.getById(id);
        ThrowUtils.throwIf(user == null, "该用户信息不存在");
        return BeanUtils.copy(user,UserVo.class);
    }

    @Override
    public Page<UserVo> getVoPage(UserQueryRequest request) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //拼接条件
        if (StrUtil.isNotBlank(request.getUserAccount())) {
            wrapper.eq(User::getUserAccount,request.getUserAccount());
        }
        if (StrUtil.isNotBlank(request.getEmail())) {
            wrapper.eq(User::getEmail,request.getEmail());
        }
        if (StrUtil.isNotBlank(request.getQq())) {
            wrapper.eq(User::getQq,request.getQq());
        }
        if (StrUtil.isNotBlank(request.getUserRole())) {
            wrapper.eq(User::getUserRole,request.getUserRole());
        }
        wrapper.orderByDesc(User::getCreateTime);
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<User> userPage = this.page(new Page<>(current, pageSize), wrapper);
        Page<UserVo> userVoPage = new Page<>(current, pageSize, userPage.getTotal());
        userVoPage.setRecords(BeanUtils.copyList(userPage.getRecords(),UserVo.class));
        return userVoPage;
    }

    @Override
    public LoginUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        if (loginUser == null) {
            throw new RuntimeException("用户未登录");
        }
        return loginUser;
    }

    @Override
    public LoginUser getCurrentUser(String token) {
        //token解析
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("非法的Token");
        }
        //Redis查询登录数据
        String key = CacheEnums.USER_LOGIN.getKeyPrefix() + userId;
        LoginUser loginUser = redisCache.getCacheObject(key);
        if (loginUser == null) {
            throw new BusinessException("用户未登录");
        }
        return loginUser;
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




