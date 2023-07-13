package com.zhn.webopenapicore.service.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhn.webopenapicommon.model.domain.UserInterfaceInfo;
import com.zhn.webopenapicommon.service.RpcUserInterfaceInfoService;
import com.zhn.webopenapicore.mapper.UserInterfaceInfoMapper;
import com.zhn.webopenapicore.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/10 20:21
 * @blog www.zhnblog.icu
 */
@DubboService
public class RpcUserInterfaceInfoServiceImpl implements RpcUserInterfaceInfoService {
    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public UserInterfaceInfo getOneByInterfaceInfoId(Long userId, Long interfaceInfoId) {
        // TODO 后续优化可以加上时间过期条件
        return userInterfaceInfoService.getInfoByInterfaceId(userId, interfaceInfoId);
    }

    @Override
    public boolean updateInvokeNum(Long userInterfaceInfoId) {
        LambdaUpdateWrapper<UserInterfaceInfo> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(UserInterfaceInfo::getId, userInterfaceInfoId);
        wrapper.gt(UserInterfaceInfo::getSurplusNum, 0);
        wrapper.setSql("total_num = total_num + 1, surplus_num = surplus_num - 1");
        return userInterfaceInfoService.update(wrapper);
    }
}
