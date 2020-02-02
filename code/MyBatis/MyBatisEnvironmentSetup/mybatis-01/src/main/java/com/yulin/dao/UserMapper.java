package com.yulin.dao;

import com.yulin.pojo.User;

import java.util.List;
import java.util.Map;

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

    /**
     * use map:
     */
    //insert
    void insertUserMap(Map<String,Object> map);

    //update
    void updateUserMap(Map<String,Object> map);

    /**
     * 模糊查询
     */
    //select3
    List<User> getUserListLike(String value);

}