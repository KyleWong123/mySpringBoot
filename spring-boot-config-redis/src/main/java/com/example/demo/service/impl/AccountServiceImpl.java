package com.example.demo.service.impl;

/***
 * @author
 */

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.AccountEntity;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper = null;
    private List<AccountEntity> accountEntityList = null;
    private AccountEntity accountEntity = null;
    @Autowired
    private RedisTemplate redisTemplate = null;
    @Override
    // @Cacheable(value = "redisCache", key = "'redis_account_' + #id")
    public AccountEntity getAccount(Integer id) {
        accountEntity = new AccountEntity();
        log.info("传入的id为{}",id);
        accountEntity = accountMapper.getAccount(id);
        String json = JSON.toJSONString(accountEntity);
        log.info("查找出的账户信息为{}",json);
        // 将获取的数据或存到redis数据库
        log.info("实现数据缓存到redis数据库");
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redis_account"+id, json);
        if (accountEntity == null) {
            return null;
        }
        return accountEntity;
    }

    @Override
    public List<AccountEntity> listAccount(String name, Double money) {
        log.info("根据条件查询账户的信息");
        accountEntityList = accountMapper.listAccount(name, money);
        log.info("查出的账户信息有{}",accountEntityList.toArray());
        if (accountEntityList == null) {
            return null;
        }
        return accountEntityList;
    }

    @Override
    @ResponseBody
    // @CachePut(value = "redisCache", key = "'redis_account_'")
    public int saveAccount(AccountEntity accountEntity) {
        int count = accountMapper.saveAccount(accountEntity);
        String json = JSON.toJSONString(accountEntity);
        log.info("{}", json);
        log.info("保存的用户信息为{}", accountEntity);
        log.info("实现数据缓存到redis数据库");
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redis_account"+accountEntity.getId(), json);
        if (count != 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteAccount(Integer id) {
        log.info("要删除的账户id为{}", id);
        int count = accountMapper.deleteAccount(id);
        if (count == 0) {
            return 0;
        }
        log.info("从redis数据库中清除缓存");
        Boolean aBoolean = redisTemplate.delete("redis_account"+id);
        return 1;
    }

    @Override
    public List<AccountEntity> listAccountByInfo(AccountEntity accountEntity) {
        log.info("根据指定条件查询账户的信息");
        accountEntityList = accountMapper.listAccountByInfo(accountEntity);
        log.info("查出的账户信息有{}",accountEntityList.toArray());
        if (accountEntityList == null) {
            return null;
        }
        return accountEntityList;
    }
}
