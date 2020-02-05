package com.yulin.dao;

import com.yulin.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository     //表示这个东西也交给IOC容器去管理
public interface AccountDao {
    /**
     * 查询所有账户
     * @return
     */
    @Select("select * from account")
    public List<Account> findAll();

    /**
     * 保存账户信息
     * @param account
     */
    @Insert("insert into account (name, money) values(#{name},#{money})")
    public void saveAccount(Account account);


}
