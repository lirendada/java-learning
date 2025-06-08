package com.liren.thread_advanced;

import java.util.concurrent.CountDownLatch;

public class demo4 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(10);

        for(int i = 0; i < 10; ++i) {
            int index = i;
            new Thread(() -> {
                System.out.println("子线程" + index + " 开始执行");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("子线程" + index + " 执行完毕");
                cdl.countDown();
            }).start();
        }

        System.out.println("主线程等待所有子线程完成...");
        cdl.await(); // 等待所有 countDown 执行完毕
        System.out.println("所有子线程已完成，主线程继续");
    }
}
