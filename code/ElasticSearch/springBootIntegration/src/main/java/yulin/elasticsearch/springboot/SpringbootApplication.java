package yulin.elasticsearch.springboot;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {



    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
