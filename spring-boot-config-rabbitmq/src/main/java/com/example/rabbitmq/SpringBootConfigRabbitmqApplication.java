package com.example.rabbitmq;

/**
 * @author
 */

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootConfigRabbitmqApplication {
    @Value("${rabbitmq.queue.msg}")
    private String magQueueName = null;
    @Value("${rabbitmq.queue.account}")
    private String accountQueueName = null;

    /**
     * 创建字符串消息队列，boolean表示是否持久化
     *
     * @return
     */
    @Bean
    public Queue createMsgQueue() {
        return new Queue(magQueueName, true);
    }

    /**
     * 创建用户对象消息队列，boolean表示是否持久化
     *
     * @return
     */
    @Bean
    public Queue creatAccountQueue() {
        return new Queue(accountQueueName, true);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigRabbitmqApplication.class, args);
    }

}
