package com.liren.thread_apply;

public class LazySingleton {
    // 静态变量，存储单例对象，同时使用volatile修饰
    private static volatile LazySingleton instance = null;

    // 私有化构造方法，防止外部通过new创建对象
    private LazySingleton() {
    }

    // 提供一个公共的静态方法，返回单例对象
    public static LazySingleton getInstance() {
        if(instance == null) { // 第一次检查
            synchronized(LazySingleton.class) {
                if(instance == null) { // 第二次检查
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
