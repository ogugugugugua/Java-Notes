package com.yulin.test;

import com.yulin.dao.AccountDao;
import com.yulin.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis {
    @Test
    public void run() throws Exception {
        // 加载配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取到代理对象
        AccountDao mapper = sqlSession.getMapper(AccountDao.class);
        //保存（插入）新数据
        mapper.saveAccount(new Account(2,"a2",234.0));
        // 查询所有数据
        List<Account> accountList = mapper.findAll();
        //打印查询的数据
        for (Account account : accountList) {
            System.out.println(account);
        }

        //关闭流
        sqlSession.close();
        inputStream.close();
    }
}
