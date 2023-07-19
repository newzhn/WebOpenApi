package com.zhn.webopenapicore.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapicommon.model.domain.UserInterfaceInfo;
import com.zhn.webopenapicommon.utils.ThrowUtil;
import com.zhn.webopenapicore.constant.InterfaceConstant;
import com.zhn.webopenapicore.mapper.UserInterfaceInfoMapper;
import com.zhn.webopenapicore.service.UserInterfaceService;
import com.zhn.webopenapicore.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author zhanh
* @description 针对表【user_interface_info(用户接口关联表)】的数据库操作Service实现
* @createDate 2023-07-05 15:41:38
*/
@Service
public class UserInterfaceServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceService {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Resource
    private UserService userService;

    @Override
    public UserInterfaceInfo getInfoByInterfaceId(Long interfaceInfoId) {
        Long userId = userService.getCurrentUser().getUser().getId();
        return this.getInfoByInterfaceId(userId,interfaceInfoId);
    }

    @Override
    public UserInterfaceInfo getInfoByInterfaceId(Long userId, Long interfaceInfoId) {
        LambdaQueryWrapper<UserInterfaceInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInterfaceInfo::getUserId,userId);
        wrapper.eq(UserInterfaceInfo::getInterfaceInfoId,interfaceInfoId);
        wrapper.eq(UserInterfaceInfo::getStatus,0);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoMapper.selectOne(wrapper);
        return userInterfaceInfo;
    }

    @Override
    public void validateApply(Long interfaceId) {
        User user = userService.getCurrentUser().getUser();
        if (StrUtil.isBlank(user.getAccessKey()) || StrUtil.isBlank(user.getAccessKey())) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"需要在个人中心里申请密钥后才能开通接口服务");
        }
        Long userId = user.getId();
        LambdaQueryWrapper<UserInterfaceInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserInterfaceInfo::getUserId,userId);
        wrapper.eq(UserInterfaceInfo::getInterfaceInfoId,interfaceId);
        UserInterfaceInfo userInterfaceInfo = this.getOne(wrapper);
        //如果有关联关系但是被拉黑
        ThrowUtil.throwIf(ObjectUtil.isNotNull(userInterfaceInfo)
                        && userInterfaceInfo.getStatus() == 1,
                HttpStatus.ERROR,"无法申请该接口调用权限，您已被拉黑");
        //如果有关联关系且剩余调用次数大于0则证明有该接口的调用权限
        ThrowUtil.throwIf(ObjectUtil.isNotNull(userInterfaceInfo)
                        && userInterfaceInfo.getSurplusNum() > 0,
                HttpStatus.ERROR,"您已有该接口的调用权限");
    }

    @Override
    public boolean applyInterface(Long interfaceId, Integer applyNum) {
        UserInterfaceInfo userInterfaceInfo = this.getInfoByInterfaceId(interfaceId);
        if (userInterfaceInfo != null && userInterfaceInfo.getSurplusNum() <= 0) {
            userInterfaceInfo.setSurplusNum(applyNum == null ?
                    InterfaceConstant.SURPLUS_NUMBER : applyNum);
            return this.updateById(userInterfaceInfo);
        }
        Long userId = userService.getCurrentUser().getUser().getId();
        userInterfaceInfo = new UserInterfaceInfo();
        userInterfaceInfo.setUserId(userId);
        userInterfaceInfo.setInterfaceInfoId(interfaceId);
        userInterfaceInfo.setTotalNum(InterfaceConstant.TOTAL_NUMBER);
        userInterfaceInfo.setSurplusNum(applyNum == null ? InterfaceConstant.SURPLUS_NUMBER : applyNum);
        return this.save(userInterfaceInfo);
    }

}




