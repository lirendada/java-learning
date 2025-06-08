package com.liren.thread_apply;

import java.util.concurrent.*;

public class demo5 {
    ThreadPoolExecutor etor = new ThreadPoolExecutor(3
    , 5
    , 60
    , TimeUnit.SECONDS
    , new LinkedBlockingQueue<>(10)
    , r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });
}
