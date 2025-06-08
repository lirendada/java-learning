package com.liren.thread_advanced;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class demoo1 {
    public static void main(String[] args) throws Exception {
        Callable<String> cable = () -> {
            Thread.sleep(1000);
            return "使用Callable执行完成";
        };

        // 使用FutureTask包装Callable
        FutureTask<String> ft = new FutureTask<>(cable);

        // 传给Thread进行构造
        Thread thread = new Thread(ft);
        thread.start();

        // 使用get()方法获取返回值
        String ret = ft.get();
        System.out.println("结果：" + ret);
    }
}
