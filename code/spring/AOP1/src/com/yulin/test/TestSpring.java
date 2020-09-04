package com.yulin.test;

import com.yulin.POJO.Category;
import com.yulin.POJO.Product;
import com.yulin.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        ProductService service = (ProductService) context.getBean("service");
        service.doSomeService();
        Product product = (Product) context.getBean("p");
        System.out.println("product: " + product.getName() + "\tid: " + product.getId());
        Category category = (Category) context.getBean("c");
        System.out.println("category: "+category.getName()+"\tid: "+category.getId());
    }
}
