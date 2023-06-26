package com.zhn.webopenapibackend.model.request.user;

import com.zhn.webopenapibackend.constant.RegexConstant;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 用户修改请求
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/25 19:06
 * @blog www.zhnblog.icu
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    @NotNull(message = "用户Id修改时不能为空")
    private Long id;

    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    @Pattern(regexp = RegexConstant.PASSWORD_REGEX,message="用户密码不符合格式")
    private String userPassword;

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空")
    @Size(min = 1,max = 10,message = "用户昵称长度必须要在1到10之间")
    private String userName;

    /**
     * 用户头像
     */
    @NotBlank(message = "用户头像Url不能为空")
    private String userAvatar;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 邮箱
     */
    @NotBlank(message = "用户邮箱不能为空")
    @Email
    private String email;

    /**
     * QQ
     */
    @NotBlank(message = "用户qq号不能为空")
    @Pattern(regexp = RegexConstant.QQ_REGEX,message="用户QQ号不符合格式")
    private String qq;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}
