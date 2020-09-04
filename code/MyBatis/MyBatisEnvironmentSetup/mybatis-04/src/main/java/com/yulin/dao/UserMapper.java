package com.yulin.dao;

import com.yulin.pojo.User;
import org.apache.ibatis.annotations.Param;

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
    User getUserById(@Param("ByID") int id);

    //update
    void updateUser(User user);

    //insert
    void insertUser(User user);

    //delete
    void deleteUser(int id);


    /**
     * 分页查询
     */
    List<User> getUserByLimit(Map<String,Integer> map);
}
