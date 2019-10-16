package com.example.demo.service;

import com.example.demo.entity.AccountEntity;

import java.util.List;

public interface IAccountService {
    int add(AccountEntity accountEntity);

    int update(AccountEntity accountEntity);

    int delete(int id);

    AccountEntity findById(int id);

    List<AccountEntity> findAllList();
}
