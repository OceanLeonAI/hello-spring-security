package com.leon.hello.spring.security.token.domain;

import com.alibaba.fastjson.annotation.JSONField;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: LoginUser
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/26 22:57
 * @Version 1.0
 * @DESCRIPTION: 自定义springsecurity所需用户信息
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginUser implements UserDetails {

    /**
     * 成员变量用户信息
     */
    private User user;

    /**
     * 存储权限信息字符串集合
     */
    private List<String> permissions;

    /**
     * 存储SpringSecurity所需要的权限信息的集合
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 本地缓存起来

        // 把permissions中字符串类型的权限信息转换成GrantedAuthority对象存入authorities中
        if (Collections.isEmpty(authorities)) {
            authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
