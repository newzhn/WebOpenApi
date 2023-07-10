package com.zhn.webopenapigateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/10 14:21
 * @blog www.zhnblog.icu
 */
@SpringBootApplication
@EnableDubbo
public class WebOpenApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebOpenApiGatewayApplication.class, args);
    }
}
