package com.yulin.service.impl;

import com.yulin.domain.Account;
import com.yulin.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> findAll() {
        System.out.println("业务层：查询所有账户");
//        System.out.println("Spring环境搭建成功");
        return null;
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存账户");
    }
}
