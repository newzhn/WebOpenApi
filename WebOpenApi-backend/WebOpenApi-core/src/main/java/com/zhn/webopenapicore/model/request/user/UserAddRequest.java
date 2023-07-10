package com.zhn.webopenapicore.model.request.user;

import com.zhn.webopenapicore.constant.RegexConstant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 用户添加请求
 *
 * @author zhn
 * @version 1.0
 */
@Data
public class UserAddRequest implements Serializable {
    /**
     * 账号
     */
    @NotBlank(message = "用户账户名不能为空")
    @Pattern(regexp = RegexConstant.USERNAME_REGEX,message="用户账户名不符合格式")
    private String userAccount;

    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    @Pattern(regexp = RegexConstant.PASSWORD_REGEX,message="用户密码不符合格式")
    private String userPassword;

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
