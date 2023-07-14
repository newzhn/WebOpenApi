package com.zhn.webopenapicore.service;

import com.zhn.webopenapicore.model.vo.InterfaceInfoRankVo;

import java.util.List;

/**
 * 统计服务
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/13 22:30
 * @blog www.zhnblog.icu
 */
public interface StatisticsService {
    /**
     * 获取接口调用次数排行榜
     *
     * @return the statistic rank list
     */
    List<InterfaceInfoRankVo> getRankVoList();
}
