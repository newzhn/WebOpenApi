package com.zhn.webopenapigateway.filters;

import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapicommon.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 全局请求日志过滤器
 *
 * @author zhn
 * @version 1.0
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "web-open-api.gateway.log.request.enabled", havingValue = "true", matchIfMissing = true)
public class GlobalRequestLogFilter implements GlobalFilter, Ordered {
    private static final String AUTH_KEY = "Access-Token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 打印请求路径
        String path = request.getPath().pathWithinApplication().value();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        if (!queryParams.isEmpty()) {
            queryParams.forEach((key,value) -> {

            });
        }
        // 构建成一条长日志，避免并发下日志错乱
        StringBuilder reqLog = new StringBuilder(200);
        // 日志参数
        List<Object> reqArgs = new ArrayList<>();
        reqLog.append("\n================ Gateway Request Start  ================\n");
        // 打印路由添加占位符
        reqLog.append("===> id: {}\n");
        reqArgs.add(request.getId());
        reqLog.append("===> {}: {}\n");
        // 参数
        String requestMethod = request.getMethodValue();
        reqArgs.add(requestMethod);
        reqArgs.add(path);

        // 打印请求头
        HttpHeaders headers = request.getHeaders();
        headers.forEach((headerName, headerValue) -> {
            reqLog.append("===Headers===  {}: {}\n");
            reqArgs.add(headerName);
            //如果有token，可以先把token解析后打印出原始报文，JwtUtil替换成自己项目里工具类
            if (AUTH_KEY.equals(headerName)) {
                String token = headerValue.get(0);
                Claims claims = null;
                try {
                    claims = JwtUtil.parseJWT(token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                reqArgs.add((claims == null) ? "" : claims.toString());
                reqLog.append("===Headers===  {}: {}\n");
                reqArgs.add(headerName.concat("-original"));
                reqArgs.add(StrUtil.join(",",headerValue.toArray()));
            } else {
                reqArgs.add(StrUtil.join(",",headerValue.toArray()));
            }
        });

        reqLog.append("================  Gateway Request End  =================\n");
        // 打印执行时间
        log.info(reqLog.toString(), reqArgs.toArray());
        return chain.filter(exchange);
    }

    private String getOriginalRequestUrl(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        LinkedHashSet<URI> uris = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI requestUri = uris.stream().findFirst().orElse(request.getURI());
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        return UriComponentsBuilder.fromPath(requestUri.getRawPath()).queryParams(queryParams).build().toUriString();
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
