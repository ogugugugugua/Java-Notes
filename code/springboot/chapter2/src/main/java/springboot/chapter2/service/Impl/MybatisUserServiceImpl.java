package springboot.chapter2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.chapter2.dao.MyBatisUserDao;
import springboot.chapter2.pojo.UserForMybatis;
import springboot.chapter2.service.MybatisUserService;

import java.util.List;

public class MybatisUserServiceImpl implements MybatisUserService {
    @Autowired
    MyBatisUserDao myBatisUserDao;

    @Override
    public List<UserForMybatis> findall() {
        return myBatisUserDao.findall();
    }
}
