package com.MJR.user.filter;

import com.MJR.security.util.JWTUtil;
import com.MJR.tool.ServletUtils;
import com.MJR.user.constant.SysUserConstant;
import com.MJR.user.entity.po.SysUser;
import com.MJR.user.service.impl.TokenService;
import com.MJR.web.constant.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Mjr
 * @version 1.0
 * @description: token认证过滤器
 * @date 2023/4/17 17:09
 */
public class JwtAuthenticationTokenFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = (String) servletRequest.getAttribute("token");
        if(StringUtils.isBlank(token)){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        //用Redis作为黑名单，从redis中取出token存放的用户信息进行校验。
        //查询封禁信息
        SysUser user = tokenService.getUserByToken(token);
        if(!ObjectUtils.isEmpty(user)&&user!=null){
            if(JWTUtil.validateToken(token, user) && user.getState()==SysUserConstant.SYSUSER_STATUS_BENED) {
                //如果校验成功，那么证明用户是黑户
                ServletUtils.renderFailMsg((HttpServletResponse) servletResponse, HttpStatus.ERROR, "当前用户已被封禁，登录失败！");
                throw new RuntimeException("用户:{" + user.getUsername() + "已被封禁，请联系系统股管理员");
            }
        }
        //正常用户，进行放行
        //TODO 存放权限信息
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
