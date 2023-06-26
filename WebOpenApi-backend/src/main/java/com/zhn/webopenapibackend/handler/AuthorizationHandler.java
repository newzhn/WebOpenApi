package com.zhn.webopenapibackend.handler;

import com.alibaba.fastjson.JSON;
import com.zhn.webopenapibackend.common.AjaxResult;
import com.zhn.webopenapibackend.common.HttpStatus;
import com.zhn.webopenapibackend.utils.web.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 鉴权异常处理器
 * @author zhn
 * @version 1.0
 */
@Component
public class AuthorizationHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        AjaxResult result = new AjaxResult(HttpStatus.FORBIDDEN, "您的权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, json);
    }
}
