package com.MJR.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2023/2/3 16:19
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public String get(String key){
        String value = (String)redisTemplate.opsForValue().get(key);
        return value;
    }

    public void set(String key,String value){
        redisTemplate.opsForValue().set(key,value);
    }

    public Boolean setNx(String key, String requestId, Long outTime,TimeUnit timeUnit){
        return redisTemplate.opsForValue().setIfAbsent(key, requestId, outTime, timeUnit);
    }

    public Boolean del(String key){
        return redisTemplate.delete(key);
    }
}
