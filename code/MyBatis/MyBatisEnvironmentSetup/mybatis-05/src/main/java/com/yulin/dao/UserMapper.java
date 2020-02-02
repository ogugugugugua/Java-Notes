package com.yulin.dao;

import com.yulin.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 操作数据库对象的一些方法
 * 这里的dao等价于mybatis里面的mapper
 */
public interface UserMapper {
    @Select("select * from user;")
    List<User> getUsers();
}
