package com.zhn.webopenapicore.constant;

/**
 * 用户常量
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public interface UserConstant {
    /**
     * 登录接口URI
     */
    String USER_LOGIN_URI = "/login";

    /**
     * 注册接口URI
     */
    String USER_REGISTER_URI = "/register";

    /**
     * 注册验证码接口URI
     */
    String USER_VERIFICATION_CODE = "/sendCode";

    //  region 权限

    /**
     * 默认角色
     */
    String DEFAULT_ROLE = "ROLE_user";

    /**
     * 管理员角色
     */
    String ADMIN_ROLE = "ROLE_admin";

    /**
     * 游客角色
     */
    String VISITOR_ROLE = "ROLE_visitor";

    /**
     * 被封号
     */
    String BAN_ROLE = "ROLE_ban";

    /**
     * qq头像API
     */
    String QQ_AVATAR_API = "https://q1.qlogo.cn/g?b=qq&nk=";

    /**
     * qq昵称API
     */
    String QQ_NICKNAME_API = "https://r.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?g_tk=1518561325&uins=";
}
