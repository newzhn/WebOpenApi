package com.zhn.webopenapibackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoAddRequest;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoQueryRequest;
import com.zhn.webopenapibackend.model.request.api.InterfaceInfoUpdateRequest;
import com.zhn.webopenapibackend.model.vo.InterfaceInfoVo;
import com.zhn.webopenapibackend.service.InterfaceInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * api接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/26 21:56
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/interface")
public class InterfaceInfoController {
    @Resource
    private InterfaceInfoService interfaceInfoService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public AjaxResult addInterface(@Valid @RequestBody InterfaceInfoAddRequest request) {
        boolean result = interfaceInfoService.addInterface(request);
        return AjaxResult.result(result);
    }

    @DeleteMapping("/{ids}")
    @PreAuthorize("hasRole('admin')")
    public AjaxResult deleteInterfaceByIds(@NotNull(message = "删除接口的Id不能为空") @PathVariable("ids") Long[] ids) {
        boolean result = interfaceInfoService.deleteByIds(Arrays.asList(ids));
        return AjaxResult.result(result);
    }

    @PutMapping
    @PreAuthorize("hasRole('admin')")
    public AjaxResult updateInterface(@Valid @RequestBody InterfaceInfoUpdateRequest request) {
        boolean result = interfaceInfoService.updateInterface(request);
        return AjaxResult.result(result);
    }

    @GetMapping("/{id}")
    public AjaxResult getInterfaceInfo(@NotNull(message = "查询接口的Id不能为空") @PathVariable("id") Long id) {
        InterfaceInfoVo interfaceVo = interfaceInfoService.getVoById(id);
        return AjaxResult.success(interfaceVo);
    }

    @PostMapping("/list/page/vo")
    public AjaxResult getInterfaceListVoByPage(@RequestBody InterfaceInfoQueryRequest request) {
        Page<InterfaceInfoVo> page = interfaceInfoService.getVoPage(request);
        return AjaxResult.success(page);
    }
}