package com.zhn.webopenapiclientsdk;

import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapiclientsdk.facade.ApiClientFacade;
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
    private String gatewayHost;

    @Bean
    public ApiClientFacade webApiClient() {
        return StrUtil.isNotBlank(gatewayHost)
                ? new ApiClientFacade(accessKey,secretKey)
                : new ApiClientFacade(accessKey, secretKey, gatewayHost);
    }

}
