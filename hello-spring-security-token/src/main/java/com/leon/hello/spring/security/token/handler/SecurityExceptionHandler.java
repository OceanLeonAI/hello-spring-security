package com.leon.hello.spring.security.token.handler;

import com.leon.hello.spring.security.token.domain.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: SecurityExceptionHandler
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/29 22:16
 * @Version 1.0
 * @DESCRIPTION: 自定义异常处理，只捕获controller里的异常
 * 无法捕获 filter里的异常
 * SpringSecurity filter的异常需要单独处理，分为认证异常和授权异常
 **/
@Slf4j
//@ControllerAdvice // FIXME: 考虑这个全局异常捕获对 security 异常处理的影响
public class SecurityExceptionHandler {

    /**
     * declare logger
     */
    public static final Logger logger = LoggerFactory.getLogger(SecurityExceptionHandler.class);

    /**
     * 兜底捕获一切异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult responseResult(Exception e) {
        logger.error("捕获到异常", e);
        return new ResponseResult(500, e.getMessage());
    }

}
