package com.zhn.webopenapibackend;

import com.zhn.webopenapibackend.utils.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WebOpenApiBackendApplicationTests {
    @Resource
    private RedisCache redisCache;

    @Test
    void contextLoads() {
        redisCache.setCacheObject("name","张三");
        System.out.println((String) redisCache.getCacheObject("name"));
    }

}
