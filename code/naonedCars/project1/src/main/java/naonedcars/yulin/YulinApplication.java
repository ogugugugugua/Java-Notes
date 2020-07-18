package naonedcars.yulin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "naonedcars.yulin.mapper")
@EntityScan(basePackages = "naonedcars.yulin.pojo")
public class YulinApplication {
    public static void main(String[] args) {
        SpringApplication.run(YulinApplication.class, args);
    }

}
