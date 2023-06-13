package com.zhn.webopenapibackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapibackend.model.domain.User;
import com.zhn.webopenapibackend.service.UserService;
import com.zhn.webopenapibackend.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhanh
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-06-13 22:21:23
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




