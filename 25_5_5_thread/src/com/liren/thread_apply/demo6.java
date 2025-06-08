package com.liren.thread_apply;

public class demo6 {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPool mtp = new MyThreadPool(5);
        for(int i = 0; i < 100; ++i) {
            int index = i;
            mtp.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "处理" + index + "号业务");
            });
        }
    }
}
