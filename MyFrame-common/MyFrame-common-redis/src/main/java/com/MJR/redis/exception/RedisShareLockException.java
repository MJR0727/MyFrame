package com.MJR.redis.exception;

import org.springframework.stereotype.Component;

/**
 * @author 22249
 * @version 1.0
 * @description: 分布式锁超时异常类
 * @date 2023/2/3 16:36
 */
public class RedisShareLockException extends RuntimeException{

    public RedisShareLockException() {
    }

    public RedisShareLockException(String message){
        super(message);
    }

    public RedisShareLockException(String message,Throwable throwable){
        super(message,throwable);
    }

}
