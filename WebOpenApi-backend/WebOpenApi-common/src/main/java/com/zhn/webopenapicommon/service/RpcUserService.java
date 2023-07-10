package com.zhn.webopenapicommon.service;

import com.zhn.webopenapicommon.model.domain.User;

/**
 * 用户相关的Rpc接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/10 20:18
 * @blog www.zhnblog.icu
 */
public interface RpcUserService {

    /**
     * 根据accessKey查询用户
     *
     * @param accessKey the access key
     * @return the user by access key
     */
    User getUserByAccessKey(String accessKey);
}
