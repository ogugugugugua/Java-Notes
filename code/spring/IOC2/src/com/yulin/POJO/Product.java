package com.yulin.POJO;

public class Product {
    private String name;
    private int id;
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory(){
        return this.category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
