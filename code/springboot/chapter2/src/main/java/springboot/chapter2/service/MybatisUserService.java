package springboot.chapter2.service;

import org.springframework.stereotype.Service;
import springboot.chapter2.pojo.UserForMybatis;

import java.util.List;

@Service
public interface MybatisUserService {
    List<UserForMybatis> findall();
}
