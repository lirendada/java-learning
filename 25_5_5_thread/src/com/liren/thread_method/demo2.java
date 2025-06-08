package com.liren.thread_method;

public class demo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            throw new RuntimeException("Uncaught exception in thread");
        });
        t.start();

        t.setUncaughtExceptionHandler((k,e) -> {
            System.out.println("Uncaught exception in thread: " + e.getMessage());
        });

        while(true) {
            System.out.println("主线程运行中...");
            Thread.sleep(1000);
        }
    }
}
