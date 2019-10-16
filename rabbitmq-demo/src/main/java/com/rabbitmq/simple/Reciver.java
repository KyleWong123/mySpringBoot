package com.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.util.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者消费队列中的消息
 *
 * @author 20190301511
 */
@Slf4j
public class Reciver {
    private static final String QUEUE_NAME = "test_simple_name";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        log.info("获取rabbitmq服务器");
        Connection connection = ConnectionUtil.getConnection();
        log.info("创建channel");
        Channel channel = connection.createChannel();
        log.info("创建一个队列声明");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            log.info("消费标记" + consumerTag);
            log.info(" [x] Received '" + message + "'");
        };
        // 设置消息为非自动确认
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
        // channel.basicReject(1, false);
        // channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {channel.basicAck(consumerTag.length(), false);});
        channel.basicAck(1, false);
    }
}
