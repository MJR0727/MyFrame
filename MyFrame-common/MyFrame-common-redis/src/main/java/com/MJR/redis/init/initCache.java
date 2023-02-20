package com.MJR.redis.init;

import com.MJR.redis.util.SpringContextUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author 22249
 * @version 1.0
 * @description: 通过对AbstractCache-Spring实例的获取实现缓存预热
 * @date 2023/2/3 20:54
 */
@Component
public class initCache implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        Map<String, AbstractCache> cacheMap = applicationContext.getBeansOfType(AbstractCache.class);
        if(cacheMap.isEmpty()){
            return;
        }
        for(AbstractCache cache : cacheMap.values()){
            AbstractCache abstractCache = (AbstractCache)SpringContextUtil.getBean(cache.getClass());
            abstractCache.initCache();
        }
    }
}
