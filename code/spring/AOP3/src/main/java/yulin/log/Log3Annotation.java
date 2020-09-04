package yulin.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect

public class Log3Annotation {
    @Before("execution(* yulin.service.UserServiceImpl.* (..))")
    public void before(){
        System.out.println("before");
    }

    @After("execution(* yulin.service.UserServiceImpl.* (..))")
    public void after(){
        System.out.println("after");
    }

    /**
     * 注意这里的ProceedingJoinPoint参数
     * @param joinPoint 使用它来帮助实现around的功能
     * @throws Throwable
     */
    @Around("execution(* yulin.service.UserServiceImpl.* (..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before around");
        joinPoint.proceed();
        System.out.println("after around");
    }
}
