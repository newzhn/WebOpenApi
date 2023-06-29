package com.zhn.webopenapibackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.model.domain.LoginUser;
import com.zhn.webopenapibackend.model.request.user.UserAddRequest;
import com.zhn.webopenapibackend.model.request.user.UserQueryRequest;
import com.zhn.webopenapibackend.model.request.user.UserUpdateRequest;
import com.zhn.webopenapibackend.model.vo.UserVo;
import com.zhn.webopenapibackend.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/6/26 14:00
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public AjaxResult addUser(@Valid @RequestBody UserAddRequest request) {
        boolean result = userService.addUser(request);
        return AjaxResult.result(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public AjaxResult deleteUser(@NotNull(message = "删除用户的Id不能为空") @PathVariable("id") Long id) {
        boolean result = userService.deleteById(id);
        return AjaxResult.result(result);
    }

    @PutMapping
    public AjaxResult updateUser(@Valid @RequestBody UserUpdateRequest request) {
        boolean result = userService.updateUser(request);
        return AjaxResult.result(result);
    }

    @GetMapping("/{id}")
    public AjaxResult getUserInfo(@NotNull(message = "查询用户的Id不能为空") @PathVariable("id") Long id) {
        UserVo userVo = userService.getVoById(id);
        return AjaxResult.success(userVo);
    }

    @PostMapping("/list/page/vo")
    @PreAuthorize("hasRole('admin')")
    public AjaxResult getUserListVoByPage(@RequestBody UserQueryRequest request) {
        Page<UserVo> page = userService.getVoPage(request);
        return AjaxResult.success(page);
    }

    @GetMapping("/current")
    public AjaxResult getCurrentUser() {
        LoginUser loginUser = userService.getCurrentUser();
        return AjaxResult.success(loginUser);
    }
}
