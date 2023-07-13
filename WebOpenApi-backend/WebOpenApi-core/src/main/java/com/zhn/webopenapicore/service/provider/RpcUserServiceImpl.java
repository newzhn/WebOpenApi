package com.zhn.webopenapicore.service.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapicommon.service.RpcUserService;
import com.zhn.webopenapicore.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/10 20:20
 * @blog www.zhnblog.icu
 */
@DubboService
public class RpcUserServiceImpl implements RpcUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByAccessKey(String accessKey) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccessKey,accessKey);
        return userMapper.selectOne(wrapper);
    }
}
