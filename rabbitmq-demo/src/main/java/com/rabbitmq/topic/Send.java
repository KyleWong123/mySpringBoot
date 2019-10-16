package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 20190301511
 */
@Slf4j
public class Send {
    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        log.info("连接rabbitmq服务器");
        Connection connection = ConnectionUtil.getConnection();
        log.info("创建channel");
        Channel channel = connection.createChannel();
        log.info("声明交换机---主题方式");
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        log.info("要发送的消息");
        String msg = "hello goods ...";
        log.info("指定路由key");
        String routingKey = "goods.add";
        log.info("发送消息");
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, msg.getBytes());
        log.info("消息发送成功{}", msg);
        channel.close();
        connection.close();
    }
}
