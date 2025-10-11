package com.liren.publisher_confirms;

import com.liren.constant.Constants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

public class PublisherConfirms {
    public static final Integer MESSAGE_SIZE = 10000;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        // Strategy 1: Publishing Messages Individually
        PublishingMessagesIndividually();

        // Strategy 2: Publishing Messages in Batches
        PublishingMessagesInBatchesy();

        // Strategy 3: Handling Publisher Confirms Asynchronously
        HandlingPublisherConfirmsAsynchronously();
    }

    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(Constants.IP);
        factory.setPort(Constants.PORT);
        factory.setVirtualHost(Constants.VIRTUALHOST);
        factory.setUsername(Constants.USERNAME);
        factory.setPassword(Constants.PASSWORD);
        Connection connection = factory.newConnection();
        return connection;
    }

    // 单独确认
    public static void PublishingMessagesIndividually() throws IOException, TimeoutException, InterruptedException {
        try (Connection connection = getConnection()) {
            // 1. 获取通道
            Channel channel = connection.createChannel();

            // 2. 开启confirm模式
            channel.confirmSelect();

            // 3. 声明队列
            channel.queueDeclare(Constants.PUBLISH_CONFIRM_QUEUE1, true, false, true, null);

            // 4. 发送消息
            long start = System.currentTimeMillis();
            for(int i = 0; i < MESSAGE_SIZE; ++i) {
                String text = "hello publisher_confirms";
                channel.basicPublish("", Constants.PUBLISH_CONFIRM_QUEUE1, null, text.getBytes(StandardCharsets.UTF_8));

                // waitForConfirmsOrDie() 阻塞当前线程，直到 RabbitMQ 返回所有已发送消息的确认。
                // 如果超时过期, 则抛出TimeoutException。如果任何消息被nack(丢失), 则抛出IOException。
                channel.waitForConfirmsOrDie(5000);
            }
            long end = System.currentTimeMillis();
            System.out.println("PublishingMessagesIndividually花费了：" + (end - start) + "ms");
        }
    }

    // 批量确认
    public static void PublishingMessagesInBatchesy() throws IOException, TimeoutException, InterruptedException {
        try (Connection connection = getConnection()) {
            // 1. 获取通道
            Channel channel = connection.createChannel();

            // 2. 开启confirm模式
            channel.confirmSelect();

            // 3. 声明队列
            channel.queueDeclare(Constants.PUBLISH_CONFIRM_QUEUE2, true, false, true, null);

            // 4. 发送消息
            int batchSize = 200;             // 一次确认的消息数量
            int outstandingMessageCount = 0; // 记录当前已经

            long start = System.currentTimeMillis();
            for (int i = 0; i < MESSAGE_SIZE; ++i) {
                // basicPublish() 只是把消息写入到 TCP 缓冲区，并不代表消息真的到达了 RabbitMQ 服务器或被持久化。
                // 💥在 Publisher Confirms 模式下，只有 waitForConfirms() 或 waitForConfirmsOrDie() 收到确认后，消息才算真正安全投递成功。
                String text = "hello publisher_confirms";
                channel.basicPublish("", Constants.PUBLISH_CONFIRM_QUEUE2, null, text.getBytes(StandardCharsets.UTF_8));
                outstandingMessageCount++;

                // 批量确认消息
                if (outstandingMessageCount == batchSize) {
                    channel.waitForConfirmsOrDie(5000);
                    outstandingMessageCount = 0;
                }
            }
            // 消息发送完, 还有未确认的消息, 则进行确认
            if (outstandingMessageCount > 0) {
                channel.waitForConfirmsOrDie(5000);
            }

            long end = System.currentTimeMillis();
            System.out.println("PublishingMessagesInBatchesy花费了：" + (end - start) + "ms");
        }
    }

    // 异步确认
    public static void HandlingPublisherConfirmsAsynchronously() throws IOException, TimeoutException, InterruptedException {
        try (Connection connection = getConnection()) {
            // 1. 获取通道
            Channel channel = connection.createChannel();

            // 2. 开启confirm模式
            channel.confirmSelect();

            // 3. 声明队列
            channel.queueDeclare(Constants.PUBLISH_CONFIRM_QUEUE3, true, false, true, null);

            // 创建一个有序集合SortedSet，存放delivery序号
            SortedSet<Long> set = Collections.synchronizedSortedSet(new TreeSet<>());

            // 4. 添加回调接口
            channel.addConfirmListener(
                (deliveryTag, multiple) -> {
                    if (multiple) {
                        // 批量确认：获取小于等于deliveryTag的序号集合，进行删除，表示这批序号的消息都已经被ack了
                        set.headSet(deliveryTag + 1).clear();
                    } else {
                        // 单条确认：将当前的deliveryTag从集合中移除
                        set.remove(deliveryTag);
                    }
                },
                (deliveryTag, multiple) -> {
                    if (multiple) {
                        // 批量确认：获取小于等于deliveryTag的序号集合，进行删除，表示这批序号的消息都已经被ack了
                        set.headSet(deliveryTag + 1).clear();
                    } else {
                        // 单条确认：将当前的deliveryTag从集合中移除
                        set.remove(deliveryTag);
                    }
                    // 如果处理失败, 这里需要添加处理消息重发的场景，此处代码省略
                }
            );

            // 5. 发送消息
            long start = System.currentTimeMillis();
            for(int i = 0; i < MESSAGE_SIZE; ++i) {
                String text = "hello publisher_confirms";

                // 获取下一次发送的序号，必须在basicPublish之前调用，否则会出现错位！💥
                long nextPublishSeqNo = channel.getNextPublishSeqNo();

                channel.basicPublish("", Constants.PUBLISH_CONFIRM_QUEUE3, null, text.getBytes(StandardCharsets.UTF_8));

                // 将序号存放到有序集合中
                set.add(nextPublishSeqNo);
            }

            // 确认消息都确认完毕
            while(!set.isEmpty()) {
                Thread.sleep(10);
            }

            long end = System.currentTimeMillis();
            System.out.println("HandlingPublisherConfirmsAsynchronously花费了：" + (end - start) + "ms");
        }
    }
}
