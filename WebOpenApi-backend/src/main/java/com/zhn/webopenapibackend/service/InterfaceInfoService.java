package com.zhn.webopenapibackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapibackend.model.domain.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoAddRequest;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoQueryRequest;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoUpdateRequest;
import com.zhn.webopenapibackend.model.vo.InterfaceInfoVo;

import java.util.List;

/**
 * The interface Interface info service.
 *
 * @author zhanh
 * @description 针对表 【interface_info(接口信息表)】的数据库操作Service
 * @createDate 2023 -06-13 22:21:40
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    /**
     * 添加接口（仅管理员）
     *
     * @param request the request
     * @return the boolean
     */
    boolean addInterface(InterfaceInfoAddRequest request);

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
    boolean updateInterface(InterfaceInfoUpdateRequest request);

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
    Page<InterfaceInfoVo> getVoPage(InterfaceInfoQueryRequest request);
}
