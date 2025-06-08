package com.liren.thread_apply;

import java.util.PriorityQueue;

class Task implements Comparable<Task> {
    private Runnable task;
    private long time; // 为了方便判断时间是否到达, 所以保存绝对的时间戳

    public Task(Runnable task, long time) {
        this.task = task;
        this.time = System.currentTimeMillis() + time; // 注意这里存放的是时间戳，所以要计算一下
    }

    public Runnable getTask() {
        return task;
    }

    public long getTime() {
        return time;
    }

    @Override
    public int compareTo(Task o) {
        return (int)(this.time - o.time);
    }
}

public class MyTimer {
    // 使用优先级队列作为存放定时任务的容器，且要以时间间隔最小的任务作为堆顶
    private PriorityQueue<Task> qe = new PriorityQueue<>();

    // 队列涉及到多线程操作，需要进行加锁
    // 并且为了避免“忙等”，可以用wait()和notify()配合，来让出CPU资源
    private final Object locker = new Object();

    public MyTimer() {
        // 构建线程去处理定时任务
        Thread thread = new Thread(() -> {
            while(true) {
                // 加锁
                try {
                    synchronized(locker) {
                        // 判断是否有定时任务
                        if(qe.isEmpty() == true) {
                            // 直接continue会导致忙等，浪费cpu资源
                            locker.wait();
                        }

                        // 走到这说明存在定时任务，则判断是否到达执行时间
                        Task t = qe.peek();
                        if(t.getTime() > System.currentTimeMillis()) {
                            // 时间还没到，直接continue同样会造成忙等，浪费cpu资源，所以这里同样使用wait()等待唤醒
                            // 不同的是这里要设置超时时间，因为在wait()阻塞到该定时任务时间到之前如果没有新的任务来的话，这个线程
                            // 都不会被唤醒，导致任务没有及时处理，所以要设置超时时间为剩余等待时间！
                            long gap = t.getTime() - System.currentTimeMillis();
                            locker.wait(gap);
                        } else {
                            // 时间到了，执行任务
                            t.getTask().run();
                            qe.poll();
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }

    // 插入定时任务
    public void schedule(Runnable r, long delay) {
        synchronized (locker) {
            qe.offer(new Task(r, delay));
            locker.notify();
        }
    }
}
