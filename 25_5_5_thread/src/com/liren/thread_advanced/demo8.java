package com.liren.thread_advanced;

import java.util.concurrent.atomic.AtomicInteger;

public class demo8 {
    private static AtomicInteger count = new AtomicInteger(1);
    public static void main(String[] args) {
        new Thread(() -> {
            count.getAndIncrement();
        }).start();
        new Thread(() -> {
            count.getAndIncrement();
        }).start();
        new Thread(() -> {
            count.getAndIncrement();
        }).start();
        System.out.println(count.get());
    }
}
