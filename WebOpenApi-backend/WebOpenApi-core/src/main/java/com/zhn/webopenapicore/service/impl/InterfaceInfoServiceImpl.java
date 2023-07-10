package com.zhn.webopenapicore.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.zhn.webopenapiclientsdk.client.WebApiClient;
import com.zhn.webopenapiclientsdk.model.User;
import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.domain.InterfaceInfo;
import com.zhn.webopenapicommon.utils.ThrowUtils;
import com.zhn.webopenapicore.mapper.InterfaceInfoMapper;
import com.zhn.webopenapicore.model.LoginUser;
import com.zhn.webopenapicore.model.eneum.InterfaceInfoStatusEnum;
import com.zhn.webopenapicore.model.request.IdRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoAddRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoInvokeRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoQueryRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoUpdateRequest;
import com.zhn.webopenapicore.model.vo.InterfaceInfoVo;
import com.zhn.webopenapicore.service.InterfaceInfoService;
import com.zhn.webopenapicore.service.UserService;
import com.zhn.webopenapicore.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author zhanh
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2023-06-13 22:21:40
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @Resource
    private UserService userService;
    @Resource
    private WebApiClient webApiClient;

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

    @Override
    public void onlineInterfaceInfo(IdRequest request) {
        //校验接口信息是否存在
        Long id = request.getId();
        InterfaceInfo interfaceInfo = this.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(interfaceInfo),
                HttpStatus.NOT_FOUND,"接口信息不存在");
        //验证接口是否可用 TODO 此处客户端接口是写死的，后续优化
        User user = new User();
        user.setName("zhangsan");
        String result = null;
        try {
            result = webApiClient.getNameByPostJson(user);
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.ERROR,"接口验证失败",e);
        }
        ThrowUtils.throwIf(!"zhangsan".equals(result),
                HttpStatus.ERROR,"接口验证失败");
        //上线接口
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getStatus());
        this.updateById(interfaceInfo);
    }

    @Override
    public void offlineInterfaceInfo(IdRequest request) {
        //校验接口信息是否存在
        Long id = request.getId();
        InterfaceInfo interfaceInfo = this.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(interfaceInfo),
                HttpStatus.NOT_FOUND,"接口信息不存在");
        //下线接口
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getStatus());
        this.updateById(interfaceInfo);
    }

    @Override
    public Object invokeInterfaceInfo(InterfaceInfoInvokeRequest request) {
        //校验接口信息是否存在
        Long id = request.getId();
        InterfaceInfo interfaceInfo = this.getById(id);
        ThrowUtils.throwIf(ObjectUtil.isNull(interfaceInfo),
                HttpStatus.NOT_FOUND,"接口信息不存在");
        //校验接口是否正常
        ThrowUtils.throwIf(InterfaceInfoStatusEnum.OFFLINE.getStatus().equals(interfaceInfo.getStatus()),
                HttpStatus.ERROR,"接口未上线");
        //取出传入参数进行转换
        // TODO 开发阶段参数和调用的接口暂时写死，后续修改
        String userRequestParams = request.getUserRequestParams();
        Gson gson = new Gson();
        User user = gson.fromJson(userRequestParams, User.class);
        //获取当前登录用户ak、sk
        LoginUser loginUser = userService.getCurrentUser();
        String accessKey = loginUser.getUser().getAccessKey();
        String secretKey = loginUser.getUser().getSecretKey();
        //这里不能用引入的客户端，因为开发模式下ak、sk都是配置死的
        WebApiClient client = new WebApiClient(accessKey, secretKey);
        //调用接口
        String result;
        try {
            result = client.getNameByPostJson(user);
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.ERROR,"发生未知错误，接口调用失败",e);
        }
        return result;
    }
}




