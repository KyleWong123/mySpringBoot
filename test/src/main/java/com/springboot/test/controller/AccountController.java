package com.springboot.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springboot.test.model.Account;
import com.springboot.test.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/26 10:25
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/saveaccount")
    public String saveAccount(Account account){
        int result = accountService.saveAccount(account);
        /*ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();*/
        if (result > 0) {
            log.info("success");
            return "success";
        }
        return "false";


    }
}
