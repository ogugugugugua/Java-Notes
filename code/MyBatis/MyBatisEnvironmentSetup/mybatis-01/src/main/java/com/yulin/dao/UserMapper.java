package com.yulin.dao;

import com.yulin.pojo.User;

import java.util.List;

/**
 * 操作数据库对象的一些方法
 * 这里的dao等价于mybatis里面的mapper
 */
public interface UserMapper {
    //select1
    List<User> getUserList();

    //select2
    User getUserById(int id);

    //update
    void updateUser(User user);

    //insert
    void insertUser(User user);

    //delete
    void deleteUser(int id);

}
