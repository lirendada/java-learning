package com.liren;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class t1 {
    public static void main1(String[] args) {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        System.out.println(a.add(b));// 1.9
        System.out.println(a.subtract(b));// 0.1
        System.out.println(a.multiply(b));// 0.90
//        System.out.println(a.divide(b));// 无法除尽，抛出 ArithmeticException 异常
        System.out.println(a.divide(b, 2, RoundingMode.HALF_UP));// 1.11

        BigInteger c = new BigInteger("10");
        BigInteger d = new BigInteger("3");
        System.out.println(c.add(d));// 13
    }

    public static void main2(String[] args) throws InterruptedException {
        System.out.println("A");
        System.out.println("B");

        Thread t = new Thread(() -> {
            System.out.println("1");
            System.out.println("2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
        t.join();

        System.out.println("C");
        System.out.println("D");
    }

    public static void main3(String[] args) throws InterruptedException {
        // 打印所有状态
        for(Thread.State state : Thread.State.values()) {
            System.out.println(state);
        }
        System.out.println("-----------");

        // 打印 t 线程某个时刻的状态
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

    private static int count = 0;

    public static void main4(String[] args) {
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

    private static int num = 1;
    private static final int MAX = 100;
    private static final Object lock = new Object();
    private static int flag = 0; // 0:线程1, 1:线程2, 2:线程3

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printNumber(0));
        Thread t2 = new Thread(() -> printNumber(1));
        Thread t3 = new Thread(() -> printNumber(2));

        t1.start();
        t2.start();
        t3.start();
    }

    private static void printNumber(int threadId) {
        while(true) {
            synchronized (lock) {
                while(threadId != flag) {
                    if(num > MAX) {
                        lock.notifyAll();
                        return;
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if(num > MAX) {
                    flag = (threadId + 1) % 3;
                    lock.notifyAll();
                    return;
                }

                System.out.println("线程" + (threadId + 1) + "：" + num);
                flag = (threadId + 1) % 3;
                num++;
                lock.notifyAll();
            }
        }
    }
}
