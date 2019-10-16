package com.example.rabbitmq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Kyle Wong
 * @version 1.0.0
 * @date 2019/9/10 20:20
 */
@Slf4j
@Component
public class Receiver {
    @RabbitListener(queues = "test_queue")

    public void receive(String msg) {
        log.info("receiver接受到的消息{}", msg);
    }

    @RabbitListener(queues = "test_queue1")
    public void receive1(String msg) {
        log.info("receiver1接受到的消息{}", msg);
    }
    @RabbitListener(queues = "test_queue2")
    public void receive2(String msg) {
        log.info("receiver2接受到的消息{}", msg);
    }

}
