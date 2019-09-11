package com.example.rabbitmq.service;

import com.example.rabbitmq.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 */
public interface RabbitmqService {
    /**
     * 发送消息
     * @param msg
     */
    boolean sendMsg(String msg);

    /**
     * 发送用户对象
     * @param accountEntity
     */
    boolean sendAccount(AccountEntity accountEntity);
}
