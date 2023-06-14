package com.zhn.webopenapibackend.filter;

import com.zhn.webopenapibackend.constant.UserConstant;
import com.zhn.webopenapibackend.exception.BusinessException;
import com.zhn.webopenapibackend.model.domain.LoginUser;
import com.zhn.webopenapibackend.utils.JwtUtil;
import com.zhn.webopenapibackend.utils.redis.RedisCache;
import com.zhn.webopenapibackend.utils.string.StringUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt认证过滤器
 * @author zhn
 * @version 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(token)) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //token存在则进行解析
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("非法的Token");
        }
        //Redis查询登录数据
        String key = UserConstant.USER_LOGIN_KEY + userId;
        LoginUser loginUser = redisCache.getCacheObject(key);
        if (loginUser == null) {
            throw new BusinessException("用户未登录");
        }
        // TODO 封装权限信息

        //数据存在则将其存入Holder中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
