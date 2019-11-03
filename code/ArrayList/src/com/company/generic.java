package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface LOL{

}
class GHero implements LOL{
    public String name;
    public float hp;
    public int damage;
    public GHero() {}
    // 增加一个初始化name的构造方法
    public GHero(String name) {
        this.name = name;
    }
    // 重写toString方法
    public String toString() {
        return name;
    }
}
class GItem implements LOL{
    String name;
    int price;
    public GItem(){}
    //提供一个初始化name的构造方法
    public GItem(String name){
        this.name = name;
    }
    public void effect(){
        System.out.println("物品使用后，可以有效果");
    }
    public String toString(){return this.name;}
}
public class generic {
    public static void main(String args[]){
        List<LOL> genericList = new ArrayList<>();
        genericList.add(new GHero("a"));
        genericList.add(new GHero("b"));
        genericList.add(new GHero("c"));
        genericList.add(new GItem("1"));
        genericList.add(new GItem("2"));
        String test = "hello";
//        try{
//            genericList.add(test);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //增强型for循环
        for(LOL l:genericList){
            System.out.println(l);
        }

        System.out.println("-------------------------");

        //迭代器
        for(Iterator<LOL> iter = genericList.iterator();iter.hasNext();){
            System.out.println(iter.next());
        }


        List<Hero> testHero = new ArrayList<Hero>();
        // 放5个Hero进入容器
        for (int i = 0; i < 99; i++) {
            testHero.add(new Hero("hero name " + i));
        }
        for(Iterator<Hero> iterator = testHero.iterator();iterator.hasNext();){
            Hero temp = iterator.next();
//            wrong
//            char index = temp.name.charAt(temp.name.length() - 1);
            int index = Integer.parseInt(temp.name.substring(10));
            if(index%8==0){
//                testHero.remove(temp);//wrong
                // Exception in thread "main" java.util.ConcurrentModificationException
                //	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1042)
                //	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:996)
                //	at com.company.generic.main(generic.java:71)
//                https://www.cnblogs.com/dolphin0520/p/3933551.html
                iterator.remove();

            }
        }
        for(Iterator<Hero> iterator = testHero.iterator();iterator.hasNext();){
            System.out.println(iterator.next());
        }



        //method2
        testHero.clear();
        for (int i = 0; i < 99; i++) {
            testHero.add(new Hero("hero name " + i));
        }
        System.out.println(testHero);
        List<Hero> deleteHero = new ArrayList<>();
        for(Iterator<Hero> iterator = testHero.iterator();iterator.hasNext();){
            Hero temp = iterator.next();
//            char index = temp.name.charAt(temp.name.length() - 1);
            int index = Integer.parseInt(temp.name.substring(10));
            if(index%8==0&&index>0){
                deleteHero.add(temp);
            }
        }
        testHero.removeAll(deleteHero);
        System.out.println(testHero);
    }
}
