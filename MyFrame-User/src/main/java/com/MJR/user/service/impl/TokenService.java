package com.MJR.user.service.impl;

import com.MJR.redis.util.RedisUtil;
import com.MJR.security.util.JWTUtil;
import com.MJR.user.entity.po.SysUser;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mjr
 * @version 1.0
 * @description: token-Redis业务类
 * @date 2023/4/19 23:33
 */
@Service("TokenService")
public class TokenService {

    @Autowired
    private RedisUtil redisUtil;

    public SysUser getUserByToken(String token) {
        String userIdFromToken = JWTUtil.getUserIdFromToken(token);
        String sysUserJson = redisUtil.get(userIdFromToken);
        SysUser sysUser = JSON.parseObject(sysUserJson, SysUser.class);
        return sysUser;
    }
}
