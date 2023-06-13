package com.zhn.webopenapibackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhn.webopenapibackend.model.domain.InterfaceInfo;
import com.zhn.webopenapibackend.service.InterfaceInfoService;
import com.zhn.webopenapibackend.mapper.InterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author zhanh
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2023-06-13 22:21:40
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService{

}




