package com.zhn.webopenapigateway.filters;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapiclientsdk.utils.SignUtil;
import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.domain.InterfaceInfo;
import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapicommon.model.domain.UserInterfaceInfo;
import com.zhn.webopenapicommon.service.RpcInterfaceService;
import com.zhn.webopenapicommon.service.RpcUserInterfaceService;
import com.zhn.webopenapicommon.service.RpcUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
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
    @DubboReference
    private RpcUserInterfaceService rpcUserInterfaceInfoService;
    @DubboReference
    private RpcInterfaceService rpcInterfaceInfoService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String timestamp = headers.getFirst("timestamp");
        String sign = headers.getFirst("sign");
        String uri = headers.getFirst("uri");
        String method = request.getMethodValue();

        //非空校验
        if (!StrUtil.isAllNotBlank(accessKey,timestamp,sign)) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"认证失败，无权限访问");
        }
        //查询分配给该用户的密钥校验accessKey
        User user = rpcUserService.getUserByAccessKey(accessKey);
        if (ObjectUtil.isNull(user)) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"未分配密钥，无权限访问");
        }
        //校验时间戳，超出五分钟的不放行
        long currentTime = System.currentTimeMillis() / 1000;
        if (currentTime - Long.parseLong(timestamp) >= FIVE_MINUTES) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"超时，无权限访问");
        }
        //进行签名比对
        String genSign = SignUtil.genSign(timestamp, user.getSecretKey());
        if (!genSign.equals(sign)) {
            throw new BusinessException(HttpStatus.FORBIDDEN,"api签名认证失败，无权限访问");
        }
        // 使用 Dubbo 进行 RPC 调用查询数据库接口信息
        InterfaceInfo interfaceInfo = rpcInterfaceInfoService.getOneByMethodAndUri(method, uri);
        //校验接口信息是否存在
        if (ObjectUtil.isNull(interfaceInfo)) {
            throw new BusinessException(HttpStatus.NOT_FOUND,"该接口信息不存在");
        }
        // 使用 Dubbo 进行 RPC 调用查询数据库该用户关联的接口数据
        UserInterfaceInfo userInterfaceInfo = rpcUserInterfaceInfoService.
                getOneByInterfaceInfoId(user.getId(), interfaceInfo.getId());
        //校验是否申请了该接口的调用权限以及调用次数是否足够
        if (ObjectUtil.isNull(userInterfaceInfo) || userInterfaceInfo.getSurplusNum() <= 0) {
            throw new BusinessException(HttpStatus.NOT_FOUND,"没有该接口的调用权限，需要去申请调用次数");
        }
        ServerHttpRequest newRequest = request.mutate()
                .header("user_interface_id", userInterfaceInfo.getId().toString())
                .build();
        return chain.filter(exchange.mutate().request(newRequest).build());
    }

    @Override
    public int getOrder() {
        return -8;
    }

}
