package com.MJR.user.service.impl;

import com.MJR.user.entity.dto.UserDto;
import com.MJR.user.entity.po.UserPo;
import com.MJR.user.mapper.UserMapper;
import com.MJR.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 22249
 * @version 1.0
 * @description: TODO
 * @date 2022/9/29 1:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(UserDto userDto) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(userDto,userPo);
        int insert = userMapper.insert(userPo);
        return insert;
    }

}
