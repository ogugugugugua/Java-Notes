package springboot.chapter2.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import springboot.chapter2.dao.MyBatisUserDao;
import springboot.chapter2.service.MybatisUserService;
import springboot.chapter2.service.UserService;

@Configuration
@ComponentScan(basePackages = "springboot.chapter2.*") //默认扫描当前包及子包的Component
public class AppConfig {

}
