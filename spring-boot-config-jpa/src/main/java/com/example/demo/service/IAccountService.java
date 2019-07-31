package com.example.demo.service;

import com.example.demo.entity.AccountEntity;

import java.util.List;

public interface IAccountService {
    AccountEntity add(AccountEntity accountEntity);
    AccountEntity update(AccountEntity accountEntity);
    void delete(int id);
    AccountEntity findById(int id);
    List<AccountEntity> findAllList();
}
