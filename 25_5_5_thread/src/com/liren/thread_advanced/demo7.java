package com.liren.thread_advanced;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class demo7 {
    private static int count = 1;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<Integer> future = pool.submit(() -> {
            int ret = 0;
            for(int i = 1; i <= 1000; ++i) {
                ret += i;
            }
            return ret;
        });
        System.out.println(future.get());
        pool.shutdown();
    }
}
