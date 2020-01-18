package com.yulin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect/*注解表示这是一个切面*/
@Component/*表示这是一个bean,由Spring进行管理*/
public class LoggerAspect {
    @Around(value = "execution(* com.yulin.POJO.Category.*(..))") /*表示对com.yulin.service.SomeService中的所有方法进行切面操作*/
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Start log");
        Object object = joinPoint.proceed();
        System.out.println("End log");
        return object;
    }
    @Around(value = "execution(* com.yulin.POJO.Category.*(..))")
    public Object log1(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Start log1");
        Object object = joinPoint.proceed();
        System.out.println("End log1");
        return object;
    }
}
