package com.liren.thread_apply;

import java.util.concurrent.*;

public class demo4 {
    public static void main(String[] args) {
        // 1. 创建一个指定数量线程的线程池
        ExecutorService p1 = Executors.newFixedThreadPool(10);

        // 2. 创建一个线程池，该线程池根据需要创建新线程，但将在以前构建的线程可用时重用这些线程
        ExecutorService p2 = Executors.newCachedThreadPool();

        // 3. 创建一个只有一个线程的线程池，来保证绝对的串行性
        ExecutorService p3 = Executors.newSingleThreadExecutor();

        // 4. 创建一个线程池，该线程池每个线程都有独立队列，且空闲线程可去偷别人的任务来帮忙干
        ExecutorService p4 = Executors.newWorkStealingPool();

        // 5. 创建一个可以安排命令在给定延迟后运行或定期执行的线程池
        ExecutorService p5 = Executors.newScheduledThreadPool(10);

        // 2. 让线程池中10个线程来处理100个任务
        for(int i = 0; i < 100; ++i) {
            int index = i;
            p1.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ":" + index);
            });
        }
        System.out.println(p1.toString());
        p1.shutdown();
    }
}
