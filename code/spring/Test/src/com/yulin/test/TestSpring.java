package com.yulin.test;

import com.yulin.POJO.Category;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        Category category = (Category) context.getBean("c");
        System.out.println(category.getName());

    }
}
