1. 新建一个类，该类继承Thread，重写该类的run()方法。将该类实例化，然后调用start()方法。该线程类只是代表JVM中那个真正的线程，一旦启动start()，JVM就会真正地启动一个线程，在这个线程当中就会去调用run()方法，从而执行相应业务。

2. 使用接口。新建一个类实现implemets了Runnable()，由于没有继承Thread类，因此没有start()方法。所以启动的时候需要手动操作，先new一个Thread，然后把实现Runnable那个类的实例作为参数扔进去：

3. 使用匿名类的形式。匿名类的一个好处是可以很方便地访问外部的局部变量。

注意：启动线程是start()方法，run()并不能启动一个新的线程。


如果在写数据的时候加了锁，但是对于读数据的函数没有加锁的话，就可能存在写数据函数线程进行一半时，读数据函数线程被调用，读出来不正确的数据，此所谓脏读。出来这个问题的原因是，不管该对象是否锁定，如果存在不加锁的线程，就一样可以同时运行。解决方法：在读数据的时候也要申请锁。


一个同步方法可以调用另一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候依然会得到该对象的锁，即synchronized获得的锁是可重入的。
```java
public class test{
  synchronized void m1(){
    //processing
    m2();
  }
  synchronized void m2(){
    //processing
  }
}
```
在上面的例子中，m1()在调用的时候锁定了本身，然后m1()里面调用m2()的时候再次申请锁定本身，会成功。

子类调用父类的同步方法也是可以的。


程序在执行过程中，如果出现异常，在默认情况下锁会被释放。
因此在并发处理的过程中，对于异常处理需要更加的小心，否则可能会发生不一致的情况。
e.g. 在一个web app处理过程中，多个servlet线程共同访问同一个资源，如果其中一个线程没有合理处理异常，抛出了异常，那么其他线程就会进入同步代码区，有可能访问到处理了一半的不正常数据。


volatile使一个变量在多个线程之间可见。
作用：当一个线程使得该变量的值发生变化时，会给其他的线程发出通知，告知该变量的值已经被修改，强制其他线程都去堆内存重新获取新的值放到自己线程的缓冲区里。
总结volatile保证了变量在线程之间的可见性(无锁同步)，对于多个线程共同访问的变量加volatile，如果不这样做就要用synchronized了，这样效率会低很多。另一方面，volatile并不能保证在多个线程共同修改同一变量时带来的不一致问题，即其不能代替synchronized，synchronized具有可见性和原子性CAS。

原子性：一个函数线程在执行过程中不会被另一个线程打断
```java
public class test{
  AtomicInteger count = new AtomicInteger(0);
  /*synchronized*/void m(){
    count.incrementAndGet();//replace "count++;"
  }
}
```
Atomic的方法如果不加锁的话还是可能被别的线程打断，这里讲的主要想表达：当一个函数线程当中有若干个原子操作，但是在原子操作之间还是可能被打断的。

synchronized优化中，应该只给有需要的业务逻辑上锁，避免给整个函数线程上锁(如果不需要这样做的话)。
比如下面的话m2()显然会比m1()好：
```java
synchronized void m1(){
  try{
    //something redunctant
  }catch(Exception e){
    //
  }
  //something really useful
  try{
    //something redunctant
  }catch(Exception e){
    //
  }
}

void m2(){
  try{
    //something redunctant
  }catch(Exception e){
    //
  }
  synchronized(this){
    //something useful
  }
  try{
    //something redunctant
  }catch(Exception e){
    //
  }
}
```

锁定某对象o，如果o的属性发生改变，不影响锁的使用。但是如果o指向另一个对象，则锁定的对象发生改变，应该避免将对象的引用变成另外一个对象。这是因为：
注意，synchronized这个锁是锁在堆内存上new出来的(真正的)对象上，而不是栈内存那个引用。锁的信息是记录在堆内存里面哒。


不要用字符串常量作为锁定对象，因为如上所述，synchronized其实锁定的是那个对象，而字符串常量内容一致的话其实是同一个对象。比如说下面的例子m1和m2其实锁定的是同一个对象：
```java
public class test{
  String s1 = "hello";
  String s2 = "hello";
  void m1(){
    synchronized(s1){
        //...........
    }
  }
  void m2(){
    synchronized(s2){
       //...........
    }
  }
}
```


wait会释放锁，notify不会释放锁。
使用上述两个方法必须加锁synchronized。使用notify，由CPU调度器找一个等待的线程唤醒执行，无法自主指定。


还需要加上一个问题的几个对比解法
