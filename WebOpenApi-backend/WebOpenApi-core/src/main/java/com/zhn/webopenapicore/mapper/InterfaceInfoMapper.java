package com.zhn.webopenapicore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhn.webopenapicommon.model.domain.InterfaceInfo;
import com.zhn.webopenapicore.model.vo.api.InterfaceMeVo;
import com.zhn.webopenapicore.model.vo.api.InterfaceRankVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface Interface info mapper.
 *
 * @author zhanh
 * @description 针对表 【interface_info(接口信息表)】的数据库操作Mapper
 * @createDate 2023 -06-13 22:21:40
 * @Entity com.zhn.webopenapibackend.model.domain.InterfaceInfo
 */
public interface InterfaceInfoMapper extends BaseMapper<InterfaceInfo> {
    /**
     * 获取接口调用次数统计信息
     *
     * @return the statistic rank list
     */
    List<InterfaceRankVo> getStatisticRankList();

    /**
     * 获取登录用户开通的所有接口（不包括下线的、被拉黑的）.
     *
     * @param userId the user id
     * @param search the search
     * @return the me vo list
     */
    List<InterfaceMeVo> getMeVoList(@Param("userId") Long userId, @Param("search") String search);
}




