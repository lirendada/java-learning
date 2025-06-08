package com.liren.thread_advanced;

import java.util.concurrent.locks.ReentrantLock;

public class demo3 {
    public static void main(String[] args) {
        ReentrantLock locker = new ReentrantLock();

        locker.lock();
        try {
            // ...
        } finally {
            locker.unlock();
        }
    }
}
