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
    public List<AccountEntity> findAllList() {
        return iAccountDao.findAllList();
    }

    @Override
    public AccountEntity findById(int id) {
        AccountEntity accountEntity = iAccountDao.findById(id);
        if (accountEntity!=null) {
            return accountEntity;
        }
        return null;
    }

    @Override
    public int updateAccount(AccountEntity accountEntity) {
        return iAccountDao.updateAccount(accountEntity);
    }

    @Override
    public int saveAccount(AccountEntity accountEntity) {
        return iAccountDao.saveAccount(accountEntity);
    }

    @Override
    public int deleteAccount(int id) {
        return iAccountDao.deleteAccount(id);
    }

}
