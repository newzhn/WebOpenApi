package com.zhn.webopenapicore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 校验配置
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/26 20:35
 * @blog www.zhnblog.icu
 */
@Configuration
public class ValidationConfig {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
