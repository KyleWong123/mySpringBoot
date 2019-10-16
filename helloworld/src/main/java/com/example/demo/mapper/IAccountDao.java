package com.example.demo.dao;

import com.example.demo.entity.AccountEntity;

import java.util.List;

public interface IAccountDao {
    int add(AccountEntity accountEntity);

    int update(AccountEntity accountEntity);

    int delete(int id);

    AccountEntity findById(int id);

    List<AccountEntity> findAllList();
}
