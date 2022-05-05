package com.leon.hello.spring.security.token.handler;

import com.alibaba.fastjson.JSON;
import com.leon.hello.spring.security.token.domain.ResponseResult;
import com.leon.hello.spring.security.token.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: AuthenticationEntryPointImpl
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/5 22:48
 * @Version 1.0
 * @DESCRIPTION: 认证失败
 * * 在 SpringSecurity 中，如果我们在认证或者授权的过程中出现了异常会被 ExceptionTranslationFilter 捕获到。
 * * 在 ExceptionTranslationFilter 中会去判断是认证失败还是授权失败出现的异常。
 * * 1.如果是认证过程中出现的异常会被封装成 AuthenticationException 然后调用 AuthenticationEntryPoint 对象的方法去进行异常处理。
 * * 2.如果是授权过程中出现的异常会被封装成 AccessDeniedException 然后调用 AccessDeniedHandler 对象的方法去进行异常处理
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "认证失败");
        WebUtils.renderString(response, JSON.toJSONString(responseResult));
    }
}
