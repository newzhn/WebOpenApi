package com.zhn.webopenapibackend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapibackend.model.domain.InterfaceInfo;
import com.zhn.webopenapibackend.model.domain.LoginUser;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoAddRequest;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoQueryRequest;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoUpdateRequest;
import com.zhn.webopenapibackend.model.vo.InterfaceInfoVo;
import com.zhn.webopenapibackend.service.InterfaceInfoService;
import com.zhn.webopenapibackend.mapper.InterfaceInfoMapper;
import com.zhn.webopenapibackend.service.UserService;
import com.zhn.webopenapibackend.utils.bean.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2023-06-13 22:21:40
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @Resource
    private UserService userService;

    @Override
    public boolean addInterface(InterfaceInfoAddRequest request) {
        InterfaceInfo interfaceInfo = BeanUtils.copy(request, InterfaceInfo.class);
        // TODO 处理接口添加信息
        LoginUser loginUser = userService.getCurrentUser();
        interfaceInfo.setUserId(loginUser.getUser().getId());
        interfaceInfo.setCreateBy(loginUser.getUsername());
        return this.save(interfaceInfo);
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        // TODO 进行删除前的一些处理
        return this.removeByIds(ids);
    }

    @Override
    public boolean updateInterface(InterfaceInfoUpdateRequest request) {
        InterfaceInfo interfaceInfo = BeanUtils.copy(request, InterfaceInfo.class);
        // TODO 处理接口修改数据，删除对应缓存
        LoginUser loginUser = userService.getCurrentUser();
        interfaceInfo.setUpdateBy(loginUser.getUsername());
        return this.updateById(interfaceInfo);
    }

    @Override
    public InterfaceInfoVo getVoById(Long id) {
        // TODO 查询接口缓存
        InterfaceInfo interfaceInfo = this.getById(id);
        InterfaceInfoVo interfaceInfoVo = BeanUtils.copy(interfaceInfo, InterfaceInfoVo.class);
        return interfaceInfoVo;
    }

    @Override
    public Page<InterfaceInfoVo> getVoPage(InterfaceInfoQueryRequest request) {
        // 拼接查询条件
        LambdaQueryWrapper<InterfaceInfo> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(request.getName())) {
            wrapper.eq(InterfaceInfo::getName,request.getName());
        }
        if (StrUtil.isNotBlank(request.getMethod())) {
            wrapper.eq(InterfaceInfo::getMethod,request.getMethod());
        }
        if (StrUtil.isNotBlank(request.getUrl())) {
            wrapper.like(InterfaceInfo::getUrl,request.getUrl());
        }
        if (request.getStatus() != null) {
            wrapper.eq(InterfaceInfo::getStatus,request.getStatus());
        }
        wrapper.orderByDesc(InterfaceInfo::getCreateTime);
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<InterfaceInfo> interfaceInfoPage = this.page(new Page<>(current, pageSize), wrapper);
        Page<InterfaceInfoVo> interfaceInfoVoPage = new Page<>(current, pageSize, interfaceInfoPage.getTotal());
        interfaceInfoVoPage.setRecords(
                BeanUtils.copyList(interfaceInfoPage.getRecords(),InterfaceInfoVo.class));
        return interfaceInfoVoPage;
    }
}




