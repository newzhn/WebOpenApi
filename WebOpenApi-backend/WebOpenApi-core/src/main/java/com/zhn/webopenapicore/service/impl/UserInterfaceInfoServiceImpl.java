package com.zhn.webopenapicore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapicore.constant.InterfaceConstant;
import com.zhn.webopenapicore.mapper.UserInterfaceInfoMapper;
import com.zhn.webopenapicore.model.domain.LoginUser;
import com.zhn.webopenapicore.model.domain.UserInterfaceInfo;
import com.zhn.webopenapicore.model.request.user_api.UserInterfaceInfoAddRequest;
import com.zhn.webopenapicore.model.request.user_api.UserInterfaceInfoQueryRequest;
import com.zhn.webopenapicore.model.request.user_api.UserInterfaceInfoUpdateRequest;
import com.zhn.webopenapicore.model.vo.UserInterfaceInfoVo;
import com.zhn.webopenapicore.service.InterfaceInfoService;
import com.zhn.webopenapicore.service.UserInterfaceInfoService;
import com.zhn.webopenapicore.service.UserService;
import com.zhn.webopenapicore.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author zhanh
* @description 针对表【user_interface_info(用户接口关联表)】的数据库操作Service实现
* @createDate 2023-07-05 15:41:38
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Resource
    private UserService userService;
    @Resource
    private InterfaceInfoService interfaceInfoService;


    @Override
    public boolean addInterface(UserInterfaceInfoAddRequest request) {
        UserInterfaceInfo userInterfaceInfo = BeanUtils.copy(request, UserInterfaceInfo.class);
        //获取当前登录用户信息作为调用者
        LoginUser loginUser = userService.getCurrentUser();
        userInterfaceInfo.setUserId(loginUser.getUser().getId());
        userInterfaceInfo.setTotalNum(InterfaceConstant.TOTAL_NUMBER);
        userInterfaceInfo.setSurplusNum(InterfaceConstant.TOTAL_NUMBER);
        return this.save(userInterfaceInfo);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        // TODO 进行删除前的一些处理
        return this.removeByIds(ids);
    }

    @Override
    public boolean updateInterface(UserInterfaceInfoUpdateRequest request) {
        UserInterfaceInfo userInterfaceInfo = BeanUtils.copy(request, UserInterfaceInfo.class);
        return this.updateById(userInterfaceInfo);
    }

    @Override
    public UserInterfaceInfoVo getVoById(Long id) {
        return null;
    }

    @Override
    public Page<UserInterfaceInfoVo> getVoPage(UserInterfaceInfoQueryRequest request) {
        return null;
    }
}




