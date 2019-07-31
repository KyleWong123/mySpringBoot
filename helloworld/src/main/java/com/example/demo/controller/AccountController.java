package com.example.demo.controller;

import com.example.demo.entity.AccountEntity;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService iAccountService;
    @RequestMapping(value = "/getaccounts",method = RequestMethod.GET)
    public List<AccountEntity> getAccounts() {
        return iAccountService.findAllList();
    }
    @GetMapping("/getbyid/{id}")
    public AccountEntity getById(@PathVariable("id") int id) {
        return iAccountService.findById(id);
    }

    @PostMapping("/setaccount/{id}")
    public String setAccount(@PathVariable("id") int id,@RequestParam(value = "name") String name) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(id);
        accountEntity.setName(name);
        int count = iAccountService.update(accountEntity);
        if(count>=1){
            return accountEntity.toString() + "success";
        }else {
            return "false";
        }
    }

    @PostMapping(value = "/addcount")
    public String addCount(@RequestParam(value = "name") String name,@RequestParam(value = "money") double money) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(name);
        accountEntity.setMoney(money);
        int count = iAccountService.add(accountEntity);
        if(count==1) {
            return accountEntity.toString() + "success";
        }else {
            return "false";
        }
    }

    @GetMapping("/deleteaccount/{id}")
    public int deleteAccount(@PathVariable(value = "id") int id) {
        return iAccountService.delete(id);
    }
}
