package com.MJR.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Mjr
 * @version 1.0
 * @description: 自定义线程池
 * @date 2023/2/23 22:29
 */
@Configuration
public class ThreadPoolConfig {

    private ThreadFactory myThreadPoolExecutor = new MyThreadFactory("test");

    @Bean(name = {"myThreadPoolExecutor"})
    public ThreadPoolExecutor myThreadPoolExecutor(){
        return new ThreadPoolExecutor(20,50,5000, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(50),
                myThreadPoolExecutor,
                new ThreadPoolExecutor.DiscardPolicy());
    }
}
