package com.MJR.user;

import com.MJR.tool.CompletableFutureUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Mjr
 * @version 1.0
 * @description: 线程池测试类
 * @date 2023/2/23 22:34
 */
@SpringBootTest(classes = UserApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class testThreadPool {

    @Resource(name = "myThreadPoolExecutor")
    private ThreadPoolExecutor myThreadPoolExecutor;

    @Test
    public void testMyThreadPool(){
        for(int i = 0; i < 10; i++){
            myThreadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    log.info("测试测试测试");
                }
            });
        }
    }

    @Test
    public void testFutureUtil(){
        ArrayList<FutureTask> futureTasks = new ArrayList<>();
        FutureTask task1 = new FutureTask(() -> {
            return "你好";
        });
        FutureTask task2 = new FutureTask(() -> {
            return "nihao";
        });
        futureTasks.add(task1);
        futureTasks.add(task2);
        for(int i = 0; i < futureTasks.size(); i++){
            String wobutaihao = CompletableFutureUtil.getResult(futureTasks.get(i), 10L, TimeUnit.MINUTES,
                    "wobutaihao", log);
            log.info("result:" + wobutaihao);
        }
    }
}
