package com.yulin.dao;

import com.yulin.pojo.User;
import com.yulin.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void getUserList(){

//        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        // In order to do the test, first step: get SqlSession
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        // Second step: get the needed DAO
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserList();

        //Third step: we then see the result
        for(User user:userList){
            System.out.println(user);
        }

        //close sqlsession
        sqlSession.close();
    }

    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.getUserById(1);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updateUser(new User(3,"changeName","987898"));
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void insertUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.insertUser(new User(5,"five","ffwjfio"));
        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void deleteUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUser(5);
        sqlSession.commit();

        sqlSession.close();
    }
}
