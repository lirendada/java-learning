package com.liren.thread_method;

import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class demo6 {
    public static int[] arr = new int[10000000];
    public static int sum = 0;
    public static Object locker = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 生成随机数字
        Random rand = new Random();
        for(int i = 0; i < 10000000; i++) {
            arr[i] = rand.nextInt(100) + 1;
        }

        Thread t1 = new Thread(() -> {
           for(int i = 0; i < 10000000; i+=2) {
               synchronized(locker) {
                    sum += arr[i];
               }
           }
        });

        Thread t2 = new Thread(() -> {
            for(int i = 1; i < 10000000; i+=2) {
                synchronized(locker) {
                    sum += arr[i];
                }
            }
        });

        Long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Long end = System.currentTimeMillis();
        System.out.println(sum);
        System.out.println(end - start + "ms");
    }
}
