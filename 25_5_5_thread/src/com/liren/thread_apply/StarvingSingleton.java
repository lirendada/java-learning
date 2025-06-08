package com.liren.thread_apply;

public class StarvingSingleton {
    // 静态变量，存储单例对象，使用final修饰
    private static final StarvingSingleton instance = new StarvingSingleton();

    // 私有化构造方法，防止外部通过new创建对象
    private StarvingSingleton() {
    }

    // 提供获取单例成员接口
    public static StarvingSingleton getInstance() {
        return instance;
    }
}
