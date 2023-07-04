package com.zhn.webopenapibackend;

import com.zhn.webopenapibackend.model.domain.User;
import com.zhn.webopenapibackend.utils.QQUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class WebOpenApiBackendApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        user.setQq("2571469810");
        QQUtil.setAvatarUrlAndNickname(user,new RestTemplate());
    }

}
