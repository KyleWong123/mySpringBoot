//package com.example.rabbitmq;
//
//import com.example.rabbitmq.send.Sender;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringBootConfigRabbitmqApplicationTests {
//
//    @Autowired
//    private Sender sender;
//
//    @Test
//    public void contextLoads() {
//    }
//
//    @Test
//    public void rabbitMQTest() {
//        sender.sendByFanout("hello rabbitmq");
//    }
//    @Test
//    public void rabbitMQ() {
//        sender.send("hello rabbitmq");
//    }
//
//}
