package yulin.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Beforelog implements MethodBeforeAdvice {
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + " class' " + method.getName() + " method has been invoked.");
    }
}
