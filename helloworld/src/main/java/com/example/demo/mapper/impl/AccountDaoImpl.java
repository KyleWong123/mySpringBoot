package com.example.demo.dao.impl;

import com.example.demo.dao.IAccountDao;
import com.example.demo.entity.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements IAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(AccountEntity accountEntity) {
        return jdbcTemplate.update("insert into account (name,money) values (?,?)",accountEntity.getName(),accountEntity.getName());
    }

    @Override
    public int update(AccountEntity accountEntity) {
        return jdbcTemplate.update("update account set name = ? where id = ?",accountEntity.getName(),accountEntity.getId());
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("delete from account where id = ?",id);
    }

    @Override
    public AccountEntity findById(int id) {
        List<AccountEntity> accountEntities = jdbcTemplate.query("select * from account where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(AccountEntity.class));
        if(accountEntities!=null && accountEntities.size()>0) {
            AccountEntity accountEntity = accountEntities.get(0);
            return accountEntity;
        }else {
            return null;
        }
    }

    @Override
    public List<AccountEntity> findAllList() {
        List<AccountEntity> accountEntityList = jdbcTemplate.query("select * from account", new Object[]{}, new BeanPropertyRowMapper<>(AccountEntity.class));
        if(accountEntityList!=null && accountEntityList.size()>0) {
            return accountEntityList;
        }else {
            return null;
        }
    }
}
