package com.liren.thread_security;

import java.util.Scanner;

/**
 * 内存可见性问题
 */
public class demo1 {
    private static volatile int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
           while(count == 0) {
               // do something
           }
            System.out.println("t1循环结束！！！");
        });

        Thread t2 = new Thread(() -> {
           Scanner sc = new Scanner(System.in);
           count = sc.nextInt();
            System.out.println("t2结束！！！");
        });

        t1.start();
        t2.start();
    }
}
