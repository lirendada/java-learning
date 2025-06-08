package com.liren.thread_apply;

public class MyBlockingQueue {
    // 使用循环队列模拟阻塞队列
    private String[] arr;
    private int head = 0;
    private int tail = 0;
    private int size; // 有效元素个数
    private static final Object lock = new Object();

    public MyBlockingQueue(int capacity) {
        arr = new String[capacity];
    }

    public void put(String value) throws InterruptedException {
        synchronized (lock) {
            // 用while来判断队列是否满，看看是否要阻塞
            while(size >= arr.length) {
                lock.wait();
            }

            // 插入数据
            arr[tail] = value;
            tail = (tail + 1) % arr.length;
            size++;

            // 唤醒消费者
            lock.notify();
        }
    }

    public String take() throws InterruptedException {
        synchronized (lock) {
            // 用while来判断队列是否空，看看是否要阻塞
            while(size == 0) {
                lock.wait();
            }

            // 拿到数据
            String value = arr[head];
            head = (head + 1) % arr.length;
            size--;

            // 唤醒生产者
            lock.notify();
            return value;
        }
    }
}
