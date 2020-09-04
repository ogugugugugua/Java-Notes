import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import yulin.service.UserService;
import yulin.service.UserServiceImpl;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService service = (UserService) applicationContext.getBean("service");

        service.select();
    }
}
