package springboot.chapter2.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import springboot.chapter2.pojo.UserForMybatis;

import java.util.List;

@Mapper
public interface MyBatisUserDao {
//    public UserForMybatis getUserById(Long id);

    @Select("SELECT * FROM springboot2x")
    List<UserForMybatis> findall();

}
