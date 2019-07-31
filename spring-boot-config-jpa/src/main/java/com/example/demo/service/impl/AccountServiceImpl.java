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
    public AccountEntity add(AccountEntity accountEntity) {
        return iAccountDao.save(accountEntity);
    }

    @Override
    public AccountEntity update(AccountEntity accountEntity) {
        return iAccountDao.save(accountEntity);
    }

    @Override
    public void delete(int id) {
        iAccountDao.deleteById(id);
    }

    @Override
    public AccountEntity findById(int id) {
        return iAccountDao.findById(id).get();
    }

    @Override
    public List<AccountEntity> findAllList() {
        return iAccountDao.findAll();
    }
}
