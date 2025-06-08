package com.liren.create_thread;

/**
 * 创建线程方式三：匿名内部类继承Thread，重写run()
 */
public class demo3 {
    public static void main(String[] args) {
        Thread t = new Thread() {
            public void run() {
                while(true) {
                    System.out.println("MyThread: " + this.getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t.start();

        while(true) {
            System.out.println("MainThread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
