package com.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.util.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 20190301511
 * *******       *******
 * *******        *******  ------*  MQ * ------*  C  *
 * *  P  * ------ *  X  *        *******       *******
 * *******        *******  ------*******       *******
 * *  MQ * ------*  C  *
 * *******       *******
 */
@Slf4j
public class Send {
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        log.info("连接rabbitmq服务器");
        Connection connection = ConnectionUtil.getConnection();
        log.info("创建channel");
        Channel channel = connection.createChannel();
        log.info("声明交换机---分发方式");
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        log.info("发送消息");
        String msg = "hello ps";
        for (int i = 0; i <2 ; i++) {
            channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
            log.info("消息发送成功 ", msg);
        }
        channel.close();
        connection.close();
    }
}
