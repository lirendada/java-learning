package com.liren.thread_apply;

public class demo3 {
    public static void main(String[] args) {
        MyBlockingQueue qe = new MyBlockingQueue(10);
        Thread[] producers = new Thread[2];
        Thread[] consumers = new Thread[3];
        for(int i = 0; i < producers.length; i++) {
            producers[i] = new Thread(() -> {
                int index = 0;
                while(true) {
                    try {
                        qe.put("liren" + index++);
                        System.out.println(Thread.currentThread().getName() + "放入数据成功！");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "producer" + i);
        }
        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(() -> {
                while(true) {
                    int index = 0;
                    try {
                        String value = qe.take();
                        System.out.println(Thread.currentThread().getName() + "拿到数据：" + value);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "consumer" + i);
        }
        for(int i = 0; i < producers.length; i++) {
            producers[i].start();
        }
        for(int i = 0; i < consumers.length; i++) {
            consumers[i].start();
        }
    }
}
