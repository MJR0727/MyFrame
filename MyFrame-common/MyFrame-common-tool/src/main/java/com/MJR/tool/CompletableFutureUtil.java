package com.MJR.tool;



import org.slf4j.Logger;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Mjr
 * @version 1.0
 * @description: 异步任务工具类
 * @date 2023/4/6 22:06
 */
public class CompletableFutureUtil {

    public static<T> T getResult(Future<T> future, long timeout, TimeUnit timeUnit,
                                 T defaultValue, Logger logger){
        try{
            return future.get(timeout,timeUnit);
        }catch (Exception e){
            logger.error("CompletableFutureUtil.getResult.error:{}",e.getMessage(),e);
            logger.error("CompletableFutureUtils.getResult.error.returnDefaultValue:{}", defaultValue);
            return defaultValue;
        }
    }
}
