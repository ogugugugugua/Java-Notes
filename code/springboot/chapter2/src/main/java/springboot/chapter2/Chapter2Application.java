package springboot.chapter2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import springboot.chapter2.aspect.ErrorAspect;
import springboot.chapter2.aspect.MyAspect;
import springboot.chapter2.dao.MyBatisUserDao;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "springboot.chapter2.dao")
//@EntityScan(basePackages = "springboot.chapter2.pojo")

public class Chapter2Application {

//    @Bean
//    public ErrorAspect getAspect2(){
//        return new ErrorAspect();
//    }
//    @Bean
//    public MyAspect getAspect1(){
//        return new MyAspect();
//    }


    public static void main(String[] args) {
        SpringApplication.run(Chapter2Application.class, args);
    }

}

