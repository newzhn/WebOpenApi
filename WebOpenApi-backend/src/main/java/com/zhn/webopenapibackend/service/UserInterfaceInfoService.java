package com.zhn.webopenapibackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapibackend.model.domain.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhn.webopenapibackend.model.request.user_api.UserInterfaceInfoAddRequest;
import com.zhn.webopenapibackend.model.request.user_api.UserInterfaceInfoQueryRequest;
import com.zhn.webopenapibackend.model.request.user_api.UserInterfaceInfoUpdateRequest;
import com.zhn.webopenapibackend.model.vo.UserInterfaceInfoVo;

import java.util.List;

/**
* @author zhanh
* @description 针对表【user_interface_info(用户接口关联表)】的数据库操作Service
* @createDate 2023-07-05 15:41:38
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    boolean addInterface(UserInterfaceInfoAddRequest request);

    boolean deleteByIds(List<Long> ids);

    boolean updateInterface(UserInterfaceInfoUpdateRequest request);

    UserInterfaceInfoVo getVoById(Long id);

    Page<UserInterfaceInfoVo> getVoPage(UserInterfaceInfoQueryRequest request);
}
