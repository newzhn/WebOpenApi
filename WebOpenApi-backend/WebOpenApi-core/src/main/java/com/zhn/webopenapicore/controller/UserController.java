package com.zhn.webopenapicore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.model.vo.user.LoginUser;
import com.zhn.webopenapicore.model.request.user.UserAddRequest;
import com.zhn.webopenapicore.model.request.user.UserQueryRequest;
import com.zhn.webopenapicore.model.request.user.UserUpdateRequest;
import com.zhn.webopenapicore.model.vo.user.UserVo;
import com.zhn.webopenapicore.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 用户接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /6/26 14:00
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Resource
    private UserService userService;

    /**
     * Add user.
     *
     * @param request the request
     * @return the result
     */
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public Result addUser(@Valid @RequestBody UserAddRequest request) {
        boolean result = userService.addUser(request);
        return Result.result(result);
    }

    /**
     * Delete user.
     *
     * @param id the id
     * @return the result
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Result deleteUser(@NotNull(message = "删除用户的Id不能为空") @PathVariable("id") Long id) {
        boolean result = userService.deleteById(id);
        return Result.result(result);
    }

    /**
     * Update user.
     *
     * @param request the request
     * @return the result
     */
    @PutMapping
    public Result updateUser(@Valid @RequestBody UserUpdateRequest request) {
        boolean result = userService.updateUser(request);
        return Result.result(result);
    }

    /**
     * Gets user info.
     *
     * @param id the id
     * @return the user info
     */
    @GetMapping("/{id}")
    public Result getUserInfo(@NotNull(message = "查询用户的Id不能为空") @PathVariable("id") Long id) {
        UserVo userVo = userService.getVoById(id);
        return Result.success(userVo);
    }

    /**
     * Gets user list vo by page.
     *
     * @param request the request
     * @return the user list vo by page
     */
    @PostMapping("/all/vo/page")
    @PreAuthorize("hasRole('admin')")
    public Result getUserListVoByPage(@RequestBody UserQueryRequest request) {
        Page<UserVo> page = userService.getVoPage(request);
        return Result.success(page);
    }

    /**
     * 获取当前登录用户信息.
     *
     * @return the current user
     */
    @GetMapping("/current")
    public Result getCurrentUser() {
        LoginUser loginUser = userService.getCurrentUser();
        return Result.success(loginUser);
    }

    /**
     * 申请api签名密钥.
     *
     * @return the result
     */
    @GetMapping("/applyToken")
    public Result applyUserApiToken() {
        userService.applyApiToken();
        return Result.success();
    }
}
