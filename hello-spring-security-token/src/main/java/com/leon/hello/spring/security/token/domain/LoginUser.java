package com.leon.hello.spring.security.token.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

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
     * 增加成员变量用户信息
     */
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
