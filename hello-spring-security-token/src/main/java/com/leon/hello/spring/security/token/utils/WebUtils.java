package com.leon.hello.spring.security.token.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: WebUtils
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/24 21:31
 * @Version 1.0
 * @DESCRIPTION: 工具类用于封装异常返回数据
 **/
public class WebUtils {

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
