package com.liren.thread_apply;

public class demo1 {
    public static void main(String[] args) {
        StarvingSingleton starvingSingleton = StarvingSingleton.getInstance();
        System.out.println(starvingSingleton.hashCode());
//        StarvingSingleton starvingSingleton2 = new StarvingSingleton();

        LazySingleton s1 = LazySingleton.getInstance();
        LazySingleton s2 = LazySingleton.getInstance();
        System.out.println(s1 == s2);
    }
}
