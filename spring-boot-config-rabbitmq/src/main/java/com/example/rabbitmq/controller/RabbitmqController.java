package com.example.rabbitmq.controller;

import com.example.rabbitmq.entity.AccountEntity;
import com.example.rabbitmq.service.RabbitmqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 20190301511
 */
@RestController
@Slf4j
@RequestMapping("/rabbitmq")
public class RabbitmqController {
    @Autowired
    private RabbitmqService rabbitmqService = null;

    /**
     * 发送消息控制层
     * @param msg
     * @return
     */
    @GetMapping("/sendmsg")
    public String sendMsg(String msg){
        boolean bool = rabbitmqService.sendMsg(msg);
        if (bool) {
            log.info("消息发送成功");
            return "success";
        } else {
            log.info("消息发送失败");
            return "failed";
        }
    }

    /***
     *
     * @param id
     * @param name
     * @param money
     * @return
     */
    @GetMapping("/sendaccount")
    public String sendAccount(Integer id, String name, Double money){
        AccountEntity accountEntity = new AccountEntity(id, name, money);
        boolean bool = rabbitmqService.sendAccount(accountEntity);
        if (bool) {
            log.info("消息发送成功");
            return "success";
        } else {
            log.info("消息发送失败");
            return "failed";
        }
    }

}
