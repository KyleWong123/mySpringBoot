package com.example.demo.service;

import com.example.demo.entity.AccountEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * @author
 */
public interface AccountService {

    /***
     * Service接口根据id查询账户信息
     * @param id
     * @return
     */
    AccountEntity getAccount(Integer id);

    /***
     * service接口
     * @param name
     * @param money
     * @return
     */
    List<AccountEntity> listAccount(String name, Double money);

    /***
     * Service接口保存账户信息
     * @param accountEntity
     * @return
     */
    int saveAccount(AccountEntity accountEntity);

    /***
     * Service接口删除账户信息
     * @param id
     * @return
     */
    int deleteAccount(Integer id);
    /***
     * Service接口根据条件查询信息
     * @param accountEntity
     * @return
     */
    List<AccountEntity> listAccountByInfo(AccountEntity accountEntity);

}
