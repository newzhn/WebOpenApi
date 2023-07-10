package com.zhn.webopenapicore.handler;

import com.alibaba.fastjson.JSON;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.utils.web.WebUtils;
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
        Result result = new Result(HttpStatus.UNAUTHORIZED, e.getMessage());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, json);
    }
}
