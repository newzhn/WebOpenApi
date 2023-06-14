package com.zhn.webopenapibackend.model.request.user;

import com.zhn.webopenapibackend.constant.RegexConstant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author zhn
 * @version 1.0
 */
@Data
public class LoginRequest {
    /**
     * 账户名
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
}
