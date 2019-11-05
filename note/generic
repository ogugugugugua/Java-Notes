

#### 1. Benefit of generic
The benefit of using generic along with container is that we can specify what is stored in the
container to avoid confusion of data type.

```java
public class TestGeneric {
    public static void main(String[] args) {

        ArrayList<APHero> heros = new ArrayList<APHero>();

        //只有APHero可以放进去
        heros.add(new APHero());

        //ADHero甚至放不进去
        //heros.add(new ADHero());

        //获取的时候也不需要进行转型，因为取出来一定是APHero
        APHero apHero =  heros.get(0);
    }
}
```

You can also specify the &lt;Type&gt; and store its sub-class into the container.
>对象类型可以是泛型类型的子类，比如说在泛型集合声明时使用父类作为参数，那么在这个集合中可以放进去子类的实例。
```java
public class TestGeneric {
    public static void main(String[] args) {

        ArrayList<Hero> heros = new ArrayList<Hero>();

        //只有作为Hero的子类可以放进去
        heros.add(new APHero());
        heros.add(new ADHero());

        //和Hero无关的类型Item还是放不进去
        //heros.add(new Item());

    }
}
```
#### 2. Generic Stack
```java
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
```
Above is a generic stack which can store different types of objects. For exemples:
```java
        MyStack<Hero>heroStack = new MyStack<>();
        for(int i =0;i<7;i++){
            heroStack.push(new Hero("h"+i));
        }
        for(int i=0;i<7;i++){
            Hero temp = heroStack.pull();
            System.out.println(temp);
        }
```

#### 3. ?extends
> ArrayList heroList<? extends Hero> 表示这是一个Hero泛型或者其子类泛型

Now that it can store Hero and its sub-class, we can definitely take objects out from the container and cast it into Hero.
Therefore, we __CAN__ take objects out but __CAN NOT__ put objects in. Because if you put an object in, it can be other sub-class
of the &lt;?extends Type&gt; and thus cause confusion.
<center>

![837](https://user-images.githubusercontent.com/17522733/68239514-66597380-000b-11ea-8e8f-5049091dd7b4.png)
</center>

```java
public class TestGeneric {
    public static void main(String[] args) {

        ArrayList<APHero> apHeroList = new ArrayList<APHero>();
        apHeroList.add(new APHero());

        //? extends Hero 表示这是一个Hero泛型的子类泛型
        ArrayList<? extends Hero> heroList = apHeroList;

        //heroList 的泛型可以是Hero
        //heroList 的泛型可以使APHero
        //heroList 的泛型可以使ADHero

        //可以确凿的是，从heroList取出来的对象，一定是可以转型成Hero的
        Hero h= heroList.get(0);

        //但是，不能往里面放东西
        heroList.add(new ADHero()); //编译错误，因为heroList的泛型 有可能是APHero

    }

}
```


#### 4. ?super
>ArrayList heroList<? super Hero> 表示这是一个Hero泛型或者其父类泛型

We can put Hero and sub-class of Hero into the container but we __CAN NOT__ take objects out because we have no idea
what we may take out. For exemples, we cannot take out an `Object` and cast it into Hero.

可以 父类=子类，不可以 子类=父类。因此不能取出来，不然没地儿放。

```java
public class TestGeneric {
    public static void main(String[] args) {

        //? super Hero 表示 heroList的泛型是Hero或者其父类泛型
        ArrayList<? super Hero> heroList = new ArrayList<Object>();

        //heroList 的泛型可以是Hero
        //heroList 的泛型可以是Object

        //所以就可以插入Hero
        heroList.add(new Hero());
        //也可以插入Hero的子类
        heroList.add(new APHero());
        heroList.add(new ADHero());

        //但是，不能从里面取数据出来,因为其泛型可能是Object,而Object是强转Hero会失败
        Hero h= heroList.get(0);
    }
}
```

#### 5. Conclusion
>如果希望只取出，不插入，就使用? extends Hero

>如果希望只插入，不取出，就使用? super Hero


#### 5. Casting between generic
Conclusion: super class generic cannot cast to sub class generic nor vice versa.
