package com.liren.thread_synchronization;

public class demo1 {
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        synchronized (lock) {
            lock.wait();
        }
    }
}
