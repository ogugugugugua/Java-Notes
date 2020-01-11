package com.yulin.pojo;

public class Product {
    private String name;
    private int id;
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
