package com.zhn.webopenapicore.handler;

import com.alibaba.fastjson.JSON;
import com.zhn.webopenapicommon.model.HttpStatus;
import com.zhn.webopenapicommon.model.Result;
import com.zhn.webopenapicore.utils.web.WebUtils;
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
        Result result = new Result(HttpStatus.FORBIDDEN, "您的权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse, json);
    }
}
