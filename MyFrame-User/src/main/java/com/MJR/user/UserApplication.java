package com.MJR.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 22249
 * @version 1.0
 * @description: TODO
 * @date 2022/9/27 15:23
 */
@SpringBootApplication
@MapperScan(value="com.MJR.*.mapper")
@ComponentScan(value = "com.MJR")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
