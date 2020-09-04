package springboot.chapter2.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ErrorAspect {
    @Pointcut(value = "execution(* springboot.chapter2.service.Impl.UserServiceImpl.*(..))")
    public void pointCut() {
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("error!!");
    }
}
