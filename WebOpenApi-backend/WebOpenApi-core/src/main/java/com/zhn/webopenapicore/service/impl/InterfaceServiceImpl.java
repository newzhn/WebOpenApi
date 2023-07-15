package com.zhn.webopenapicore.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapiclientsdk.facade.ApiClientFacade;
import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.domain.InterfaceInfo;
import com.zhn.webopenapicommon.model.domain.UserInterfaceInfo;
import com.zhn.webopenapicommon.utils.ThrowUtil;
import com.zhn.webopenapicore.mapper.InterfaceInfoMapper;
import com.zhn.webopenapicore.model.LoginUser;
import com.zhn.webopenapicore.model.eneum.InterfaceStatus;
import com.zhn.webopenapicore.model.request.IdRequest;
import com.zhn.webopenapicore.model.request.api.*;
import com.zhn.webopenapicore.model.vo.InterfaceDetailVo;
import com.zhn.webopenapicore.model.vo.InterfaceMeVo;
import com.zhn.webopenapicore.model.vo.InterfaceStoreVo;
import com.zhn.webopenapicore.model.vo.InterfaceInfoVo;
import com.zhn.webopenapicore.service.InterfaceService;
import com.zhn.webopenapicore.service.UserInterfaceService;
import com.zhn.webopenapicore.service.UserService;
import com.zhn.webopenapicore.utils.bean.BeanUtils;
import com.zhn.webopenapicore.utils.string.JSONUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author zhanh
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2023-06-13 22:21:40
*/
@Service
public class InterfaceServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceService {
    @Resource
    private InterfaceInfoMapper interfaceMapper;
    @Resource
    private UserService userService;
    @Resource
    private UserInterfaceService userInterfaceService;
    @Resource
    private ApiClientFacade apiClientFacade;

