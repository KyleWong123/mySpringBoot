package com.example.demo.dao;

import com.example.demo.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAccountDao {
    List<AccountEntity> findAllList();
    AccountEntity findById(int id);
    int updateAccount(AccountEntity accountEntity);
    int saveAccount(AccountEntity accountEntity);
    int deleteAccount(int id);
}
