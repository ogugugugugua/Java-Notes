package com.yulin.domain;

import java.io.Serializable;

public class PersonInfo implements Serializable {
    private int age;
    private String sex;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}
