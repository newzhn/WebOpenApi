package com.zhn.webopenapigateway.filters;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapiclientsdk.utils.SignUtil;
import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapicommon.service.RpcUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * api鉴权过滤器
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/7 20:32
 * @blog www.zhnblog.icu
 */
@Component
public class ApiAuthenticationFilter implements GlobalFilter, Ordered {
    private static final long FIVE_MINUTES = 60 * 5L;

    @DubboReference
    private RpcUserService rpcUserService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String body = headers.getFirst("body");
        String sign = headers.getFirst("sign");
        //非空校验
        if (!StrUtil.isAllNotBlank(accessKey,nonce,timestamp,sign)) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"未分配密钥，无权限访问");
        }
        //查询分配给该用户的密钥校验accessKey
        User user = rpcUserService.getUserByAccessKey(accessKey);
        if (ObjectUtil.isNull(user)) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"未分配密钥，无权限访问");
        }
        // TODO 查询数据库或缓存校验随机数，防止重放

        //校验时间戳，超出五分钟的不放行
        long currentTime = System.currentTimeMillis() / 1000;
        if (currentTime - Long.parseLong(timestamp) >= FIVE_MINUTES) {
            // TODO 定时清除后台保存的随机数

            throw new BusinessException(HttpStatus.FORBIDDEN,"无权限访问");
        }
        //进行签名比对
        String genSign = SignUtil.genSign(body, user.getSecretKey());
        if (!genSign.equals(sign)) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"api签名认证失败，无权限访问");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -8;
    }

}
