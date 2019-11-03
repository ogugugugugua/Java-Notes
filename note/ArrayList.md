# <center> List </center>
<center>Yulin XIE </center>

#### 1.



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
<center>

![806](https://user-images.githubusercontent.com/17522733/68083728-8c74ed00-fe2c-11e9-861f-61aaa9da5187.png)
</center>
