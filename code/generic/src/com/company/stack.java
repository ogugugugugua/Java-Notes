package com.company;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.company.basic;
public class stack {
    public static void main(String args[]) {
        MyStack<Hero>heroStack = new MyStack<>();
        for(int i =0;i<7;i++){
            heroStack.push(new Hero("h"+i));
        }
        for(int i=0;i<7;i++){
            Hero temp = heroStack.pull();
            System.out.println(temp);
        }

        //-------------------------------------------------------------------
        //二叉树
        int randoms[] = new int[] { 67, 7, 30, 73, 10, 0, 78, 81, 10, 74 };
        Node<Integer> roots = new Node<>();
        for (int number : randoms) {
            roots.add(number);
        }
        System.out.println(roots.getAllValue());
    }
}
class Hero{
    public String name;
    public float hp;
    public int damage;
    public Hero() {}
    // 增加一个初始化name的构造方法
    public Hero(String name) {
        this.name = name;
    }
    // 重写toString方法
    public String toString() {
        return name;
    }
}
class Item{
    String name;
    int price;
    public Item(){}
    //提供一个初始化name的构造方法
    public Item(String name){
        this.name = name;
    }
    public void effect(){
        System.out.println("物品使用后，可以有效果");
    }
    public String toString(){return this.name;}
}
class MyStack<T>{
    LinkedList<T> heros = new LinkedList<T>();
    public T pull(){
        return heros.removeLast();
    }
    public T peek(){
        return heros.getLast();
    }
    public void push(T h){
        heros.addLast(h);
    }
}

class Node<T>{
    public Node<T> lefeNode;
    public Node<T> rightNode;
    public T value;

    public void add(T t){
        if(value == null){
            value = t;
        }
        else{
            if((Integer)value-(Integer)t>0){
                if(lefeNode == null){
                    lefeNode = new Node<T>();
                }
                lefeNode.add(t);
            }
            else{
                if(rightNode == null){
                    rightNode = new Node<T>();
                }
                rightNode.add(t);
            }
        }
    }
    public List<T> getAllValue(){
        //中序遍历
        List<T> values = new ArrayList<>();
        if(lefeNode!=null){
            values.addAll(lefeNode.getAllValue());
        }
        values.add(value);
        if(rightNode!=null){
            values.addAll(rightNode.getAllValue());
        }
        return values;
    }

}