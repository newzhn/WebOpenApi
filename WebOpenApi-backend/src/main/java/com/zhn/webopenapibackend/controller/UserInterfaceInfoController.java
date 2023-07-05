package com.zhn.webopenapibackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.model.request.user_api.UserInterfaceInfoAddRequest;
import com.zhn.webopenapibackend.model.request.user_api.UserInterfaceInfoQueryRequest;
import com.zhn.webopenapibackend.model.request.user_api.UserInterfaceInfoUpdateRequest;
import com.zhn.webopenapibackend.model.vo.UserInterfaceInfoVo;
import com.zhn.webopenapibackend.service.UserInterfaceInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/5 15:43
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/user_interface")
@Validated
public class UserInterfaceInfoController {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    /**
     * Add
     *
     * @param request the request
     * @return the ajax result
     */
    @PostMapping
    public AjaxResult addUserInterface(@Valid @RequestBody UserInterfaceInfoAddRequest request) {
        boolean result = userInterfaceInfoService.addInterface(request);
        return AjaxResult.result(result);
    }

    /**
     * Delete
     *
     * @param ids the ids
     * @return the ajax result
     */
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasRole('admin')")
    public AjaxResult deleteUserInterfaceByIds(@NotNull(message = "删除接口的Id不能为空") @PathVariable("ids") Long[] ids) {
        boolean result = userInterfaceInfoService.deleteByIds(Arrays.asList(ids));
        return AjaxResult.result(result);
    }

    /**
     * Update
     *
     * @param request the request
     * @return the ajax result
     */
    @PutMapping
    @PreAuthorize("hasRole('admin')")
    public AjaxResult updateUserInterface(@Valid @RequestBody UserInterfaceInfoUpdateRequest request) {
        boolean result = userInterfaceInfoService.updateInterface(request);
        return AjaxResult.result(result);
    }

    /**
     * Get
     *
     * @param id the id
     * @return the interface info
     */
    @GetMapping("/{id}")
    public AjaxResult getUserInterfaceInfo(@NotNull(message = "查询接口的Id不能为空") @PathVariable("id") Long id) {
        UserInterfaceInfoVo userInterfaceVo = userInterfaceInfoService.getVoById(id);
        return AjaxResult.success(userInterfaceVo);
    }

    /**
     * Get page.
     *
     * @param request the request
     * @return the interface list vo by page
     */
    @PostMapping("/list/page/vo")
    public AjaxResult getUserInterfaceListVoByPage(@RequestBody UserInterfaceInfoQueryRequest request) {
        Page<UserInterfaceInfoVo> page = userInterfaceInfoService.getVoPage(request);
        return AjaxResult.success(page);
    }
}
