package com.zhn.webopenapibackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhn.webopenapibackend.mapper")
public class WebOpenApiBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebOpenApiBackendApplication.class, args);
    }

}
