package com.yulin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect("TimeAspect")
public class TimeAspect {
    @Around("execution(* com.yulin.service.SomeService.*(..))")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Start timer");
        long start = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        Thread.sleep(1500);
        long end = System.currentTimeMillis();
        System.out.println("Timer: " + (end - start));
        System.out.println("End timer");
        return object;
    }
}
