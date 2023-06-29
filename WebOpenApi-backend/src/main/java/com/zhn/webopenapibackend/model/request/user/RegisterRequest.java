package com.zhn.webopenapibackend.model.request.user;

import com.zhn.webopenapibackend.constant.RegexConstant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
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
    @Email(message = "邮箱格式错误")
    private String email;
    /**
     * QQ
     */
    @NotBlank(message = "用户qq号不能为空")
    @Pattern(regexp = RegexConstant.QQ_REGEX,message="用户QQ号不符合格式")
    private String qq;

    /**
     * 验证码
     */
    @NotBlank(message = "注册验证码不能为空")
    @Pattern(regexp = RegexConstant.VERIFY_CODE_REGEX,message="验证码必须是六位大小的数字")
    private String verificationCode;
}
