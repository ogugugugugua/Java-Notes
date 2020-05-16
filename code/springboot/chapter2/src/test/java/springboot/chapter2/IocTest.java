package springboot.chapter2.config;


//import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.chapter2.pojo.User;

public class IocTest {
//    private static Logger logger = Logger.getLogger(IocTest.class);
public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    User user = context.getBean(User.class);
    System.out.println("id:"+user.getId());
    System.out.println(user.getUserName());
}

}