    @Override
    public boolean addInterface(InterfaceAddRequest request) {
        // TODO 校验接口是否存在
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
    public boolean updateInterface(InterfaceUpdateRequest request) {
        //校验接口是否存在
        this.validateInterface(request.getId());
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
    public Page<InterfaceInfoVo> getVoPage(InterfaceQueryRequest request) {
        // 拼接查询条件
        LambdaQueryWrapper<InterfaceInfo> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(request.getName())) {
            wrapper.like(InterfaceInfo::getName,request.getName());
        }
        if (StrUtil.isNotBlank(request.getMethod())) {
            wrapper.like(InterfaceInfo::getMethod,request.getMethod());
        }
        if (StrUtil.isNotBlank(request.getUri())) {
            wrapper.like(InterfaceInfo::getUri,request.getUri());
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
    public void onlineInterface(IdRequest request) {
        //校验接口信息是否存在
        InterfaceInfo interfaceInfo = this.validateInterface(request.getId());
        if (InterfaceStatus.ONLINE.getStatus().equals(interfaceInfo.getStatus())) {
            return;
        }
        //校验是否有该接口的调用权限
        UserInterfaceInfo userInterface = userInterfaceService.
                getInfoByInterfaceId(interfaceInfo.getId());
        //没有则去申请权限
        if (ObjectUtil.isNull(userInterface) || userInterface.getSurplusNum() <= 0) {
            userInterfaceService.applyInterface(interfaceInfo.getId(),interfaceInfo.getApplyNum());
        }
        //验证接口是否可用
        String method = interfaceInfo.getMethod();
        String uri = interfaceInfo.getUri();
        Map<String, Object> paramMap = JSONUtil.toMap(interfaceInfo.getRequestParams());
        try {
            apiClientFacade.invoke(method,uri,paramMap);
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.ERROR,"接口验证失败！",e);
        }
        //上线接口
        interfaceInfo.setStatus(InterfaceStatus.ONLINE.getStatus());
        this.updateById(interfaceInfo);
    }

    @Override
    public void offlineInterface(IdRequest request) {
        //校验接口信息是否存在
        InterfaceInfo interfaceInfo = this.validateInterface(request.getId());
        if (InterfaceStatus.OFFLINE.getStatus().equals(interfaceInfo.getStatus())) {
            return;
        }
        //下线接口
        interfaceInfo.setStatus(InterfaceStatus.OFFLINE.getStatus());
        this.updateById(interfaceInfo);
    }

    @Override
    public Object invokeInterface(InterfaceInvokeRequest invokeRequest,
                                  HttpServletRequest request) {
        //校验接口信息是否存在
        InterfaceInfo interfaceInfo = this.validateInterface(invokeRequest.getId());
        //校验接口是否正常
        ThrowUtil.throwIf(InterfaceStatus.OFFLINE.getStatus().equals(interfaceInfo.getStatus()),
                HttpStatus.ERROR,"接口未上线");
        //获取当前登录用户ak、sk
        LoginUser loginUser = userService.getCurrentUser();
        String accessKey = loginUser.getUser().getAccessKey();
        String secretKey = loginUser.getUser().getSecretKey();
        //这里不能用引入的客户端，因为开发模式下ak、sk都是配置死的
        ApiClientFacade client = new ApiClientFacade(accessKey, secretKey);
        //调用接口
        String method = interfaceInfo.getMethod();
        String uri = interfaceInfo.getUri();
        Map<String, Object> paramMap = JSONUtil.
                toMap(invokeRequest.getUserRequestParams());
        String result;
        try {
            result = client.invoke(method,uri,paramMap);
        } catch (Exception e) {
            throw new BusinessException(HttpStatus.ERROR,"接口调用失败",e);
        }
        return result;
    }

    @Override
    public void applyInterface(InterfaceApplyRequest request) {
        //校验接口是否存在
        Long interfaceId = request.getInterfaceInfoId();
        InterfaceInfo interfaceInfo = this.validateInterface(interfaceId);
        //校验是否可以申请接口
        userInterfaceService.validateApply(interfaceId);
        //申请接口
        userInterfaceService.applyInterface(interfaceId,interfaceInfo.getApplyNum());
    }

    @Override
    public InterfaceInfo validateInterface(Long id) {
        InterfaceInfo interfaceInfo = this.getById(id);
        ThrowUtil.throwIf(ObjectUtil.isNull(interfaceInfo),
                HttpStatus.NOT_FOUND,"接口信息不存在");
        return interfaceInfo;
    }

    @Override
    public List<InterfaceMeVo> getMeVoList(String search) {
        Long userId = userService.getCurrentUser().getUser().getId();
        List<InterfaceMeVo> interfaceList = interfaceMapper.getMeVoList(userId,search);
        return interfaceList;
    }

    @Override
    public Page<InterfaceStoreVo> getStoreVoPage(InterfaceQueryRequest request) {
        //获取上线接口分页数据
        request.setStatus(InterfaceStatus.ONLINE.getStatus());
        Page<InterfaceInfoVo> voPage = this.getVoPage(request);
        //获取当前用户已申请的接口Id数据
        List<Long> ids = this.getMeVoList("").stream()
                .map(InterfaceMeVo::getId).collect(Collectors.toList());
        //遍历比对，申请过的或者下线状态的接口applyFlag设置为false
        long current = request.getCurrent();
        long pageSize = request.getPageSize();
        Page<InterfaceStoreVo> storeVoPage = new Page<>(current, pageSize, voPage.getTotal());
        storeVoPage.setRecords(voPage.getRecords().stream().map(vo -> {
            InterfaceStoreVo storeVo = BeanUtils.copy(vo, InterfaceStoreVo.class);
            storeVo.setApplyFlag(!(InterfaceStatus.OFFLINE.getStatus().equals(vo.getStatus()) ||
                    ids.contains(vo.getId())));
            return storeVo;
        }).collect(Collectors.toList()));
        return storeVoPage;
    }

    @Override
    public InterfaceDetailVo getDetailVoById(Long id) {
        InterfaceInfo info = this.getById(id);
        if (info.getStatus().equals(InterfaceStatus.OFFLINE.getStatus())) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"该接口暂时还未上线");
        }
        InterfaceDetailVo detailVo = BeanUtils.copy(info, InterfaceDetailVo.class);
        //查询当前用户剩余调用次数
        UserInterfaceInfo userInterfaceInfo = userInterfaceService.getInfoByInterfaceId(id);
        detailVo.setSurplusNum(userInterfaceInfo.getSurplusNum());
        return detailVo;
    }
}




