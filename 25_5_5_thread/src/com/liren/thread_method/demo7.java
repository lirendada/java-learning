package com.liren.thread_method;

public class demo7 {
    static Thread[] ts = new Thread[20];

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0; i < 20; ++i) {
            final int index = i;
            ts[i] = new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " " + index);
            });
            ts[i].start();
        }

        for(int i = 0; i < 20; ++i) {
            ts[i].join();
        }

        System.out.println("ok");
    }
}
