package com.springboot.test.mapper;

import com.springboot.test.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Kyle Wong
 * @Date: 2019/9/26 10:05
 * @Version 1.0
 */
@Repository
@Mapper
public interface AccountMapper {
    /**
     * 添加用户信息
     * @param account
     * @return
     */
    int saveAccount(Account account);
}
