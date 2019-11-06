package com.company;
public class Hero {
    public String name;
    public float hp;
    public int damage;

    public Hero(){}

    public Hero(String name) {
        this.name =name;
    }

    //初始化name,hp,damage的构造方法
    public Hero(String name,float hp, int damage) {
        this.name =name;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "Hero [name=" + name + ", hp=" + hp + ", damage=" + damage + "]\r\n";
    }
}
