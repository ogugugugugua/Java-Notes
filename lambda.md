整章围绕集合筛选来做

<center>

# Lambda
</center>

<center>Yulin XIE
</center>

#### 1. Find out heros that satisfy requirements in a common way
First of all we have basic Hero class:
```java
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

```
Then we have a common function to filter specific Heros:
```java
private static void filter(List<Hero> heros){
        for(Hero h:heros){
            if(h.damage<50 && h.hp>100){
                System.out.println(h);
            }
        }
    }
...
public class Main {

    private static void filter(List<Hero> heros){
        for(Hero h:heros){
            if(h.damage<50 && h.hp>100){
                System.out.println(h);
            }
        }
    }
    public static void main(String[] args) {
        List<Hero> heros = new ArrayList<>();
        Random r = new Random();
        for(int i = 0;i<100;i++){
            heros.add(new Hero("h"+i,r.nextInt(1000),r.nextInt(100)));
        }
        filter(heros);
    }
}
```

#### 2.Anonyme function:
> 首先准备一个接口HeroChecker，提供一个test(Hero)方法
然后通过匿名类的方式，实现这个接口

So first we construct an interface:
```java
public interface HeroChecker {
    public boolean herochecker(Hero h);
}
```
And then we need to reconstruct a function of filter:
```java
private static void filter_anonyme(List<Hero>heros, HeroChecker heroChecker){
        for(Hero h : heros){
            if(heroChecker.herochecker(h)){
                System.out.println(h);
            }
        }
    }
```
In the main function we use the anonyme function like this:
```java
        HeroChecker heroChecker = new HeroChecker() {
            @Override
            public boolean herochecker(Hero h) {
                return h.damage < 50 && h.hp > 100;
            }
        };
        filter_anonyme(heros,heroChecker);
```

#### 3. lambda way
Instead of (1)constructing an interface & (2)implementing the details of the instance of the interface in the main function, (like above), we distill the essence of the HeroChecker(instance of the interface) and represent it as a single line, and as a parameter:
```java
        HeroChecker heroChecker = new HeroChecker() {
            @Override
            public boolean herochecker(Hero h) {
                return h.damage < 50 && h.hp > 100;
            }
        };
//        filter_anonyme(heros,heroChecker); //old way as aboved
        filter_anonyme(heros , h->h.damage<50 && h.hp>100);//lambda way!!!
```

Then we will use another exemple to describe how an anonyme class becomes a lambda line:
```java
//匿名类
        Comparator<Hero> c = new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                return h1.hp>=h2.hp?1:-1;
            }
        };

//equals
       c = (Hero h1, Hero h2) -> {
            return h1.hp>=h2.hp?1:-1;
        };

//equals
       c = (Hero h1, Hero h2) -> h1.hp>h2.hp?1:-1;

//equals
       c = (h1,h2) -> h1.hp>h2.hp?1:-1;
```
Voila!
