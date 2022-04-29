package com.leon.hello.spring.security.token.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @PROJECT_NAME: hello-spring-security
 * @CLASS_NAME: SecurityConfiguraton
 * @AUTHOR: OceanLeonAI
 * @CREATED_DATE: 2022/4/26 23:08
 * @Version 1.0
 * @DESCRIPTION:
 **/
@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 注入密码编码对象
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * security 配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
    }

    /**
     * 获取 AuthenticationManager 对象
     * <p>
     * Override this method to expose the AuthenticationManager
     * from configure(AuthenticationManagerBuilder)
     * to be exposed as a Bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
