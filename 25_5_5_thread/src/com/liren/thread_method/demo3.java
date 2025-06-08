package com.liren.thread_method;

public class demo3 {
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().interrupt(); // 主线程被中断

        System.out.println(Thread.interrupted()); // true（并清除中断状态）
        System.out.println(Thread.interrupted()); // false（因为已经被清除了）
    }
}
