package com.yulin.POJO;

import org.springframework.stereotype.Component;

@Component("c")
public class Category {
    private String name="Cat1";
    private int id=1;

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}
