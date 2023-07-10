package com.zhn.webopenapicore.filter;

import com.zhn.webopenapicommon.exception.BusinessException;
import com.zhn.webopenapicore.model.LoginUser;
import com.zhn.webopenapicore.model.eneum.CacheEnums;
import com.zhn.webopenapicore.utils.JwtUtil;
import com.zhn.webopenapicore.utils.redis.RedisCache;
import com.zhn.webopenapicore.utils.string.StringUtils;
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

import static com.zhn.webopenapicore.constant.UserConstant.*;

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
        //登录或注册接口则直接放行
        String uri = httpServletRequest.getRequestURI();
        uri = uri.substring(4);
        if (USER_LOGIN_URI.equals(uri) || USER_REGISTER_URI.equals(uri)
            || USER_VERIFICATION_CODE.equals(uri)) {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //获取token
        String token = httpServletRequest.getHeader("Access-Token");
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
        String key = CacheEnums.USER_LOGIN.getKeyPrefix() + userId;
        LoginUser loginUser = redisCache.getCacheObject(key);
        if (loginUser == null) {
            throw new BusinessException("用户未登录");
        }
        //数据存在则将其存入Holder中并更新登录时间
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        redisCache.expire(CacheEnums.USER_LOGIN,userId);
        //放行
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
