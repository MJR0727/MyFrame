package com.MJR.user.config;

import com.MJR.security.handler.ExceptionalAuthenticationHandler;
import com.MJR.security.handler.LogoutSuccessHandler;
import com.MJR.user.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Mjr
 * @version 1.0
 * @description: security配置类 for 后台程序
 * @date 2023/4/17 17:12
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //设置token认证过滤器，将用户数据存放入Redis中

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private ExceptionalAuthenticationHandler exceptionalAuthenticationHandler;

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO 设置认证成功和失败的逻辑  自定义处理器
        http
                .csrf().disable()
                //异常处理器
                .exceptionHandling().authenticationEntryPoint(exceptionalAuthenticationHandler).and()
                //禁止默认的session模式
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //登出成功处理器
                .logout().logoutSuccessHandler(logoutSuccessHandler).and()
                .authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
