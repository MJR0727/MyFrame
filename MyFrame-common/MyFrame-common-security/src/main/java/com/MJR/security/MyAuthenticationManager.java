package com.MJR.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author Mjr
 * @version 1.0
 * @description: 认证处理器
 * @date 2023/4/20 1:17
 */
@Component
public class MyAuthenticationManager implements AuthenticationManager {

    //TODO 自定义认证逻辑
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
