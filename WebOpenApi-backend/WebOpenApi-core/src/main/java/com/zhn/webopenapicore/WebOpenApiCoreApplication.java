package com.zhn.webopenapicore;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/10 14:19
 * @blog www.zhnblog.icu
 */
@SpringBootApplication
@MapperScan("com.zhn.webopenapicore.mapper")
@EnableDubbo
public class WebOpenApiCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebOpenApiCoreApplication.class, args);
    }
}
