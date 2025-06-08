package com.liren.thread_method;

public class demo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
           // 还没被打断则进行循环
           while(Thread.currentThread().isInterrupted() == false) {
               System.out.println(Thread.currentThread().getName());
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   // 处理可能剩余的业务，而不至于直接退出导致业务完成一半
                   // ...
                   break;
               }
           }
        });
        t.start();

        System.out.println("main thread, 3秒后中断子线程");
        Thread.sleep(3000);
        t.interrupt();
    }
}
