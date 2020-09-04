package yulin.AOP;

import yulin.pojo.UserService;
import yulin.pojo.UserServiceImpl;

import java.lang.reflect.Proxy;

public class testAOP {
    public static void main(String[] args) {
        //real character
        UserService userService = new UserServiceImpl();

        //proxy
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.setTarget(userService);
        UserService proxy = (UserService) myInvocationHandler.getProxy();

        proxy.add();
    }
}
