package com.zhn.webopenapibackend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zhn.webopenapibackend.model.domain.User;

/**
 * The interface User service.
 *
 * @author zhanh
 * @description 针对表 【user(用户)】的数据库操作Service
 * @createDate 2023 -06-13 22:21:23
 */
public interface UserService extends IService<User> {
    /**
     * 校验账户名
     *
     * @param userAccount the user account
     * @return the boolean
     */
    boolean checkUserAccount(String userAccount);

    /**
     * 校验邮箱
     *
     * @param email the email
     * @return the boolean
     */
    boolean checkEmail(String email);

    /**
     * 校验QQ
     *
     * @param qq the qq
     * @return the boolean
     */
    boolean checkQQ(String qq);
}
