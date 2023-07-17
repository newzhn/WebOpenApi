package com.zhn.webopenapicore.model.vo.user;

import lombok.Data;

import java.util.Date;

/**
 * 返回给前端的用户信息封装类
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/26 13:58
 * @blog www.zhnblog.icu
 */
@Data
public class UserVo {
    /**
     * id
     */
    private Long id;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;
}
