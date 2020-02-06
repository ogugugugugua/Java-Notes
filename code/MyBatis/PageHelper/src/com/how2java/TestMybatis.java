package com.how2java;
  
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.github.pagehelper.PageHelper;
import com.how2java.pojo.Category;
import org.junit.Test;

public class TestMybatis {
    public static void main(String[] args) throws IOException, InterruptedException
    {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();


        PageHelper.offsetPage(9, 5);

        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }

        PageInfo pageInfo = new PageInfo<>(cs);
        System.out.println("总数："+pageInfo.getTotal());
        System.out.println(pageInfo);

        session.commit();
        session.close();



    }

}