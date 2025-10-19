package com.liren.rabbitmq_advanced.constant;

public class Constants {
    // 消息确认
    public static final String ACK_EXCHANGE_NAME = "ack_exchange";
    public static final String ACK_QUEUE = "ack_queue";


    // 发布确认机制
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    public static final String CONFIRM_QUEUE = "confirm_queue";


    // 重试机制
    public static final String RETRY_EXCHANGE_NAME = "retry_exchange";
    public static final String RETRY_QUEUE = "retry_queue";


    // TTL
    public static final String TTL_TIME = "10000"; // 10s
    public static final String TTL_EXCHANGE_NAME = "ttl_exchange";
    public static final String TTL_QUEUE = "ttl_queue";


    // 死信
    public static final String NORMAL_EXCHANGE = "normal_exchange";
    public static final String NORMAL_QUEUE = "normal_queue";
    public static final String DL_EXCHANGE = "dl_exchange";
    public static final String DL_QUEUE = "dl_queue";


    // 延迟队列
    public static final String DELAY_EXCHANGE = "delay_exchange";
    public static final String DELAY_QUEUE = "delay_queue";


    // 消息分发
    public static final String QOS_EXCHANGE = "qos_exchange";
    public static final String QOS_QUEUE = "qos_queue";
}
