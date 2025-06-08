package com.liren.thread_advanced;

public class demo5 {
    private final static Object locker1 = new Object();
    private final static Object locker2 = new Object();

    // 死锁演示
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (locker1) {
                System.out.println(Thread.currentThread().getName() + "持有locker1");
                try {
                    Thread.sleep(100); // 模拟操作耗时，增加死锁概率
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "等待获取locker2...");
                synchronized(locker2) {
                    // ...
                }
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            synchronized (locker2) {
                System.out.println(Thread.currentThread().getName() + "持有locker2");
                try {
                    Thread.sleep(100); // 模拟操作耗时，增加死锁概率
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "等待获取locker1...");
                synchronized(locker1) {
                    // ...
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
