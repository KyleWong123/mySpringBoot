package com.example.demo.controller;

import com.example.demo.entity.AccountEntity;
import com.example.demo.service.IAccountService;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService iAccountService;
    static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/findall")
    public String findAll(){
        List<AccountEntity> accountEntityList = iAccountService.findAllList();
        if(!accountEntityList.isEmpty()){
            return "success";
        }
        return "false";
    }

    @GetMapping("/findbyid/{id}")
    public String findById(@PathVariable("id") int id) {
        AccountEntity accountEntity = iAccountService.findById(id);
        logger.info("查看到账户信息："+accountEntity.getId()+accountEntity.getMoney()+accountEntity.getName());
        if (accountEntity!=null) {
            return "success:" + accountEntity.getName() + ":"+accountEntity.getMoney();
        }else {
            return null;
        }
    }

    @PostMapping("/saveaccount")
    public String saveAccount(AccountEntity accountEntity,@RequestParam(value = "name") String name, @RequestParam(value = "money") double money){
        accountEntity.setMoney(money);
        accountEntity.setName(name);
        int flag = iAccountService.saveAccount(accountEntity);
        if (flag == 1) {
            return "success";
        }else {
            return "false";
        }

    }

    @PostMapping("/updateaccount/{id}")
    public String updateAccount(@PathVariable("id") int id, AccountEntity accountEntity) {
        int count = iAccountService.updateAccount(accountEntity);
        if (count==1){
            return "success";
        }else {
            return "false";
        }
    }
    @GetMapping("/deleteaccount/{id}")
    public String deleteAccount(@PathVariable("id") int id){
        int count = iAccountService.deleteAccount(id);
        if (count==1){
            return "success";
        }else {
            return "false";
        }
    }
}
