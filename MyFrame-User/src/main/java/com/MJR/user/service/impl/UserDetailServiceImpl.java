package com.MJR.user.service.impl;

import com.MJR.user.dao.SysUserDao;
import com.MJR.user.entity.po.SysUser;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Mjr
 * @version 1.0
 * @description: Security用户信息业务类
 * @date 2023/4/18 0:38
 */
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private SysUserDao userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = userMapper.getUserByUsername(userName);
        if(!ObjectUtils.allNotNull(user)){
            logger.info("登录用户:{} 不存在",userName);
            throw new RuntimeException("登录用户:{"+userName+" 不存在");
        }else if("1".equals(user.getDeleteFlag())){
            logger.info("登录用户:{} 已删除",userName);
            throw new RuntimeException("登录用户:{"+userName+" 已删除");
        }else if("1".equals(user.getState())){
            logger.info("登录用户:{} 已封禁",userName);
            throw new RuntimeException("登录用户:{"+userName+" 已封禁");
        }
        //TODO 用户角色信息封装。
        return user;
    }
}
