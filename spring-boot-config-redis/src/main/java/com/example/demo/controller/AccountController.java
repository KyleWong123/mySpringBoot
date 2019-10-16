package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
// import com.example.annotation.interfaceclass.ParamValidate;
import com.example.annotation.interfaceclass.ParamValidate;
import com.example.demo.entity.AccountEntity;
import com.example.demo.service.AccountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    /***
     * 根据id查询信息controller
     * 
     * @param id
     * @return
     */
    @GetMapping("/getaccountbyid/{id}")
   // @ParamValidate
    public String getAccountById(@PathVariable("id") Integer id) {
        //accountEntity = new AccountEntity();
        AccountEntity accountEntity;
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
    public String listAccount(String name, Double money) {
        List<AccountEntity> accountEntityList;
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
    public String saveAccount(String name, Double money) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(name);
        accountEntity.setMoney(money);
        accountEntity = accountService.saveAccount(accountEntity);
        if (accountEntity != null) {
            return "success";
        }
        return "failed";
    }

    /***
     * 根据id删除账户@PathVariable("id")
     * @param ids
     * @return
     */
    @GetMapping("/deleteaccount/{ids}")
    public String deleteAccount(@PathVariable Integer ids) {
        int count = accountService.deleteAccount(ids);
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
    public String listAccountByInfo(String name, Double money) {
        List<AccountEntity> accountEntityList = getAccountEntities(name, money);
        String json = JSON.toJSONString(accountEntityList);
        if (accountEntityList.size() == 0) {
            return "failed";
        } else {
            return "success" + json;
        }
    }

    private List<AccountEntity> getAccountEntities(String name, Double money) {
        AccountEntity accountEntity = new AccountEntity();
        if (name != null) {
            accountEntity.setName(name);
        }
        if (money != null) {
            accountEntity.setMoney(money);
        }
        List<AccountEntity> accountEntityList;
        accountEntityList = accountService.listAccountByInfo(accountEntity);
        return accountEntityList;
    }

    /***
     * Mybatis分页实现
     * @param name
     * @param money
     * @return
     */
    @GetMapping("/page")
    public PageInfo<AccountEntity> listAccountByInfo(PageInfo pageInfo, String name, Double money) {
        Integer pageNum = pageInfo.getPageNum();
        Integer pageSize = pageInfo.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<AccountEntity> accountEntityList = getAccountEntities(name, money);
        PageInfo<AccountEntity> pageList = new PageInfo<>(accountEntityList);
        if (accountEntityList.size() == 0) {
            return null;
        } else {
            return pageList;
        }
    }

    @GetMapping("accountToRedis")
    public void cacheTest() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName("Ky");
        accountEntity.setMoney(200);
        accountService.saveToRedis(accountEntity);
        //log.info("123{}", accountEntity.getName());
    }

    @GetMapping("accountFromRedis")
    public AccountEntity accountFromRedis(Integer id) {
        return accountService.getFromRedis(id);
    }

}
