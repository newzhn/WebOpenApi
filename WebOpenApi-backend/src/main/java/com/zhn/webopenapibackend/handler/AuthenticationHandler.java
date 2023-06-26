package com.zhn.webopenapibackend.handler;

import com.alibaba.fastjson.JSON;
import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.common.HttpStatus;
import com.zhn.webopenapibackend.utils.web.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证异常处理器
 *
 * @author zhn
 * @version 1.0
 */
@Component
public class AuthenticationHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResult result = new AjaxResult(HttpStatus.UNAUTHORIZED, "认证失败请重新登录");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, json);
    }
}
