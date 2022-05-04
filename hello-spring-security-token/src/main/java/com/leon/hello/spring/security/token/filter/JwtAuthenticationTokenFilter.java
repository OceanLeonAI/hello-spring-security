package com.leon.hello.spring.security.token.filter;

import com.leon.hello.spring.security.token.domain.LoginUser;
import com.leon.hello.spring.security.token.utils.JwtUtil;
import com.leon.hello.spring.security.token.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.apache.catalina.security.SecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.leon.hello.spring.security.token.constant.HelloSpringSecurityTokenConstant.TOKEN;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: JwtAuthenticationTokenFilter
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/29 21:18
 * @Version 1.0
 * @DESCRIPTION: 我们需要自定义一个过滤器，
 * 这个过滤器会去获取请求头中的token，对token进行解析取出其中的userid。
 * 使用userid去redis中获取对应的LoginUser对象。
 * 然后封装Authentication对象存入SecurityContextHolder
 * <p>
 * 注意将 Filter 配置到拦截器链路中
 * @see com.leon.hello.spring.security.token.configuration.SpringSecurityConfiguration
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * declare logger
     */
    public static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 获取 token
        String token = request.getHeader(TOKEN);

        // 如果未携带token放行
        if (ObjectUtils.isEmpty(token)) { // StringUtils.hasText(token) 判断也行
            filterChain.doFilter(request, response);
            return; // 拦截器链回调需要终止
        }

        // 解析 token
        String userId;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userId = claims.getSubject();

        } catch (Exception e) {
            logger.error("解析token发生异常", e);
            throw new RuntimeException("token无效");
        }

        // 验证 token
        String cacheKey = "login:" + userId;
        LoginUser loginUser = redisCache.getCacheObject(cacheKey);

        if (Objects.isNull(loginUser)) {
            throw new RuntimeException("token无效");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginUser, // principal
                null,
                loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 验证成功放行
        filterChain.doFilter(request, response);

    }
}
