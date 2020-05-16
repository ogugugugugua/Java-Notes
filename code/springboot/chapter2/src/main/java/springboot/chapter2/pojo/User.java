package springboot.chapter2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("user")
public class User {
    @Value("1")
    private Long id;
    @Value("userName1")
    private String userName;
    @Value("note1")
    private String Note;

}
