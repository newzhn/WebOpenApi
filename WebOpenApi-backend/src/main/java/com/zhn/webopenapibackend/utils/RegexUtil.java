package com.zhn.webopenapibackend.utils;

import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapibackend.constant.RegexConstant;

/**
 * @author zhn
 */
public class RegexUtil {
    /**
     * 是否是无效手机格式
     * @param phone 要校验的手机号
     * @return true:符合，false：不符合
     */
    public static boolean isPhoneInvalid(String phone){
        return mismatch(phone, RegexConstant.PHONE_REGEX);
    }

    /**
     * 是否是无效用户名格式
     * @param userName 要校验的用户名
     * @return true:符合，false：不符合
     */
    public static boolean isUserNameInvalid(String userName) {
        return mismatch(userName,RegexConstant.USERNAME_REGEX);
    }

    /**
     * 是否是无效邮箱格式
     * @param email 要校验的邮箱
     * @return true:符合，false：不符合
     */
    public static boolean isEmailInvalid(String email){
        return mismatch(email, RegexConstant.EMAIL_REGEX);
    }

    /**
     * 是否是无效qq格式
     * @param qq 要校验的qq号
     * @return true:符合，false：不符合
     */
    public static boolean isQQInvalid(String qq) {
        return mismatch(qq,RegexConstant.QQ_REGEX);
    }

    /**
     * 是否是无效密码格式
     * @param password 要校验的密码
     * @return true:符合，false：不符合
     */
    public static boolean isPasswordInvalid(String password) {
        return mismatch(password,RegexConstant.PASSWORD_REGEX);
    }

    /**
     * 是否是无效验证码格式
     * @param code 要校验的验证码
     * @return true:符合，false：不符合
     */
    public static boolean isCodeInvalid(String code){
        return mismatch(code, RegexConstant.VERIFY_CODE_REGEX);
    }

    // 校验是否不符合正则格式
    private static boolean mismatch(String str, String regex){
        if (StrUtil.isBlank(str)) {
            return true;
        }
        return !str.matches(regex);
    }
}
