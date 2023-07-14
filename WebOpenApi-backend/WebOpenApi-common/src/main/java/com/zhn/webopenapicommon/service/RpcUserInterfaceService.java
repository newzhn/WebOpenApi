package com.zhn.webopenapicommon.service;

import com.zhn.webopenapicommon.model.domain.UserInterfaceInfo;

/**
 * 用户与接口关联的Rpc接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/10 20:19
 * @blog www.zhnblog.icu
 */
public interface RpcUserInterfaceService {
    /**
     * Gets one by interface info id.
     *
     * @param interfaceInfoId the interface info id
     * @return the one by interface info id
     */
    UserInterfaceInfo getOneByInterfaceInfoId(Long userId, Long interfaceInfoId);

    /**
     * Update invoke num boolean.
     *
     * @param userInterfaceInfoId the interface info id
     * @return the boolean
     */
    boolean updateInvokeNum(Long userInterfaceInfoId);
}
