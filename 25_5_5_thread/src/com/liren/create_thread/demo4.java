package com.liren.create_thread;

/**
 * 创建线程方式四：匿名内部类实现Runnable，重写run()
 */
public class demo4 {
    public static void main(String[] args) {
        Runnable myrunnable = new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("MyThread: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread t = new Thread(myrunnable);
        t.start();

        while(true) {
            System.out.println("MyThread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
