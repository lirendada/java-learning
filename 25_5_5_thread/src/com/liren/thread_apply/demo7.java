package com.liren.thread_apply;

public class demo7 {
    private final static Object lock1 = new Object();
    private final static Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                synchronized(lock2) {
                    lock2.wait();
                }
                System.out.println('a');
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                synchronized (lock1) {
                    lock1.wait();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized(lock2) {
                lock2.notify();
            }
            System.out.println('b');
        });

        Thread t3 = new Thread(() -> {
            System.out.println('c');
            synchronized (lock1) {
                lock1.notify();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
