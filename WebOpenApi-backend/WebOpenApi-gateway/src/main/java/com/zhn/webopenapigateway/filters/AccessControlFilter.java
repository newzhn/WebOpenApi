package com.zhn.webopenapigateway.filters;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
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
        ServerHttpResponse response = exchange.getResponse();
        //访问控制，只有白名单内的ip才能放行
        String host = Objects.requireNonNull(request.getLocalAddress()).getHostString();
        if (!whiteList.contains(host)) {
            return handleNoAuth(response);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -9;
    }

    private Mono<Void> handleNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }
}
