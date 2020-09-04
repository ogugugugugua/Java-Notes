package yulin.log;

import org.aspectj.lang.ProceedingJoinPoint;

public class Log2 {
    public void before(){
        System.out.println("before.");
    }
    public void after(){
        System.out.println("after");
    }

    public void strange(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("strange method 1 has been invoked");
        proceedingJoinPoint.proceed();
        System.out.println("strange method 2 has been invoked");
    }

}
