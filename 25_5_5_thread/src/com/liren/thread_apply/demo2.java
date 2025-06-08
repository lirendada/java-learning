package com.liren.thread_apply;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用BlockingQueue
 */
public class demo2 {
    public static void main(String[] args) {
        BlockingQueue<String> qe = new ArrayBlockingQueue<>(10);

        Thread t1 = new Thread(() -> {
           while(true) {
               try {
                   qe.put("liren");
                   System.out.println(Thread.currentThread().getName() + "放入一个数据");
                   Thread.sleep(500);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });

        Thread t2 = new Thread(() -> {
            while(true) {
                try {
                    String value = qe.take();
                    System.out.println(Thread.currentThread().getName() + "拿到数据： " + value);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
