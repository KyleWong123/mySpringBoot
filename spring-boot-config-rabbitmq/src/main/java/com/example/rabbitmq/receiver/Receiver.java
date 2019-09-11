package com.example.rabbitmq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

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
public class Receiver {
    @RabbitListener(queues = "test_queue")
    public void receive(String msg){
        log.info("接受到的消息{}", msg);
    }
}
