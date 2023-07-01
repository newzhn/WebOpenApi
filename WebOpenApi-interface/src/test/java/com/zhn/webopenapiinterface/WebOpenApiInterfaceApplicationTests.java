package com.zhn.webopenapiinterface;

import com.zhn.webopenapiclientsdk.client.WebApiClient;
import com.zhn.webopenapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class WebOpenApiInterfaceApplicationTests {
    @Resource
    private WebApiClient webApiClient;

    @Test
    void contextLoads() {
        String result1 = webApiClient.getNameByGet("zhangsan1");
        String result2 = webApiClient.getNameByPost("zhangsan2");
        User user = new User();
        user.setName("zhangsan3");
        String result3 = webApiClient.getNameByPostJson(user);
        System.out.println("result1=" + result1);
        System.out.println("result2=" + result2);
        System.out.println("result3=" + result3);
    }

}
