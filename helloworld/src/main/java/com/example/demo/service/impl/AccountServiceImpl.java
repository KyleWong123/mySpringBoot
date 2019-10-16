package com.example.demo.service.impl;

import com.example.demo.dao.IAccountDao;
import com.example.demo.entity.AccountEntity;
import com.example.demo.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao iAccountDao;

    @Override
    public int add(AccountEntity accountEntity) {
        return iAccountDao.add(accountEntity);
    }

    @Override
    public int update(AccountEntity accountEntity) {
        return iAccountDao.update(accountEntity);
    }

    @Override
    public int delete(int id) {
        return iAccountDao.delete(id);
    }

    @Override
    public AccountEntity findById(int id) {
        return iAccountDao.findById(id);
    }

    @Override
    public List<AccountEntity> findAllList() {
        return iAccountDao.findAllList();
    }
}
