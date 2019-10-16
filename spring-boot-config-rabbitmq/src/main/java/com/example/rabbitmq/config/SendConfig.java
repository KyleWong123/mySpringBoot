package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
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
    public static final String ROUTING_NAME = "direct";
    @Bean
    public Queue queue() {
        return new Queue("test_queue", false, false, false);
    }
    @Bean
    public Queue queue2() {
        return new Queue("test_queue2");
    }
    @Bean
    public Queue queue1(){
        return new Queue("test_queue1");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout");
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct");
    }

    @Bean
    public Binding bindingExchange(){
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }
    @Bean
    public Binding bindingExchange1(){
        return BindingBuilder.bind(queue1()).to(fanoutExchange());
    }
    /*@Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(queue1()).to(directExchange()).with(ROUTING_NAME);
    }*/
}
