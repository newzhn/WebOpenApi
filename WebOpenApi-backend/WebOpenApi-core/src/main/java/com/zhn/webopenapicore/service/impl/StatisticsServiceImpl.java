package com.zhn.webopenapicore.service.impl;

import com.zhn.webopenapicore.mapper.InterfaceInfoMapper;
import com.zhn.webopenapicore.model.vo.InterfaceRankVo;
import com.zhn.webopenapicore.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/13 22:31
 * @blog www.zhnblog.icu
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public List<InterfaceRankVo> getRankVoList() {
        return interfaceInfoMapper.getStatisticRankList();
    }
}
