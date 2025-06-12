package com.liren.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
@Slf4j
public class AspectDemo2 {
    @Before("com.liren.aop.aspect.AspectDemo1.pt()")
    public void Before() {
        log.info("执行AspectDemo2 -> before方法");
    }
}
