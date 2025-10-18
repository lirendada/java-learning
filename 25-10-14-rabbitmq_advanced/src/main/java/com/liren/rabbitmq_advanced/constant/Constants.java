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
}
