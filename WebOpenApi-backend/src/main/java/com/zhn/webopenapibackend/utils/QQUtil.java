package com.zhn.webopenapibackend.utils;

import com.zhn.webopenapibackend.constant.UserConstant;
import com.zhn.webopenapibackend.model.domain.User;
import com.zhn.webopenapibackend.utils.string.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Collections;

/**
 * QQ工具类
 *
 * @author zhn
 * @version 1.0
 * @date 2023/6/26 18:29
 * @blog www.zhnblog.icu
 */
public class QQUtil {
    /**
     * 获取QQ昵称和头像
     *
     * @param user
     * @param restTemplate
     */
    public static void setAvatarUrlAndNickname(User user, RestTemplate restTemplate) {
        String qq = user.getQq();
        user.setUserAvatar(UserConstant.QQ_AVATAR_API + qq + "&s=100");
        String nickname = "";
        try {
            restTemplate.setMessageConverters(Collections.singletonList(new StringHttpMessageConverter(Charset.forName("GBK"))));
            String url = UserConstant.QQ_NICKNAME_API + qq;
            String response = restTemplate.getForObject(url, String.class);
            nickname = response.split(",")[6];
            nickname = nickname.substring(1, nickname.length() - 1);
        } catch (Exception e) {
            //获取失败则昵称采用随机六位字符串
            nickname = "user_" + StringUtils.randomString(6);
        }
        user.setUserName(nickname);
    }
}
