package com.liren.thread_advanced;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class demo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        // submit一个Callable任务，然后返回Future<String>类型，需要强转
        Future<String> ft = pool.submit(() -> {
            try {
                Thread.sleep(1000);
                return "使用Callable执行完成";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        String ret = ft.get();
        System.out.println("结果：" + ret);
    }
}
