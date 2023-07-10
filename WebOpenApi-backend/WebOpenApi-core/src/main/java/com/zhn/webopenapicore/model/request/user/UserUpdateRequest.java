package com.zhn.webopenapicore.model.request.user;

import com.zhn.webopenapicore.constant.RegexConstant;
import com.zhn.webopenapicore.validation.ValidationGroup;
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
    @NotEmpty(message = "用户密码不能为空",groups = ValidationGroup.class)
    @Pattern(regexp = RegexConstant.PASSWORD_REGEX,message="用户密码不符合格式",groups = ValidationGroup.class)
    private String userPassword;

    /**
     * 用户昵称
     */
    @NotEmpty(message = "用户昵称不能为空",groups = ValidationGroup.class)
    @Size(min = 1,max = 10,message = "用户昵称长度必须要在1到10之间",groups = ValidationGroup.class)
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
    @NotEmpty(message = "用户邮箱不能为空",groups = ValidationGroup.class)
    @Email(groups = ValidationGroup.class)
    private String email;

    /**
     * QQ
     */
    @NotEmpty(message = "用户qq号不能为空",groups = ValidationGroup.class)
    @Pattern(regexp = RegexConstant.QQ_REGEX,message="用户QQ号不符合格式",groups = ValidationGroup.class)
    private String qq;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}
