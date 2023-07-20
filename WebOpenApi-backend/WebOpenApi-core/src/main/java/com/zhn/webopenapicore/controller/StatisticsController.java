package com.zhn.webopenapicore.controller;

import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.service.StatisticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 接口统计
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/13 22:30
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/interface/statistic")
public class StatisticsController {
    @Resource
    private StatisticsService statisticsService;

    /**
     * 统计排序所有上线接口调用次数
     *
     * @return the rank interface info vo list
     */
    @GetMapping("/rank/vo/list")
    @PreAuthorize("hasRole('admin')")
    public Result getRankInterfaceInfoVoList() {
        return Result.success(statisticsService.getRankVoList());
    }
}
