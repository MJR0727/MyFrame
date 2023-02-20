package com.MJR.redis.util;

import com.MJR.redis.exception.RedisShareLockException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2023/2/3 16:30
 */
@Component
public class RedisShareLockUtil {

    @Autowired
    private RedisUtil redisUtil;

    private long TIME_OUT = 3000;

    public Boolean tryLock(String key, String requestId, Long expirTime){
        if (StringUtils.isBlank(key) || StringUtils.isBlank(requestId) || (expirTime<=0)){
            throw  new RedisShareLockException("分布式锁尝试加锁参数异常");
        }
        return redisUtil.setNx(key,requestId,expirTime,TimeUnit.MILLISECONDS);
    }

    public Boolean lock(String key, String requestId, Long expirTime){
        //1、参数校验  2、自旋尝试加锁（配置业务加锁超时时间）3、循环暂停（睡眠一段时间）
        if (StringUtils.isBlank(key) || StringUtils.isBlank(requestId) || (expirTime<=0)){
            throw  new RedisShareLockException("分布式锁加锁参数异常");
        }
        long currentTimeMillis = System.currentTimeMillis();
        long aquairTime = currentTimeMillis + TIME_OUT;
        Boolean result = false;
        while(currentTimeMillis < aquairTime){
            result = redisUtil.setNx(key,requestId,expirTime,TimeUnit.MILLISECONDS);
            if(result==true){
                return true;
            }
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            currentTimeMillis = System.currentTimeMillis();
        }
        return false;
    }

    public Boolean unLock(String key, String requestId){
        if(StringUtils.isBlank(key)||StringUtils.isBlank(requestId)){
            throw new RedisShareLockException("分布式锁解锁参数异常");
        }
        String valse = redisUtil.get(key);
        if(valse.equals(requestId)){
            redisUtil.del(key);
            return true;
        }
        return false;
    }

}
