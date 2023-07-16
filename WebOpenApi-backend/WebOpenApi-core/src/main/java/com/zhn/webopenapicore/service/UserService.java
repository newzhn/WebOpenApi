package com.zhn.webopenapicore.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapicore.model.LoginUser;
import com.zhn.webopenapicore.model.request.user.UserAddRequest;
import com.zhn.webopenapicore.model.request.user.UserQueryRequest;
import com.zhn.webopenapicore.model.request.user.UserUpdateRequest;
import com.zhn.webopenapicore.model.vo.UserVo;

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


    /**
     * 添加用户（仅管理员）
     *
     * @param request the request
     * @return the boolean
     */
    boolean addUser(UserAddRequest request);

    /**
     * 删除用户（仅管理员）
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteById(Long id);

    /**
     * 修改用户信息
     *
     * @param request the request
     * @return the boolean
     */
    boolean updateUser(UserUpdateRequest request);

    /**
     * 查询用户Vo信息
     *
     * @param id the id
     * @return the vo by id
     */
    UserVo getVoById(Long id);

    /**
     * 查询用户Vo分页信息
     *
     * @param request the request
     * @return the vo page
     */
    Page<UserVo> getVoPage(UserQueryRequest request);


    /**
     * 获取当前登录用户信息
     *
     * @return the current user
     */
    LoginUser getCurrentUser();

    /**
     * 获取当前登录用户信息(根据Token)
     *
     * @param token the token
     * @return the current user
     */
    LoginUser getCurrentUser(String token);

    void applyApiToken();
}
