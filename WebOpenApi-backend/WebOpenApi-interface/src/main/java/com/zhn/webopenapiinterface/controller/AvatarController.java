package com.zhn.webopenapiinterface.controller;

import cn.hutool.core.util.StrUtil;
import com.zhn.webopenapiinterface.constant.RegexConstant;
import com.zhn.webopenapiinterface.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * 头像接口
 *
 * @author zhn
 * @version 1.0
 * @date 2023/7/20 22:20
 * @blog www.zhnblog.icu
 */
@RestController
@RequestMapping("/avatar")
@Slf4j
public class AvatarController {
    private static final String RANDOM_AVATAR_API = "https://www.loliapi.com/acg/pp/";
    /**
     * qq头像API
     */
    private static final String QQ_AVATAR_API = "https://q1.qlogo.cn/g?b=qq&nk=";

    @GetMapping("/random")
    public ApiResult getRandomAvatar() throws Exception {
        String redirectUrl = getRedirectUrl(RANDOM_AVATAR_API);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("randomAvatar",redirectUrl);
        return ApiResult.ok(resultMap,"获取随机头像成功");
    }

    @GetMapping("/qq/{qqNumber}")
    public ApiResult getQqAvatar(@PathVariable("qqNumber") String qqNumber) {
        //校验QQ号
        if (StrUtil.isEmpty(qqNumber) || RegexConstant.QQ_REGEX.matches(qqNumber)) {
            return ApiResult.fail("QQ号格式错误，请重新输入");
        }
        //拼接QQ号
        String url = QQ_AVATAR_API + qqNumber;
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("qqAvatar",url);
        return ApiResult.ok(resultMap,"获取QQ头像成功");
    }


    /**
     * 获取重定向地址
     * @param path
     * @return
     * @throws Exception
     */
    private String getRedirectUrl(String path) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(path)
                .openConnection();
        conn.setInstanceFollowRedirects(false);
        conn.setConnectTimeout(5000);
        String location = conn.getHeaderField("Location");
        return location;
    }
}
