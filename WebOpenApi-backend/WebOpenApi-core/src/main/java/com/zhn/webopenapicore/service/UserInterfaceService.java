package com.zhn.webopenapicore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhn.webopenapicommon.model.domain.UserInterfaceInfo;
import com.zhn.webopenapicore.model.request.PageRequest;

import java.util.List;

/**
 * The interface User interface service.
 *
 * @author zhanh
 * @description 针对表 【user_interface_info(用户接口关联表)】的数据库操作Service
 * @createDate 2023 -07-05 15:41:38
 */
public interface UserInterfaceService extends IService<UserInterfaceInfo> {

    /**
     * Gets info by interface id.
     *
     * @param interfaceInfoId the interface info id
     * @return the info by interface id
     */
    UserInterfaceInfo getInfoByInterfaceId(Long interfaceInfoId);

    /**
     * Gets info by interface id.
     *
     * @param userId          the user id
     * @param interfaceInfoId the interface info id
     * @return the info by interface id
     */
    UserInterfaceInfo getInfoByInterfaceId(Long userId, Long interfaceInfoId);

    /**
     * 校验当前登录用户是否申请过该接口，有则报异常
     *
     * @param interfaceId the interface id
     * @return the user interface info
     */
    void validateApply(Long interfaceId);

    boolean applyInterface(Long interfaceId, Integer applyNum);

}
