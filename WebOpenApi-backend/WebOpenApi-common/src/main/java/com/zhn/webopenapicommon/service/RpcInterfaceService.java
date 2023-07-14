package com.zhn.webopenapicommon.service;

import com.zhn.webopenapicommon.model.domain.InterfaceInfo;

import java.util.Map;

/**
 * 接口相关的Rpc接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023 /7/10 20:18
 * @blog www.zhnblog.icu
 */
public interface RpcInterfaceService {
    /**
     * 根据请求method and uri获取对应接口信息
     *
     * @param method the method
     * @param uri    the uri
     * @return the one by method and uri
     */
    InterfaceInfo getOneByMethodAndUri(String method, String uri);

    /**
     * Gets all interface info map.
     *
     * @return the all interface info map
     */
    Map<String,String> getAllInterfaceInfoMap();
}
