package com.example.rabbitmq.receiver;

import com.example.rabbitmq.entity.AccountEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author 20190301511
 */
@Component
@Slf4j
public class RabbitmqReceiver {
    @RabbitListener(queues = {"${rabbitmq.queue.msg}"})
    public void receiveMsg(String msg){
        log.info("接收到的msg为{}", msg);
    }

    @RabbitListener(queues = {"${rabbitmq.queue.account}"})
    public void receiveAccount(AccountEntity accountEntity) {
        log.info("接收到的account为{}", accountEntity);
    }
}

