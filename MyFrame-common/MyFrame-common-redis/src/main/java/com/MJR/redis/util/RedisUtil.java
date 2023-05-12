package com.MJR.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
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

    private DefaultRedisScript<Boolean> casScript;

    @PostConstruct
    public void init(){
        casScript = new DefaultRedisScript<>();
        casScript.setResultType(Boolean.class);
        casScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("casLuaScript.lua")));
    }

    /**
     * @description: 基于Lua脚本完成cas
     * @author Mjr
     * @date 2023/2/28 17:27
     * @version 1.0
     */
    public Boolean compareAndSet(String key,String oldValue,String newValue){
        ArrayList<String> list = new ArrayList<>();
        list.add(key);
        return (Boolean)redisTemplate.execute(casScript, list, oldValue, newValue);
    }


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
