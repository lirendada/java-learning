package com.liren.create_thread;

/**
 * 创建线程方式一：继承Thread类，重写run()
 */
class MyThread extends Thread {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                System.out.println("MyThread: " + this.getName()); // 直接用this即可
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class demo1 {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

        while(true) {
            try {
                Thread.sleep(1000);
                System.out.println("Main Thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
