package com.leon.hello.spring.security.token.handler;

import com.leon.hello.spring.security.token.domain.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: SecurityExceptionHandler
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/29 22:16
 * @Version 1.0
 * @DESCRIPTION: 自定义异常处理
 * FIXME: 如何捕获security抛出的异常
 **/
@Slf4j
@ControllerAdvice
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
    @ExceptionHandler(Exception.class)
    public ResponseResult responseResult(Exception e) {
        logger.error("捕获到异常", e);
        return new ResponseResult(500, "发生异常，请联系管理员！");
    }

}
