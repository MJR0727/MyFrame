package com.MJR.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;


/**
 * @author Mjr
 * @version 1.0
 * @description: 认证成功处理器
 * @date 2023/4/18 15:55
 */
@Component
public class ExceptionalAuthenticationHandler implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionalAuthenticationHandler.class);

    @Override
    public void commence(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        logger.error("can't be authenticate!");
        httpServletResponse.sendError(400,"authenticate fail");
    }
}
