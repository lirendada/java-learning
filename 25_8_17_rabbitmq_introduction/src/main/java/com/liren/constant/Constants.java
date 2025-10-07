package com.liren.constant;

public class Constants {
    public static final String IP = "127.0.0.1";
    public static final int PORT = 5672;
    public static final String VIRTUALHOST = "lirendada";
    public static final String USERNAME = "liren";
    public static final String PASSWORD = "123456";

    // 工作队列模式
    public static final String WORK_QUEUE = "work.queue";

    // 发布订阅模式
    public static final String FANOUT_QUEUE1 = "fanout.queue1";
    public static final String FANOUT_QUEUE2 = "fanout.queue2";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";

    // 路由模式
    public static String DIRECT_EXCHANGE = "direct.exchange";
    public static String DIRECT_QUEUE1 = "direct.queue1";
    public static String DIRECT_QUEUE2 = "direct.queue2";

    // 通配符模式
    public static String TOPIC_EXCHANGE = "topic.exchange";
    public static String TOPIC_QUEUE1 = "topic.queue1";
    public static String TOPIC_QUEUE2 = "topic.queue2";
}
