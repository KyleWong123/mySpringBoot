package com.example.demo.mapper;

/***
 * @author
 */

import com.example.demo.entity.AccountEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountMapper {
    /***
     * 根据id查询账户信息
     * @param id  账户id
     * @return 账户信息
     */
    AccountEntity getAccount(Integer id);

    /***
     * 根据条件查询信息
     * @param name
     * @param money
     * @return
     */
    List<AccountEntity> listAccount(@Param("name") String name, @Param("money") Double money);

    /**
     * 保存账户
     * @param accountEntity 要保存的账户信息
     * @return
     */
    int saveAccount(AccountEntity accountEntity);

    /***
     * 修改账户信息
     * @param accountEntity
     * @return
     */
    int updateAccount(AccountEntity accountEntity);

    /***
     * 根据id删除账户
     * @param id
     * @return
     */
    int deleteAccount(Integer id);

    /***
     * 根据条件查询信息
     * @param accountEntity
     * @return
     */
    List<AccountEntity> listAccountByInfo(@Param("accountEntity") AccountEntity accountEntity);

}
