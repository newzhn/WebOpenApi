package com.zhn.webopenapigateway.config;

import com.zhn.webopenapicommon.service.RpcInterfaceService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 网关路由配置
 * 在服务器初始化时加载数据库接口信息进行路由配置
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/12 14:02
 * @blog www.zhnblog.icu
 */
@Configuration
public class RouteConfig {
    @DubboReference
    private RpcInterfaceService rpcInterfaceInfoService;

    @Bean
    public RouteLocator customizeRoute(RouteLocatorBuilder builder) {
        //调用rpc接口获取接口host和uri公共前缀信息
        Map<String, String> map = rpcInterfaceInfoService.getAllInterfaceInfoMap();
        RouteLocatorBuilder.Builder routes = builder.routes();
        for(Map.Entry<String, String> entry : map.entrySet()) {
            String host = entry.getKey();
            String uri = entry.getValue();
            routes.route(r -> r.path(uri).uri(host));
        }
        return routes.build();
    }
}
