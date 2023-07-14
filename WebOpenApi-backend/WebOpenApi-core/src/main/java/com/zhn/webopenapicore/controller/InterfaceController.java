package com.zhn.webopenapicore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.model.request.IdRequest;
import com.zhn.webopenapicore.model.request.api.*;
import com.zhn.webopenapicore.model.vo.InterfaceInfoMeVo;
import com.zhn.webopenapicore.model.vo.InterfaceInfoStoreVo;
import com.zhn.webopenapicore.model.vo.InterfaceInfoVo;
import com.zhn.webopenapicore.service.InterfaceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * api接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /6/26 21:56
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/interface")
public class InterfaceController {
    @Resource
    private InterfaceService interfaceService;

    /**
     * Add
     *
     * @param request the request
     * @return the ajax result
     */
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Result addInterface(@Valid @RequestBody InterfaceAddRequest request) {
        boolean result = interfaceService.addInterface(request);
        return Result.result(result);
    }

    /**
     * Delete
     *
     * @param ids the ids
     * @return the ajax result
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasRole('admin')")
    public Result deleteInterfaceByIds(@NotNull(message = "删除接口的Id不能为空") @PathVariable("ids") Long[] ids) {
        boolean result = interfaceService.deleteByIds(Arrays.asList(ids));
        return Result.result(result);
    }

    /**
     * Update
     *
     * @param request the request
     * @return the ajax result
     */
    @PutMapping
    @PreAuthorize("hasRole('admin')")
    public Result updateInterface(@Valid @RequestBody InterfaceUpdateRequest request) {
        boolean result = interfaceService.updateInterface(request);
        return Result.result(result);
    }

    /**
     * 获取某个接口详细信息
     *
     * @param id the id
     * @return the interface info
     */
    @GetMapping("/{id}")
    public Result getInterfaceInfo(@NotNull(message = "查询接口的Id不能为空") @PathVariable("id") Long id) {
        InterfaceInfoVo interfaceVo = interfaceService.getVoById(id);
        return Result.success(interfaceVo);
    }

    /**
     * 获取所有接口的分页数据，用于管理
     *
     * @param request the request
     * @return the interface list vo by page
     */
    @PostMapping("/all/vo/page")
    @PreAuthorize("hasRole('admin')")
    public Result getInterfaceListVoByPage(@RequestBody InterfaceQueryRequest request) {
        Page<InterfaceInfoVo> page = interfaceService.getVoPage(request);
        return Result.success(page);
    }

    /**
     * 获取首页接口商店信息
     *
     * @return the interface store vo by page
     */
    @PostMapping("/store/vo/page")
    public Result getInterfaceStoreVoByPage(@RequestBody InterfaceQueryRequest request) {
        Page<InterfaceInfoStoreVo> page = interfaceService.getStoreVoPage(request);
        return Result.success(page);
    }

    /**
     * 获取当前登录用户已申请的接口
     *
     * @return the interface me vo by page
     */
    @GetMapping("/me/vo/list")
    public Result getInterfaceMeVoList() {
        List<InterfaceInfoMeVo> list = interfaceService.getMeVoList();
        return Result.success(list);
    }

    /**
     * 接口上线
     *
     * @param request the request
     * @return the result
     */
    @PutMapping("/online")
    @PreAuthorize("hasRole('admin')")
    public Result onlineInterface(@Valid @RequestBody IdRequest request) {
        interfaceService.onlineInterface(request);
        return Result.success();
    }

    /**
     * 接口下线
     *
     * @param request the request
     * @return the result
     */
    @PutMapping("/offline")
    @PreAuthorize("hasRole('admin')")
    public Result offlineInterface(@Valid @RequestBody IdRequest request) {
        interfaceService.offlineInterface(request);
        return Result.success();
    }

    /**
     * 接口调用
     *
     * @param invokeRequest the invoke request
     * @param request       the request
     * @return the result
     */
    @PostMapping("/invoke")
    public Result invokeInterface(@Valid @RequestBody InterfaceInvokeRequest invokeRequest,
                                      HttpServletRequest request) {
        Object result = interfaceService.invokeInterface(invokeRequest,request);
        return Result.success(result);
    }

    /**
     * 接口申请
     *
     * @param request the request
     * @return the result
     */
    @PostMapping("/apply")
    public Result applyInterface(@Valid @RequestBody InterfaceApplyRequest request) {
        interfaceService.applyInterface(request);
        return Result.success();
    }
}
