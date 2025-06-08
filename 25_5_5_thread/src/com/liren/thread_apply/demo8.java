package com.liren.thread_apply;

import java.util.Timer;
import java.util.TimerTask;

public class demo8 {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器执行3000ms");
            }
        }, 3000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器执行2000ms");
            }
        }, 2000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时器执行1000ms");
            }
        }, 1000);
        System.out.println("主线程！！！");
        Thread.sleep(4000);
        timer.cancel();
    }
}
