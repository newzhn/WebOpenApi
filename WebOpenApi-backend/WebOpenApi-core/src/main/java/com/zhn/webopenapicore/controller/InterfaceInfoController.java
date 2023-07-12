package com.zhn.webopenapicore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.model.request.IdRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoAddRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoInvokeRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoQueryRequest;
import com.zhn.webopenapicore.model.request.api.InterfaceInfoUpdateRequest;
import com.zhn.webopenapicore.model.vo.InterfaceInfoVo;
import com.zhn.webopenapicore.service.InterfaceInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

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
public class InterfaceInfoController {
    @Resource
    private InterfaceInfoService interfaceInfoService;

    /**
     * Add
     *
     * @param request the request
     * @return the ajax result
     */
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Result addInterface(@Valid @RequestBody InterfaceInfoAddRequest request) {
        boolean result = interfaceInfoService.addInterface(request);
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
        boolean result = interfaceInfoService.deleteByIds(Arrays.asList(ids));
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
    public Result updateInterface(@Valid @RequestBody InterfaceInfoUpdateRequest request) {
        boolean result = interfaceInfoService.updateInterface(request);
        return Result.result(result);
    }

    /**
     * Get
     *
     * @param id the id
     * @return the interface info
     */
    @GetMapping("/{id}")
    public Result getInterfaceInfo(@NotNull(message = "查询接口的Id不能为空") @PathVariable("id") Long id) {
        InterfaceInfoVo interfaceVo = interfaceInfoService.getVoById(id);
        return Result.success(interfaceVo);
    }

    /**
     * Get page.
     *
     * @param request the request
     * @return the interface list vo by page
     */
    @PostMapping("/list/page/vo")
    public Result getInterfaceListVoByPage(@RequestBody InterfaceInfoQueryRequest request) {
        Page<InterfaceInfoVo> page = interfaceInfoService.getVoPage(request);
        return Result.success(page);
    }

    /**
     * 接口上线
     *
     * @param request the request
     * @return the ajax result
     */
    @PutMapping("/online")
    public Result onlineInterfaceInfo(@Valid @RequestBody IdRequest request) {
        interfaceInfoService.onlineInterfaceInfo(request);
        return Result.success();
    }

    /**
     * 接口下线
     *
     * @param request the request
     * @return the ajax result
     */
    @PutMapping("/offline")
    public Result offlineInterfaceInfo(@Valid @RequestBody IdRequest request) {
        interfaceInfoService.offlineInterfaceInfo(request);
        return Result.success();
    }

    /**
     * 接口调用
     *
     * @param request the request
     * @return the ajax result
     */
    @PostMapping("/invoke")
    public Result invokeInterfaceInfo(@Valid @RequestBody InterfaceInfoInvokeRequest invokeRequest,
                                      HttpServletRequest request) {
        Object result = interfaceInfoService.invokeInterfaceInfo(invokeRequest,request);
        return Result.success(result);
    }
}
