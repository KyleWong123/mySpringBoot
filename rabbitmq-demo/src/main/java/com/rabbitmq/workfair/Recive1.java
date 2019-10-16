package com.rabbitmq.workfair;

import com.rabbitmq.client.*;
import com.rabbitmq.util.ConnectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 20190301511
 */
@Slf4j
public class Recive1 {
    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接
        log.info("获取rabbitmq服务器");
        Connection connection = ConnectionUtil.getConnection();
        log.info("创建channel");
        Channel channel = connection.createChannel();
        log.info("创建一个队列声明");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 保证一次之分发一个
        channel.basicQos(1);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            log.info(" [1] Received '" + message + "'");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("[1] done");
                channel.basicAck(consumerTag.hashCode(), true);
            }
        };
        // 关闭自动应答
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }
}
