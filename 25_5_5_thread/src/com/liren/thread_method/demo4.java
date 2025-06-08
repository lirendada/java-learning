package com.liren.thread_method;

public class demo4 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for(int i = 0;i < 5;i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println(t.getState() + " " + t.isAlive());
        t.start();
        System.out.println(t.getState() + " " + t.isAlive());

        t.join();
        System.out.println(t.getState() + " " + t.isAlive());
    }
}
