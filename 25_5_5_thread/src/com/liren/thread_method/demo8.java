package com.liren.thread_method;

public class demo8 {
    private static final Object locker = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            log("尝试获取第一层锁");
            synchronized (locker) {
                log("进入第一层锁");

                log("尝试获取第二层锁");
                synchronized (locker) {
                    log("进入第二层锁");

                    log("尝试获取第三层锁");
                    synchronized (locker) {
                        log("进入第三层锁");

                        log("尝试获取第四层锁");
                        synchronized (locker) {
                            log("进入第四层锁");
                            // 模拟执行逻辑
                        }
                        log("退出第四层锁");
                    }
                    log("退出第三层锁");
                }
                log("退出第二层锁");
            }
            log("退出第一层锁");
        });

        Thread t2 = new Thread(() -> {
            sleep(1000); // 保证 t1 提前获取锁
            log("线程2尝试获取锁");
            synchronized (locker) {
                log("线程2获得了锁");
            }
        });

        t1.start();
        t2.start();
    }

    private static void log(String msg) {
        System.out.printf("[%s] %s%n", Thread.currentThread().getName(), msg);
    }

    private static void sleep(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException e) { }
    }
}
