package com.liren.create_thread;

/**
 * 创建线程方式五：lambda表达式创建Thread对象
 */
public class demo5 {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while(true) {
                System.out.println("Hello, world!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "MyThread");
        t.start();

        while(true) {
            try {
                System.out.println("i am main thread");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
