package com.springboot.test.service.impl;

import com.springboot.test.mapper.AccountMapper;
import com.springboot.test.model.Account;
import com.springboot.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/26 10:23
 * @Version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public int saveAccount(Account account) {
        return accountMapper.saveAccount(account);
    }
}
