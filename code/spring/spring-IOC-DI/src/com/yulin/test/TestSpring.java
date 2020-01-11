package com.yulin.test;
 
import com.yulin.pojo.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
import com.yulin.pojo.Category;
 
public class TestSpring {
 
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "applicationContext.xml" });
 
        Category c = (Category) context.getBean("c");

        System.out.println(c.getName());
        System.out.println(c.getId());


        Product p = (Product) context.getBean("p");
        System.out.println(p.getId());
        System.out.println(p.getName());
    }
}