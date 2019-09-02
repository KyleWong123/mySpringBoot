package com.example.demo.listener;

/***
 * @author
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        // 发送的消息内容
        String body = new String(message.getBody());
        log.info("发送的消息内容{}", body);
        // 频道channel
        String channel = new String(bytes);
        log.info("要被订阅的channel为{}", channel);

    }
}
