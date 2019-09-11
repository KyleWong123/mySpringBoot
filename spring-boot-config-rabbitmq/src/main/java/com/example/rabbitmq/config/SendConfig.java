package com.example.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <pre>
 *
 * </pre>
 *
 * @author Kyle Wong
 * @version 1.0.0
 * @date 2019/9/10 20:10
 */
@Configuration
public class SendConfig {
    @Bean
    public Queue queue(){
        return new Queue("test_queue");
    }
}
