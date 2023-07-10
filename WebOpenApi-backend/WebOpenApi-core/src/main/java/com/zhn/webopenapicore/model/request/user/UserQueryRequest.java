package com.zhn.webopenapicore.model.request.user;

import com.zhn.webopenapicore.model.request.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户查询请求
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/25 19:07
 * @blog www.zhnblog.icu
 */
@Data
public class UserQueryRequest extends PageRequest implements Serializable {
    /**
     * 账号
     */
    private String userAccount;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 用户角色：user/admin/ban
     */
    private String userRole;

    private static final long serialVersionUID = 1L;
}
