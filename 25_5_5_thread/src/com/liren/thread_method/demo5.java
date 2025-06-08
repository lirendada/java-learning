package com.liren.thread_method;

public class demo5 {
    public static void main(String[] args) {
        // 打印所有状态
        for(Thread.State state : Thread.State.values()) {
            System.out.println(state);
        }

    }
}
