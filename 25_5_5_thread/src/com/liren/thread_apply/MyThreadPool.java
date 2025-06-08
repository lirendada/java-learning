package com.liren.thread_apply;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThreadPool {
    private BlockingQueue<Runnable> qe = new LinkedBlockingQueue<>();

    public MyThreadPool(int n) {
        for(int i = 0; i < n; ++i) {
            Thread thread = new Thread(() -> {
                // 让每个线程循环处理阻塞队列中的业务，然后执行
                while (true) {
                    try {
                        Runnable r = qe.take();
                        r.run();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start(); // 别忘了启动线程
        }
    }

    // 将具体业务插入到阻塞队列中，具体调度由阻塞队列自己处理
    public void submit(Runnable r) {
        try {
            qe.put(r);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
