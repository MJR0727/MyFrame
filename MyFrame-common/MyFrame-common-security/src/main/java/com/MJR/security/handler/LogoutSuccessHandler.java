package com.MJR.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mjr
 * @version 1.0
 * @description: 登出成功处理器
 * @date 2023/4/18 16:25
 */
@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //TODO 登出逻辑 删除缓存信息，token清除。
       httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
       httpServletResponse.getWriter().flush();
    }
}
