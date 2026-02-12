package com.liren;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKInvocationHandler implements InvocationHandler {
    private Object target;

    public JDKInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 执行代理的事情
        System.out.println("我是中介, 开始代理");

        // 执行房东的事情
        Object retVal = method.invoke(target, args);

        // 执行代理的事情
        System.out.println("我是中介, 代理结束");
        return retVal;
    }
}
