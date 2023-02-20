package com.MJR.redis.init;

import org.springframework.stereotype.Component;

/**
 * @author 22249
 * @version 1.0
 * @description: 定义缓存抽象类，实现具体的方法，通过对abstractCache-Spring实例的获取然后实现缓存预热
 * @date 2023/2/3 20:50
 */
@Component
public abstract class AbstractCache {

    public void initCache(){}

    public void clearCache(){}

    public <T> T getCache(String key){
        return null;
    }

    public void reloadCache(){
        initCache();
        clearCache();
    }
}
