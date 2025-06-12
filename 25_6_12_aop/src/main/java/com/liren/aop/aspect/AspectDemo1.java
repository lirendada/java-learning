package com.liren.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Aspect
@Component
@Slf4j
public class AspectDemo1 {
    @Pointcut("execution(* com.liren.aop.controller.*.*(..))")
    public void pt() {}

    @Around("pt()")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        log.info("目标方法执行前....");
        //执行目标方法
        Object result = null;
        try {
            result = pjp.proceed(); // 执行目标方法
        } catch (Exception e) {
            log.error("do Around throwing....");
        }
        //记录方法执行结束时间
        log.info("目标方法执行后....");

        return result;
    }

    @Before("pt()")
    public void doBefore() {
        log.info("doBefore....");
    }

    @After("pt()")
    public void doAfter(){
        log.info("doAfter....");
    }

//    @AfterReturning("pt()")
//    public void doAfterReturning(){
//        log.info("doAfterReturning....");
//    }
//
//    @AfterThrowing("pt()")
//    public void doAfterThrowing(){
//        log.info("doAfterThrowing....");
//    }
}