package com.leon.hello.spring.security.token.expression;

import com.leon.hello.spring.security.token.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: CustomisedSecurityExpressionRoot
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/5/8 22:52
 * @Version 1.0
 * @DESCRIPTION: 自定义权限注解
 **/
@Component // 默认bean名称 类名首字母小写
public class CustomisedSecurityExpressionRoot {

    /**
     * 自定义
     *
     * @param authority
     * @return
     */
    public final boolean hasAuthority(String authority) {
        return hasAnyAuthority(authority);
    }

    /**
     * 自定义
     *
     * @param authorities
     * @return
     */
    public final boolean hasAnyAuthority(String... authorities) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        return Arrays
                .stream(authorities)
                .distinct()
                .anyMatch(permissions::contains);
    }

}
