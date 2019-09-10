package com.rabbitmq.work;

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
    private static final String QUEUE_NAME = "test_work_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        log.info("获取rabbitmq服务连接");
        Connection connection = ConnectionUtil.getConnection();
        log.info("获取channel");
        Channel channel = connection.createChannel();
        log.info("声明队列");
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        log.info("发送消息");
        for (int i = 0; i < 50; i++) {
            String msg = "hello " + i;
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            log.info(msg);
            Thread.sleep(2000);
        }
        channel.close();
        connection.close();
    }
}
