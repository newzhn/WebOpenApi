package com.zhn.webopenapibackend.model.request.user;

import com.zhn.webopenapibackend.constant.RegexConstant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 接收前端注册信息
 * @author zhn
 * @version 1.0
 */
@Data
public class RegisterRequest {
    /**
     * 用户名
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
}
