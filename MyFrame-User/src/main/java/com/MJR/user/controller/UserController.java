package com.MJR.user.controller;

import com.MJR.user.entity.dto.UserDto;
import com.MJR.user.entity.req.UserReq;
import com.MJR.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 22249
 * @version 1.0
 * @description: TODO
 * @date 2022/9/29 1:08
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public int addUser(@RequestBody UserReq userReq){
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userReq,userDto);
        int i = userService.addUser(userDto);
        return i;
    }
}
