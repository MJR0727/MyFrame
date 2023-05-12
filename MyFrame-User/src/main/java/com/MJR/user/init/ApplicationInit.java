package com.MJR.user.init;

import com.MJR.user.designPattem.SkuDO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Mjr
 * @version 1.0
 * @description: 预热服务
 * @date 2023/2/21 23:15
 */
@Component
@Slf4j
public class ApplicationInit implements ApplicationListener<ApplicationReadyEvent> {

    Map<String, InitFunction> functionMap = new HashMap<>();

    {
        functionMap.put("预热FastJson",this::initFastJson);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("服务初始化完毕");
        functionMap.forEach((desc, function) -> {
            try {
                long start = System.currentTimeMillis();
                function.invoke();
                log.info("ApplicationInit{}.costTime{}", desc, System.currentTimeMillis() - start);
            } catch (Exception e) {
                log.error("ApplicationInit{}.error", desc, e);
            }
        });
    }

    private void initFastJson() {
        SkuDO skuDO = new SkuDO();
        skuDO.setSkuId(1L);
        skuDO.setSkuName("苹果");
        String s = JSON.toJSONString(skuDO);
        System.out.println(s);
        JSON.parseObject(s, SkuDO.class);
    }

    interface InitFunction{

        void invoke();

    }

}
