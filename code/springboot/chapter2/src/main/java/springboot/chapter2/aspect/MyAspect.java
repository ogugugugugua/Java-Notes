package springboot.chapter2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import springboot.chapter2.aspect.Impl.UserValidatorImpl;

@Aspect
public class MyAspect {

    @DeclareParents(value = "springboot.chapter2.service.Impl.UserServiceImpl+", defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;

    @Pointcut(value = "execution(* springboot.chapter2.service.Impl.UserServiceImpl.*(..))")
    public void pointCut() {
    }

    @Before(value = "pointCut()")
    public void before() {
        System.out.println("before....");
    }

    @After(value = "pointCut()")
    public void after() {
        System.out.println("after.....");
    }

    @AfterReturning(value = "pointCut()")
    public void afterRetuning() {
        System.out.println("afterRetuning.....");
    }

    @Around(value = "pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before jointPoint...");
        joinPoint.proceed();
        System.out.println("after jointPoint...");
    }
}
