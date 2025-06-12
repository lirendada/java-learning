package com.liren.book_system.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect // 标识这是一个切面类
public class TimeAspect {
    // 记录方法耗时
    @Around("execution(* com.liren.book_system.controller.*.*(..))") // 环绕通知，即设置切点
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        log.info(pjp.getSignature() + "耗时：" + (end - start) + "ms");
        return proceed;
    }
}
