package com.zhn.webopenapibackend.constant;

import io.jsonwebtoken.Claims;

/**
 * 通用常量信息
 *
 * @author server
 */
public interface SystemConstants {
    /**
     * UTF-8 字符集
     */
    String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    String GBK = "GBK";

    /**
     * http请求
     */
    String HTTP = "http://";

    /**
     * https请求
     */
    String HTTPS = "https://";

    /**
     * 通用成功标识
     */
    String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    String FAIL = "1";

    /**
     * 短信验证码5分钟
     */
    Integer VERIFYCODE_EXPIRATION = 5;

    /**
     * JWT验证的请求头字段名
     */
    String JWT_HEADER = "Authorization";

    /**
     * JWT加密密钥
     */
    String JWT_TOKEN_SECRET = "2G2QLUjuFJfNOjjAZP2k";

    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    String LOGOUT = "Logout";

    /**
     * 注册
     */
    String REGISTER = "Register";

    /**
     * 登录失败
     */
    String LOGIN_FAIL = "Error";

    /**
     * 验证码 redis key
     */
    String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 登录用户 redis key
     */
    String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 前台登录用户 redis key
     */
    String LOGIN_TOKEN_KEY_FRONT = "login_tokens_front:";

    /**
     * 防重提交 redis key
     */
    String REPEAT_SUBMIT_KEY = "repeat_submit:";

    /**
     * 限流 redis key
     */
    String RATE_LIMIT_KEY = "rate_limit:";

    /**
     * 验证码有效期（分钟）
     */
    Integer CAPTCHA_EXPIRATION = 2;

    /**
     * 短信验证码 redis key
     */
    String CAPTCHA_CODE_KEY_SMS = "captcha_codes_sms:";

    /**
     * 短信验证码有效期（分钟）
     */
    Integer CAPTCHA_EXPIRATION_SMS = 5;

    /**
     * 令牌
     */
    String TOKEN = "token";

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户ID
     */
    String JWT_USERID = "userid";

    /**
     * 用户名称
     */
    String JWT_USERNAME = Claims.SUBJECT;

    /**
     * 用户头像
     */
    String JWT_AVATAR = "avatar";

    /**
     * 创建时间
     */
    String JWT_CREATED = "created";

    /**
     * 用户权限
     */
    String JWT_AUTHORITIES = "authorities";

    /**
     * 参数管理 cache key
     */
    String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    String RESOURCE_PREFIX = "/profile";

    /**
     * RMI 远程方法调用
     */
    String LOOKUP_RMI = "rmi://";

    /**
     * LDAP 远程方法调用
     */
    String LOOKUP_LDAP = "ldap://";

    /**
     * 定时任务违规的字符
     */
    String[] JOB_ERROR_STR = {"java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
            "org.springframework.jndi"};

    /**
     * 祭品分类
     */
    String SACRIFICE_TYPE_LIST = "sacrifice:type:list";

    /**
     * 祭品分类不可删除的id
     */
    long[] SACRIFICE_TYPE_CANNOT_DELETED_IDS = new long[]{1, 2, 3};

    /**
     * 家谱分类
     */
    long SACRIFICE_TYPE_JPCOIN = 1;

    /**
     * 积分分类
     */
    long SACRIFICE_TYPE_INTEGRAL = 2;

    /**
     * 自动分类
     */
    long SACRIFICE_TYPE_AUTO = 3;

    /**
     * 家谱币分类id
     */
    long SACRIFICE_TYPE_JPCOIN_ID = 1;

    /**
     * 积分商品分类id
     */
    long SACRIFICE_TYPE_JF_ID = 2;

    /**
     * 图片文件类型
     */
    String[] IMAGE_TYPES = new String[]{".jpg", ".png", ".jpeg", ".gif"};

    /**
     * 视频类型
     */
    String[] VIDEO_TYPE = new String[]{".mp4",".avi"};
    /**
     * 祭品延迟队列
     */
    String SACRIFICE_ORDER_DELAY_QUEUE = "memorial_sacrifice_order_delay_queue";
    /**
     * 祭品延迟队列
     */
    String WORSHIP_ARTICLES_ORDER_DELAY_QUEUE = "worship_articles_order_delay_queue";
    /**
     * 相册的关联类型
     */
    Integer[] ALBUM_ASSOCIATION_TYPES = {0, 1, 2};
    /**
     * 背景类型 1=首页 2=祭拜页
     */
    Integer[] BACKGROUND_TYPE = {1, 2};

    /**
     * 普通套餐的id
     */
    Integer ORDINARY_PACKAGE_ID = 1;

    /**
     * 任务拦截器拦截的请求方式
     */
    String[] TASK_INTERCEPTOR_REQMETHODS = {"POST", "PUT"};
    /**
     * 地区列表key
     */
    String REGION_KEY = "region:list";

    /**
     * 业务配置 redis 存储前置key
     */
    String BIZ_BASIC_CONFIG_KEY = "biz_basic_config:";

    /**
     * mysql cache表key
     */
    String UPDATE_CACHE_JIAPU_CODE_KEY = "jiapu_code:";
}
