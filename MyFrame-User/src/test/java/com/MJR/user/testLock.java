package com.MJR.user;

import com.MJR.redis.init.AbstractCache;
import com.MJR.redis.util.RedissonUtil;
import com.MJR.redis.util.SpringContextUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2023/2/4 11:55
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
public class testLock {

    @Autowired
    private SpringContextUtil springContextUtil;

    @Resource
    private RedissonUtil redissonUtil;

    @Test
    public void tryLock() {
        redissonUtil.tryLock("hello");
    }

    @Test
    public void unLock() {
        redissonUtil.unLock("hello");
    }

    @Test
    public void testRedis(){
        ApplicationContext applicationContext = springContextUtil.getApplicationContext();
        Map<String, AbstractCache> cacheMap = applicationContext.getBeansOfType(AbstractCache.class);
        if(cacheMap.isEmpty()){
            return;
        }
        for(AbstractCache cache : cacheMap.values()){
            System.out.println(cache.getClass());
            AbstractCache abstractCache = (AbstractCache)SpringContextUtil.getBean(cache.getClass());
            abstractCache.initCache();
        }
    }
}
