package com.zhn.webopenapigateway.handler;

import com.alibaba.fastjson.JSON;
import com.zhn.webopenapicommon.model.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 网关层面全局异常处理器
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/7 17:55
 * @blog www.zhnblog.icu
 */
@Component
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }
        // 设置响应状态码为 500
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        // 设置响应头
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        // 构建自定义结果类
        Result result = Result.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        // 将结果类转换为 JSON 字符串
        String responseBody = JSON.toJSONString(result);
        DataBuffer buffer = response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8));
        // 返回响应
        return response.writeWith(Mono.just(buffer));
    }

}
