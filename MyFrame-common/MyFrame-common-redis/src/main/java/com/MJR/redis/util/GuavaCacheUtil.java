package com.MJR.redis.util;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Mjr
 * @version 1.0
 * @description: guava本地缓存工具类
 * @date 2023/2/15 22:26
 */
@Component
@Slf4j
public class GuavaCacheUtil<K,V> {

    @Value("guava.cache.enable")
    private boolean guavaCacheEnable;

    private static Cache<String,String> localCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .maximumSize(5000)
            .build();

    public Map<K,V> getRecord(List<K> idList, String cachePrefix, String cacheSuffix,
                              Class<V> clazz, Function<List<K>,Map<K,V>> function){
        if(CollectionUtils.isEmpty(idList)){
            return Collections.emptyMap();
        }
        //1建立记录集
        Map<K, V> recordMap = new HashMap<>();
        //case1:没有开启guava缓存-》执行rpc调用
        if(!guavaCacheEnable){
            recordMap = function.apply(idList);
            return recordMap;
        }
        List<K> noCacheKeyList = new ArrayList<>();
        for(K id : idList){
            String key = cachePrefix + "_" + id + "_" + cacheSuffix;
            String content = localCache.getIfPresent(key);
            if(StringUtils.isBlank(content)){
                noCacheKeyList.add(id);
            }else{
                V v = JSON.parseObject(content, clazz);
                recordMap.put(id,v);
            }
        }
        if(CollectionUtils.isEmpty(noCacheKeyList)){
            return recordMap;
        }
        Map<K, V> tempCacheMap = function.apply(noCacheKeyList);
        if(CollectionUtils.isEmpty(tempCacheMap) || tempCacheMap==null){
            return recordMap;
        }
        //将查询到的放入recordMap 和 localcache中。
        for(Map.Entry<K,V> entry : tempCacheMap.entrySet()){
            K id = entry.getKey();
            V value = entry.getValue();
            recordMap.put(id,value);
            String key = cachePrefix + "_" + id + "_" + cacheSuffix;
            String content = JSON.toJSONString(value);
            localCache.put(key,content);
        }
        return recordMap;
    }
}
