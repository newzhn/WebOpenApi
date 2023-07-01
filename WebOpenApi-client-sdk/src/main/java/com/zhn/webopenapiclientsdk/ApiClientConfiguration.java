package com.zhn.webopenapiclientsdk;

import com.zhn.webopenapiclientsdk.client.WebApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/1 22:06
 * @blog www.zhnblog.icu
 */
@Configuration
@ComponentScan
@ConfigurationProperties("web-open-api.client")
@Data
public class ApiClientConfiguration {
    private String accessKey;
    private String secretKey;

    @Bean
    public WebApiClient webApiClient() {
        return new WebApiClient(accessKey,secretKey);
    }

}
