package com.example.demo.service;

import com.example.demo.entity.AccountEntity;

import java.util.List;

public interface IAccountService {
    List<AccountEntity> findAllList();

    AccountEntity findById(int id);

    int updateAccount(AccountEntity accountEntity);

    int saveAccount(AccountEntity accountEntity);

    int deleteAccount(int id);
}
