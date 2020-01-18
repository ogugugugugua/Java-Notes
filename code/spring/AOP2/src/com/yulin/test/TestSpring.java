package com.yulin.test;

import com.yulin.POJO.Product;
import com.yulin.service.SomeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        SomeService service = (SomeService) context.getBean("s");
        service.doSomeService();

        System.out.println("----------------------------");

        Product product = (Product) context.getBean("p");
        System.out.println(product.getCategory());/*这一行不会调用aspect.LoggerAspect.log1方法*/

        System.out.println("----------------------------");

        System.out.println(product.getName());/*这一行会调用aspect.LoggerAspect.log1方法，因为这正是log1切面操作指定的关联方法*/
    }
}
