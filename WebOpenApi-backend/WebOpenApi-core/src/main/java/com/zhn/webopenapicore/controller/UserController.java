package com.zhn.webopenapicore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.model.LoginUser;
import com.zhn.webopenapicore.model.request.user.UserAddRequest;
import com.zhn.webopenapicore.model.request.user.UserQueryRequest;
import com.zhn.webopenapicore.model.request.user.UserUpdateRequest;
import com.zhn.webopenapicore.model.vo.UserVo;
import com.zhn.webopenapicore.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Result addUser(@Valid @RequestBody UserAddRequest request) {
        boolean result = userService.addUser(request);
        return Result.result(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public Result deleteUser(@NotNull(message = "删除用户的Id不能为空") @PathVariable("id") Long id) {
        boolean result = userService.deleteById(id);
        return Result.result(result);
    }

    @PutMapping
    public Result updateUser(@Valid @RequestBody UserUpdateRequest request) {
        boolean result = userService.updateUser(request);
        return Result.result(result);
    }

    @GetMapping("/{id}")
    public Result getUserInfo(@NotNull(message = "查询用户的Id不能为空") @PathVariable("id") Long id) {
        UserVo userVo = userService.getVoById(id);
        return Result.success(userVo);
    }

    @PostMapping("/all/vo/page")
    @PreAuthorize("hasRole('admin')")
    public Result getUserListVoByPage(@RequestBody UserQueryRequest request) {
        Page<UserVo> page = userService.getVoPage(request);
        return Result.success(page);
    }

    @GetMapping("/current")
    public Result getCurrentUser() {
        LoginUser loginUser = userService.getCurrentUser();
        return Result.success(loginUser);
    }
}
