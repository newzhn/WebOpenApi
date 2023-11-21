package com.zhn.webopenapicore;

import com.zhn.webopenapiclientsdk.facade.ApiClientFacade;
import com.zhn.webopenapiclientsdk.utils.JsonUtil;
import com.zhn.webopenapicommon.model.domain.User;
import com.zhn.webopenapicore.utils.QQUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhn
 * @version 1.0
 * @date 2023/7/10 16:03
 * @blog www.zhnblog.icu
 */
@SpringBootTest
public class WebOpenApiCoreApplicationTests {
    @Resource
    private ApiClientFacade apiClientFacade;

    @Test
    void contextLoads() {
        HashMap<String, Object> paramMap = new HashMap<>();
        User user = new User();
        user.setUserName("zhangsan");

        paramMap.put("user", user);
        String result = apiClientFacade.invoke("POST", "/api/test/post/name", paramMap);
        Map<String, Object> resultMap = JsonUtil.toMap(result);
        System.out.println(resultMap);
    }

}
