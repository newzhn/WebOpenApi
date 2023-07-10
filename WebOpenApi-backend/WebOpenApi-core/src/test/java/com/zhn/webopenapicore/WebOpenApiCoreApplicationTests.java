package com.zhn.webopenapicore;

import com.zhn.webopenapicore.model.domain.User;
import com.zhn.webopenapicore.utils.QQUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/10 16:03
 * @blog www.zhnblog.icu
 */
@SpringBootTest
public class WebOpenApiCoreApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        user.setQq("2571469810");
        QQUtil.setAvatarUrlAndNickname(user,new RestTemplate());
    }

}
