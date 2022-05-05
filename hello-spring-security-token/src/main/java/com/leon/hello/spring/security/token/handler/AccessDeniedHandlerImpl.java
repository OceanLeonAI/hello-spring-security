package com.leon.hello.spring.security.token.handler;

import com.alibaba.fastjson.JSON;
import com.leon.hello.spring.security.token.domain.ResponseResult;
import com.leon.hello.spring.security.token.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: AccessDeniedHandlerImpl
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/5 22:42
 * @Version 1.0
 * @DESCRIPTION: 授权异常
 * 在 SpringSecurity 中，如果我们在认证或者授权的过程中出现了异常会被 ExceptionTranslationFilter 捕获到。
 * 在 ExceptionTranslationFilter 中会去判断是认证失败还是授权失败出现的异常。
 * 1.如果是认证过程中出现的异常会被封装成 AuthenticationException 然后调用 AuthenticationEntryPoint 对象的方法去进行异常处理。
 * 2.如果是授权过程中出现的异常会被封装成 AccessDeniedException 然后调用 AccessDeniedHandler 对象的方法去进行异常处理
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult(HttpStatus.FORBIDDEN.value(), "授权失败");
        WebUtils.renderString(response, JSON.toJSONString(responseResult));
    }
}
