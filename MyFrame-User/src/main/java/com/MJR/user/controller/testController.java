package com.MJR.user.controller;

import com.MJR.redis.util.RedisShareLockUtil;
import com.MJR.redis.util.RedisUtil;
import com.MJR.redis.util.RedissonUtil;
import com.MJR.tool.ExportWordUtil;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author 22249
 * @version 1.0
 * @description:
 * @date 2023/2/4 14:03
 */
@RestController
public class testController {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisShareLockUtil redisShareLockUtil;

    @Autowired
    private RedissonUtil redissonUtil;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/testRedis")
    public void testRedis() {
        redisUtil.set("name", "鸡翅");
    }

    @GetMapping("/testRedisLock")
    public void testRedisLock() {
        boolean result = redisShareLockUtil.lock("jichi", "1231231", 100000L);
        System.out.println(result);
    }

    @GetMapping("/testRedissonLock")
    public void testRedissonLock(){
        redissonUtil.tryLock("lock1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redissonUtil.unLock("lock1");
    }

    @GetMapping("/testRedissonUnLock")
    public void testRedissonUnLock(){
        redissonUtil.unLock("lock1");
    }

    @GetMapping("/testExport")
    public void testExport() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("name","MJR");
        map.put("age","23");
        map.put("gender","man");
        map.put("enthnicGroup","hanzu");
        map.put("phoneNumber","13729295281");
        ExportWordUtil.exprotWord(map,"导出文件","exprotWord.ftl");
    }
}
