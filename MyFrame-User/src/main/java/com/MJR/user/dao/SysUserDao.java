package com.MJR.user.dao;

import com.MJR.user.entity.po.SysUser;

public interface SysUserDao {
    SysUser getUserByUsername(String userName);
}
