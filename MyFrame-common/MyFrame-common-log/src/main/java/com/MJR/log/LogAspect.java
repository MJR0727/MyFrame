package com.MJR.log;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author Mjr
 * @version 1.0
 * @description: AOP输出日志
 * @date 2023/2/13 14:17
 */
@Aspect
@Slf4j
@Component
@ConditionalOnProperty(name={"log.aspect.enable"},havingValue = "true",matchIfMissing = true)
public class LogAspect {

    @Pointcut("execution(* com.MJR.*.controller.*Controller.*(..)) || execution(* com.MJR.*.service.*Service.*(..))")
    public void Pointcut(){
    }

    @Around("Pointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        String reqArgs = new Gson().toJson(args);
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        String methodName = methodSignature.getDeclaringType().getName() + "." + methodSignature.getName();
        log.info("{},req:{}",methodName,reqArgs);
        long startTime = System.currentTimeMillis();
        Object resp = pjp.proceed();
        String respJson = new Gson().toJson(resp);
        long endTime = System.currentTimeMillis();
        log.info("{},resp:{},costTime:{}",methodName,respJson,endTime-startTime);
    }
}
