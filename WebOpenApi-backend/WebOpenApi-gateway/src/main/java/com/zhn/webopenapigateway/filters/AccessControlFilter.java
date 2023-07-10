package com.zhn.webopenapigateway.filters;

import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

/**
 * 访问控制过滤器
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/7 17:55
 * @blog www.zhnblog.icu
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "web-open-api.gateway.access-control")
@Component
public class AccessControlFilter implements GlobalFilter, Ordered {
    private boolean enabled;
    private List<String> whiteList;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (!enabled) {
            return chain.filter(exchange);
        }
        ServerHttpRequest request = exchange.getRequest();
        //访问控制，只有白名单内的ip才能放行
        String host = Objects.requireNonNull(request.getLocalAddress()).getHostString();
        if (!whiteList.contains(host)) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"您没有访问权限");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
