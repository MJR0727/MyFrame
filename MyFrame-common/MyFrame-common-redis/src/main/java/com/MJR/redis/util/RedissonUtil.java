package com.MJR.redis.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2023/1/19 0:25
 */
@Component
public class RedissonUtil {

    @Autowired
    private RedissonClient redissonClient;

    public void tryLock(String lockName){
        RLock lock = redissonClient.getLock(lockName);
        boolean b = lock.tryLock();
        System.out.println("尝试进行加锁");
    }

    public void unLock(String lockName){
        RLock lock = redissonClient.getLock(lockName);
        lock.unlock();
        System.out.println("尝试进行解锁");
    }
}
