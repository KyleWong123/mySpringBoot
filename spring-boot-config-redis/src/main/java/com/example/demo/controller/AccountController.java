package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.AccountEntity;
import com.example.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/***
 * @author
 */
@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    private AccountEntity accountEntity = null;
    List<AccountEntity> accountEntityList = null;

    /***
     * 根据id查询信息controller
     * @param id
     * @return
     */
    @GetMapping("/getaccountbyid/{id}")
    public String getAccountById(@PathVariable("id") Integer id){
        //accountEntity = new AccountEntity();
        accountEntity = accountService.getAccount(id);
        String json = JSON.toJSONString(accountEntity);
        if (accountEntity == null) {
            return "failed";
        }
        return "success" + json;
    }

    /***
     * 根据条件查询信息controller
     * @param name
     * @param money
     * @return
     */
    @GetMapping("/listaccount")
    public String listAccount(String name, Double money){
        accountEntityList = accountService.listAccount(name, money);
        // 将结果集转化为json字符串
        String json = JSON.toJSONString(accountEntityList);
        if (accountEntityList.size() == 0) {
            return "failed";
        } else {
            return "success" + json;
        }
    }

    /***
     * 保存账户信息
     * @param name
     * @param money
     * @return
     */
    @PostMapping("/saveaccount")
    @ResponseBody
    public String saveAccount(String name, Double money){
        accountEntity = new AccountEntity();
        accountEntity.setName(name);
        accountEntity.setMoney(money);
        int count = accountService.saveAccount(accountEntity);
        if (count !=0) {
            return "success";
        }
        return "failed";
    }

    /***
     * 根据id删除账户
     * @param id
     * @return
     */
    @GetMapping("/deleteaccount/{id}")
    public String deleteAccount(@PathVariable("id") Integer id){
        int count = accountService.deleteAccount(id);
        if (count == 0) {
            return "failed";
        }
        return "success";
    }

    /***
     * 根据指定条件查询信息controller
     * @param name
     * @param money
     * @return
     */
    @GetMapping("/listaccountbyinfo")
    public String listAccountByInfo(String name, Double money){
        accountEntity = new AccountEntity();
        if (name != null) {
            accountEntity.setName(name);
        }
        if (money != null) {
            accountEntity.setMoney(money);
        }
        accountEntityList = accountService.listAccountByInfo(accountEntity);
        String json = JSON.toJSONString(accountEntityList);
        if (accountEntityList.size() == 0) {
            return "failed";
        } else {
            return "success" + json;
        }
    }
}
