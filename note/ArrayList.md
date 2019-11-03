# <center> List </center>
<center>Yulin XIE </center>

#### 1.Basic manipulations about a List
- add objects at specific positions
```java
         ArrayList heros = new ArrayList();
         heros.add(new Hero("a"));
         heros.add(new Hero("b"));
         for (int i = 0; i < 5; i++) {
             heros.add(new Hero("hero " + i));
         }
         System.out.println(heros);

         // 在指定位置增加对象
         Hero specialHero = new Hero("special hero");
         heros.add(3, specialHero);
         System.out.println(heros);
```
- check if contains certain objects __(only when the object is identical)__
```java
//contains
         System.out.println("contain a: "+heros.contains(new Hero("a")));
         System.out.println("contain special: "+heros.contains(specialHero));
```
- get objects of specific positions
```java
         try{//get
             System.out.println(heros.get(1));
             System.out.println(heros.get(heros.size()+1));
         }catch (Exception e){
             e.printStackTrace();
         }
```
- get positions of specific objects __(need to specify the object wanted, not just the same name)__
```java
        try{//indexof
             System.out.println("position: "+heros.indexOf(specialHero));
             System.out.println("position: "+heros.indexOf(new Hero("a")));
         }catch (Exception e){
             e.printStackTrace();
         }
```
- remove specific objects (or objects of certain positions)
```java
         try{ //remove
             System.out.println(heros);
             heros.remove(2);
             System.out.println(heros);
             heros.remove(specialHero);
             System.out.println(heros);
         }catch (Exception e){
             e.printStackTrace();
         }
```
- replace objects of specific positions
```java
         try{
             System.out.println(heros);
             heros.set(3,specialHero);
             System.out.println(heros);
         }catch (Exception e){
             e.printStackTrace();
         }
```
- cast an `ArrayList` to an array
```java
         try{//wrong casting
//             Hero[] array = (Hero[]) heros.toArray();
//             Hero[] array = (Hero[]) heros.toArray(new Hero[]{});// wrong as well
             System.out.println(array);
         }catch (Exception e){
             e.printStackTrace();
         }

         try{//right casting
             Hero array[] = (Hero[])heros.toArray(new Hero[]{});
             System.out.println("array: "+array);
         }catch (Exception e){
             e.printStackTrace();
         }
```
- add another `List` into present `List`
```java
       try{//addAll
            ArrayList anotherHeros = new ArrayList();
            for (int i = 0; i < 5; i++) {
                anotherHeros.add(new Hero("hero " + i*10));
            }
            System.out.println(anotherHeros);
            anotherHeros.addAll(4,heros);
            System.out.println(anotherHeros);
        }catch (Exception e){
            e.printStackTrace();
        }
```
- clear the List
```java
        try{//clear
            System.out.println("================");
            heros.clear();
            System.out.println(heros);
        }catch (Exception e){
            e.printStackTrace();
        }
```




#### Generic
If we do this:
```java
List heros = new ArrayList();
heros.add(Hero("a"));
heros.add(Hero("b"));
heros.add(Hero("c"));
heros.add(Item("1"));
```
then we may encounter some problems of casting if we forget the corresponding position:
```java
Hero h1 = (Hero) heros.get(1);
Hero h2 = (Hero) heros.get(2);
//wrong if Item is not a sub-class of Hero ↓
//Hero h3 = (Hero) heros.get(3);
```
so we introduce generic:
```java
List<Hero> genericheros = new ArrayList<Hero>();
genericheros.add(new Hero("盖伦"));
//如果不是Hero类型，根本就放不进去
//genericheros.add(new Item("冰杖"));
//除此之外，还能存放Hero的子类
genericheros.add(new APHero());
//并且在取出数据的时候，不需要再进行转型了，因为里面肯定是放的Hero或者其子类
Hero h = genericheros.get(0);
```
Btw, we can neglect the second parameter:
```java
List<Hero> genericheros1 = new ArrayList<>();
```
Therefore if we want to construct a List that can __only support certain data type__, we can firstly construct an interface, and let whatever we want to put in the List to `implements` this interface. Then we construct a List with the type equals the interface:
```java
package com.company;
import java.util.ArrayList;
import java.util.List;

interface LOL{}

class GHero implements LOL{
    public String name;
    public float hp;
    public int damage;
    public GHero() {}
    // 增加一个初始化name的构造方法
    public GHero(String name) {this.name = name;}
    // 重写toString方法
    public String toString() {return name;}
}

class GItem implements LOL{
    String name;
    int price;
    public GItem(){}
    //提供一个初始化name的构造方法
    public GItem(String name){this.name = name;}
    public void effect(){System.out.println("物品使用后，可以有效果");}
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
        try{
            // DOES NOT WORK!
            //genericList.add(test);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
```
#### Walk through a list
- avanced for loop:
``` java
        //增强型for循环
        for(LOL l:genericList){
            System.out.println(l);
        }
```
- iterator:
```java
//迭代器简洁版本
        for(Iterator<LOL> iter = genericList.iterator();iter.hasNext();){
            System.out.println(iter.next());
        }
```
```java
//使用while的iterator
        Iterator<Hero> it= heros.iterator();
        //从最开始的位置判断"下一个"位置是否有数据
        //如果有就通过next取出来，并且把指针向下移动
        //直到"下一个"位置没有数据
        while(it.hasNext()){
            Hero h = it.next();
            System.out.println(h);
        }
```
<center>


![806](https://user-images.githubusercontent.com/17522733/68083728-8c74ed00-fe2c-11e9-861f-61aaa9da5187.png)
</center>
