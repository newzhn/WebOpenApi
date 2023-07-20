package com.zhn.webopenapiinterface.constant;

/**
 * 正则表达式常量
 *
 * @author zhn
 */
public interface RegexConstant {
    /**
     * 手机号正则
     */
    String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    /**
     * 用户名正则
     */
    String USERNAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9_]{2,10}$";
    /**
     * 邮箱正则
     */
    String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * qq号正则
     */
    String QQ_REGEX = "^[1-9][0-9]{4,10}$";
    /**
     * 密码正则。6~20位的字母、数字、下划线
     */
    String PASSWORD_REGEX = "^[a-zA-Z0-9_.-=*&^%$#@!+]{6,20}$";
    /**
     * 验证码正则, 6位数字
     */
    String VERIFY_CODE_REGEX = "^[\\d]{6}$";
    /**
     * 域名正则
     */
    String DOMAIN_NAME_REGEX = "^[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
}
