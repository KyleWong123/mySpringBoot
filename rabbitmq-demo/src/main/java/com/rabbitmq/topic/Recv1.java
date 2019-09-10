package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.util.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 20190301511
 */
@Slf4j
public class Recv1 {
    private static final String QUEUE_NAME = "test_queue_fanout_shopmarket1";
    private static final String EXCHANGE_NAME = "test_exchange_topic";
    public static void main(String[] args) throws IOException, TimeoutException {
        log.info("连接rabbitmq服务器");
        Connection connection = ConnectionUtil.getConnection();
        log.info("创建channel");
        Channel channel = connection.createChannel();
        log.info("声明队列");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        log.info("将队列绑定到交换机上");
        /**
         * param1:队列名称
         * param2:交换机名称
         * param3:路由键
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "goods.add");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            log.info(" [1] Received '" + message + "'");
            log.info("done");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
