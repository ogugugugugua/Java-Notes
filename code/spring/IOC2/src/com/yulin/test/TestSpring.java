package com.yulin.test;
import com.yulin.POJO.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        Product product = (Product) context.getBean("p");
        System.out.println(product.getId());
        System.out.println(product.getCategory().getName());
        System.out.println(product.getCategory().getId());
    }

}
