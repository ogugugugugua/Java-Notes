package com.yulin.POJO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("p")
public class Product {
    private String name="Pro2";
    private int id=2;
    private Category category;

    public Category getCategory(){
        return this.category;
    }
    @Autowired
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        System.out.println("enter getName");
        return name;
    }
}
