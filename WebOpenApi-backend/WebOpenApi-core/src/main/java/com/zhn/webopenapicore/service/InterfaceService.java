package com.zhn.webopenapicore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhn.webopenapicommon.model.domain.InterfaceInfo;
import com.zhn.webopenapicore.model.request.IdRequest;
import com.zhn.webopenapicore.model.request.api.*;
import com.zhn.webopenapicore.model.vo.api.InterfaceDetailVo;
import com.zhn.webopenapicore.model.vo.api.InterfaceMeVo;
import com.zhn.webopenapicore.model.vo.api.InterfaceStoreVo;
import com.zhn.webopenapicore.model.vo.api.InterfaceInfoVo;

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
     * 接口上线服务
     *
     * @param request the request
     */
    void onlineInterface(IdRequest request);

    /**
     * 接口下线服务
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

    /**
     * 获取登录用户开通的所有接口（不包括下线的、被拉黑的）.
     *
     * @param search the search
     * @return the me vo list
     */
    List<InterfaceMeVo> getMeVoList(String search);

    /**
     * 获取首页中api商店展示的分页接口数据.
     *
     * @param request the request
     * @return the store vo page
     */
    Page<InterfaceStoreVo> getStoreVoPage(InterfaceQueryRequest request);

    /**
     * 获取接口详细信息（包括剩余调用次数等）.
     *
     * @param id the id
     * @return the detail vo by id
     */
    InterfaceDetailVo getDetailVoById(Long id);
}
