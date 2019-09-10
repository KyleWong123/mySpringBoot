package com.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 获取MQ连接的工具类
 * @author 20190301511
 */
public class ConnectionUtil {
    /**
     * 获取MQ连接方法
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        // 定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置服务地址
        connectionFactory.setHost("127.0.0.1");
        //  设置服务端口
        connectionFactory.setPort(5672);
        // 设置vhost
        connectionFactory.setVirtualHost("/vhost");
        // 设置用户名
        connectionFactory.setUsername("user");
        // 设置密码
        connectionFactory.setPassword("123");
        return connectionFactory.newConnection();
    }
}
