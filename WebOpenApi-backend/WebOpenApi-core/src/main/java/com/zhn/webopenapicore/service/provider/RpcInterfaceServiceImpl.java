package com.zhn.webopenapicore.service.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhn.webopenapicommon.model.domain.InterfaceInfo;
import com.zhn.webopenapicommon.service.RpcInterfaceService;
import com.zhn.webopenapicore.mapper.InterfaceInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/10 20:20
 * @blog www.zhnblog.icu
 */
@DubboService
public class RpcInterfaceServiceImpl implements RpcInterfaceService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getOneByMethodAndUri(String method, String uri) {
        LambdaQueryWrapper<InterfaceInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(InterfaceInfo::getMethod, method);
        wrapper.eq(InterfaceInfo::getUri, uri);
        return interfaceInfoMapper.selectOne(wrapper);
    }

    @Override
    public Map<String, String> getAllInterfaceInfoMap() {
        List<InterfaceInfo> interfaceInfos = interfaceInfoMapper.selectList(null);
        // 对查询出来的所有接口按照host进行分组处理，获取每组URI公共前缀
        return interfaceInfos.stream()
                .collect(Collectors.groupingBy(InterfaceInfo::getHost,
                        Collectors.collectingAndThen(Collectors.toList(), this::getCommonPrefix)));
    }

    private String getCommonPrefix(List<InterfaceInfo> interfaceInfos) {
        if (interfaceInfos == null || interfaceInfos.isEmpty()) {
            return "/**";
        }
        List<String> list = interfaceInfos.stream()
                .map(InterfaceInfo::getUri).collect(Collectors.toList());
        String res = list.get(0);
        for(int i = 1;i < list.size();i++) {
            while (list.get(i).indexOf(res) == -1) {
                res = res.substring(0,res.length() - 1);
            }
        }
        StringBuilder commonPrefix = new StringBuilder(res);
        if (commonPrefix.length() > 0 && commonPrefix.charAt(commonPrefix.length() - 1) != '/') {
            return commonPrefix.toString();
        }
        if (commonPrefix.length() == 0) {
            commonPrefix.append("/");
        }
        return commonPrefix.append("**").toString();
    }
}
