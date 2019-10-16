package com.example.demo.mapper;

import com.example.demo.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IAccountDao {
    List<AccountEntity> findAllList();

    AccountEntity findById(int id);

    int updateAccount(AccountEntity accountEntity);

    int saveAccount(AccountEntity accountEntity);

    int deleteAccount(int id);
}
