package com.liren.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(-1)
@Slf4j
@Aspect
@Component
public class MyAspetImpl {
    @Around("@annotation(com.liren.aop.aspect.MyAspect)")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        log.info("MyAspetImpl目标方法执行前....");
        //执行目标方法
        Object result = null;
        try {
            result = pjp.proceed(); // 执行目标方法
        } catch (Exception e) {
            log.error("do Around throwing....");
        }
        //记录方法执行结束时间
        log.info("MyAspetImpl目标方法执行后....");

        return result;
    }

    @Before("@annotation(com.liren.aop.aspect.MyAspect)")
    public void before() {
        log.info("MyAspect => before....");
    }

    @After("@annotation(com.liren.aop.aspect.MyAspect)")
    public void after() {
        log.info("MyAspect => after....");
    }
}
