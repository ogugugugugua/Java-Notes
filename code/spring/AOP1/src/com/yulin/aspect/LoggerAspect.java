package com.yulin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggerAspect {
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Start log: " + joinPoint.getSignature().getName());
        Object object = joinPoint.proceed();//就是将来与某个核心功能编织之后，用于执行核心功能的代码
        System.out.println("End log: " + joinPoint.getSignature().getName());
        return object;
    }
}
