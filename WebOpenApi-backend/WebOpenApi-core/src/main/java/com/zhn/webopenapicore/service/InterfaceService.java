package com.zhn.webopenapicore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhn.webopenapicommon.model.domain.InterfaceInfo;
import com.zhn.webopenapicore.model.request.IdRequest;
import com.zhn.webopenapicore.model.request.PageRequest;
import com.zhn.webopenapicore.model.request.api.*;
import com.zhn.webopenapicore.model.vo.InterfaceInfoMeVo;
import com.zhn.webopenapicore.model.vo.InterfaceInfoStoreVo;
import com.zhn.webopenapicore.model.vo.InterfaceInfoVo;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * The interface Interface info service.
 *
 * @author zhanh
 * @description 针对表 【interface_info(接口信息表)】的数据库操作Service
 * @createDate 2023 -06-13 22:21:40
 */
public interface InterfaceService extends IService<InterfaceInfo> {

    /**
     * 添加接口（仅管理员）
     *
     * @param request the request
     * @return the boolean
     */
    boolean addInterface(InterfaceAddRequest request);

    /**
     * 删除接口（仅管理员）
     *
     * @param ids the ids
     * @return the boolean
     */
    boolean deleteByIds(List<Long> ids);

    /**
     * 修改接口（仅管理员）
     *
     * @param request the request
     * @return the boolean
     */
    boolean updateInterface(InterfaceUpdateRequest request);

    /**
     * 查询接口信息
     *
     * @param id the id
     * @return the vo by id
     */
    InterfaceInfoVo getVoById(Long id);

    /**
     * 查询接口分页信息
     *
     * @param request the request
     * @return the vo page
     */
    Page<InterfaceInfoVo> getVoPage(InterfaceQueryRequest request);

    /**
     * 接口下线服务
     *
     * @param request the request
     */
    void onlineInterface(IdRequest request);

    /**
     * 接口上线服务
     *
     * @param request the request
     */
    void offlineInterface(IdRequest request);

    /**
     * 接口调用服务
     *
     * @param invokeRequest the invoke request
     * @param request       the request
     * @return the object
     */
    Object invokeInterface(InterfaceInvokeRequest invokeRequest, HttpServletRequest request);

    /**
     * 接口申请服务
     *
     * @param request the request
     */
    void applyInterface(InterfaceApplyRequest request);

    /**
     * 校验接口是否存在，存在返回，不存在报异常
     *
     * @param id the id
     * @return the boolean
     */
    InterfaceInfo validateInterface(Long id);

    List<InterfaceInfoMeVo> getMeVoList();

    Page<InterfaceInfoStoreVo> getStoreVoPage(InterfaceQueryRequest request);
}
