package com.MJR.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;


/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2023/1/15 13:34
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() throws IOException {
        Config config = Config.fromYAML(new File("D:\\workplace\\IdeaProjects\\MyFrame\\MyFrame-User\\src\\main\\resources\\redisson-config.yaml"));
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
