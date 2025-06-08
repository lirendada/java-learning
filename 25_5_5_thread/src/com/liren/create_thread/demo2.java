package com.liren.create_thread;

/**
 * 创建线程方式二：实现Runnable接口，重写run()，然后传给Thread
 */
class MyRunnable implements Runnable {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                System.out.println("MyThread: " + Thread.currentThread().getName()); // 不能用this
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class demo2 {
    public static void main(String[] args) {
        // 传给Thread，并给线程命名
        Thread t = new Thread(new MyRunnable(), "MyThread");
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
