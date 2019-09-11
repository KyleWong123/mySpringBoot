package com.example.rabbitmq.send;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 *
 * @author Kyle Wong
 * @version 1.0.0
 * @date 2019/9/10 20:15
 */
@Component
@Slf4j
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(String msg){
        log.info("正在发送消息");
        /*while (true) {
            rabbitTemplate.convertAndSend("test_queue", msg);
            log.info("消息发送成功{}", msg);
        }*/

    }
}
