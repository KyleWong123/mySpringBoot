package com.rabbitmq.simple;

/**
 * @author
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@Slf4j
public class Send {
    private static final String QUEUE_NAME = "test_simple_name";
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        log.info("连接rabbitmq服务器");
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中获取一个通道
        log.info("创建channel成功");
        Channel channel = connection.createChannel();
        log.info("创建一个队列");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 要发送的消息
        String msg = "hello simple !";
        log.info("发送消息");
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        log.info("发送的消息为{}", msg);
        // 关闭通道和连接
        channel.close();
        connection.close();

    }
}
