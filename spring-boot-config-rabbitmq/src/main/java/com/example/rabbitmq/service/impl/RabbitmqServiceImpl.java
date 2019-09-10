package com.example.rabbitmq.service.impl;

/**
 * @author
 */
import com.example.rabbitmq.entity.AccountEntity;
import com.example.rabbitmq.service.RabbitmqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitmqServiceImpl implements RabbitmqService, RabbitTemplate.ConfirmCallback {
    @Value("${rabbitmq.queue.msg}")
    private String msgRouting = null;
    @Value("${rabbitmq.queue.account}")
    private String accountRouting = null;
    @Autowired
    private RabbitTemplate rabbitTemplate = null;

    /**
     * 发送消息
     * @param msg
     */
    @Override
    public boolean sendMsg(String msg) {
        log.info("发送的msg为{}", msg);
        // 设置回调
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(msgRouting, msg);
        return true;
    }

    /**
     * 发送用户对象
     * @param accountEntity
     */
    @Override
    public boolean sendAccount(AccountEntity accountEntity) {
        log.info("发送的account为{}", accountEntity);
       // rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.convertAndSend(accountRouting, accountEntity);
        return true;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            log.info("消息消费成功");
        } else {
            log.info("消息消费失败");
        }
    }
}
