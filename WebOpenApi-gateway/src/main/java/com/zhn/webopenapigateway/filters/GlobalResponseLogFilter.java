package com.zhn.webopenapigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局响应日志过滤器
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/7 20:40
 * @blog www.zhnblog.icu
 */
@Slf4j
@Component
@ConditionalOnProperty(value = "web-open-api.gateway.log.response.enabled", havingValue = "true", matchIfMissing = true)
public class GlobalResponseLogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        try {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse originalResponse = exchange.getResponse();
            //缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            //拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();
            if(statusCode == HttpStatus.OK){
                //装饰、增强能力的
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //往返回值里写数据
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                // TODO 调用成功，接口调用次数加一

                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                //打印响应日志
                                printResponseLog(content,request,originalResponse);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            //降级处理返回数据
            return chain.filter(exchange);
        }catch (Exception e){
            log.error("网关处理响应异常.\n" + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -7;
    }

    private void printResponseLog(byte[] content,ServerHttpRequest request,ServerHttpResponse response) {
        // 构建日志
        String data = new String(content, StandardCharsets.UTF_8);
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        // 打印请求路径
        String path = request.getPath().pathWithinApplication().value();
        String requestUrl = UriComponentsBuilder.fromPath(path).queryParams(queryParams).build().toUriString();
        // 构建成一条长日志
        StringBuilder responseLog = new StringBuilder(200);
        // 日志参数
        List<Object> responseArgs = new ArrayList<>();
        responseLog.append("\n================ Gateway Response Start  ================\n");
        responseLog.append("<=== id: {}\n");
        responseArgs.add(request.getId());
        //请求类型、UrI get: /xxx/xxx/xxx?a=b
        responseLog.append("<=== method: {}\n");
        String requestMethod = request.getMethodValue();
        responseArgs.add(requestMethod);
        responseLog.append("<=== uri: {}\n");
        responseArgs.add(requestUrl);
        //状态码
        responseLog.append("<=== code: {}\n");
        responseArgs.add(response.getStatusCode().value());
        //响应结果
        responseLog.append("<=== data: {}\n");
        responseArgs.add(data);
        responseLog.append("================  Gateway Response End  =================\n");
        // 打印执行时间
        log.info(responseLog.toString(), responseArgs.toArray());
    }

    private Mono<Void> handleInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}
