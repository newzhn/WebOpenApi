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
     * 获取当前登录用户与该接口的关联数据.
     *
     * @param interfaceInfoId the interface info id
     * @return the info by interface id
     */
    UserInterfaceInfo getInfoByInterfaceId(Long interfaceInfoId);

    /**
     * 获取当前登录用户与该接口的关联数据.
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

    /**
     * 申请接口服务.
     *
     * @param interfaceId the interface id
     * @param applyNum    the apply num
     * @return the boolean
     */
    boolean applyInterface(Long interfaceId, Integer applyNum);

}
