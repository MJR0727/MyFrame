package com.MJR.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2022/9/27 15:23
 */
@SpringBootApplication
@MapperScan(value="com.MJR.*.dao")
@ComponentScan(basePackages = {"com.MJR"})
//@EnableCaching 注解方式的缓存
public class UserApplication {
    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector","org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(UserApplication.class);
    }
}
