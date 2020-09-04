package com.yulin.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Date;

public class PerformanceAspect {
    public Object calRunTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Date begin = new Date();
        System.out.println("Start time: " + begin.toString());
        Object object = joinPoint.proceed();
        Date end = new Date();
        System.out.println("End time: " + end.toString());
        System.out.println(joinPoint.getSignature().getName() + " has run for " + end.compareTo(begin));
        return object;
    }
}
