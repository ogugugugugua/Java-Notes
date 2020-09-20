<center>

# Multi Thread
</center>
<center>
Yulin XIE
</center>


<center>
## 线程状态转移图:

![多线程状态转移图](https://user-images.githubusercontent.com/17522733/93720203-db805980-fb87-11ea-9fa7-b66afa185098.png)

## 常用的中断线程的方法:

(1) 对目标线程调用`interrupt()`方法可以请求中断一个线程，目标线程通过检测`isInterrupted()`标志获取自身是否已中断。如果目标线程处于等待状态，该线程会捕获到`InterruptedException`；

目标线程检测到`isInterrupted()`为`true`或者捕获了`InterruptedException`都应该立刻结束自身线程；

(2) 设置标志位。我们通常会用一个`running`标志位来标识线程是否应该继续运行，在外部线程中，通过把`HelloThread.running`置为`false`，就可以让线程结束：

```java
public class Main {
    public static void main(String[] args)  throws InterruptedException {
        HelloThread t = new HelloThread();
        t.start();
        Thread.sleep(1);
        t.running = false; // 标志位置为false
    }
}

class HelloThread extends Thread {
    public volatile boolean running = true;
    public void run() {
        int n = 0;
        while (running) {
            n ++;
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}

```



## Course1

</center>

#### 1. Construction of Thread
1. 新建一个类，该类继承Thread，重写该类的run()方法。将该类实例化，然后调用start()方法。该线程类只是代表JVM中那个真正的线程，一旦启动start()，JVM就会真正地启动一个线程，在这个线程当中就会去调用run()方法，从而执行相应业务。

2. 使用接口。新建一个类实现implemets了Runnable()，由于没有继承Thread类，因此没有start()方法。所以启动的时候需要手动操作，先new一个Thread，然后把实现Runnable那个类的实例作为参数扔进去：

3. 使用匿名类的形式。匿名类的一个好处是可以很方便地访问外部的局部变量。

注意：启动线程是start()方法，run()并不能启动一个新的线程。

#### 2. Common Thread methods
- sleep
>1、线程睡眠是帮助所有线程获得运行机会的最好方法。

>2、线程睡眠到期自动苏醒，并返回到可运行状态，不是运行状态。sleep()中指定的时间是线程不会运行的最短时间。因此，sleep()方法不能保证该线程睡眠到期后就开始执行。

>3、sleep()是静态方法，只能控制当前正在运行的线程。


```java
public class test{
  public static main(String args[]){
    Thread thread7 = new Thread(){
        public void run(){
            int seconds = 0;
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                seconds++;
                System.out.println("Seconds: "+seconds);
            }
        }
    };
    thread7.start();
  }
}
```
- join
> thread.Join把指定的线程加入到当前线程，可以将两个交替执行的线程合并为顺序执行的线程。比如在线程B中调用了线程A的Join()方法，直到线程A执行完毕后，才会继续执行线程B。
```java
Thread thread7 = new Thread(){
              public void run(){
                  int seconds = 0;
                  while(seconds<5){
                      try {
                          Thread.sleep(1000);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      seconds++;
                      System.out.println("Thread 7 Seconds: "+seconds);
                  }
              }
          };
thread7.start();

/******************************/
try {
    //join the thread7 into conrrent thread(main thread) and wait thread7 for 3s, after that we don't care anymore
    thread7.join(3000);   /*Result:
                                    Thread 7 Seconds: 1
                                    Thread 7 Seconds: 2
                                    Thread 7 Seconds: 3
                                    Thread 8 Seconds: 1
                                    Thread 7 Seconds: 4
                                    Thread 8 Seconds: 2
                                    Thread 7 Seconds: 5
                                    Thread 8 Seconds: 3
                                    Thread 8 Seconds: 4
                                    Thread 8 Seconds: 5 */
    //join the thread7 into conrrent thread(main thread) and wait for thread7 to finish, then the conrrent thread continue
    thread7.join();     /*Result:
                                    Thread 7 Seconds: 1
                                    Thread 7 Seconds: 2
                                    Thread 7 Seconds: 3
                                    Thread 7 Seconds: 4
                                    Thread 7 Seconds: 5
                                    Thread 8 Seconds: 1
                                    Thread 8 Seconds: 2
                                    Thread 8 Seconds: 3
                                    Thread 8 Seconds: 4
                                    Thread 8 Seconds: 5 */
}catch (Exception e){
    e.printStackTrace();
}
/*****************************/
Thread thread8 = new Thread(){
    public void run(){
        int seconds = 0;
        while(seconds<5){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            seconds++;
            System.out.println("Thread 8 Seconds: "+seconds);
        }
    }
};
thread8.start();
```

- setPriority
```java
thread7.setPriority(thread.MAX_PRIORITY);
thread8.setPriority(thread.MIN_PRIORITY);
```

- yield
>yield()方法的作用是：让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。

>yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。

>sleep执行后线程进入阻塞状态;&emsp;yield执行后线程进入就绪状态;&emsp;join执行后线程进入阻塞状态

>join:当某个线程拥有cpu资源时，它决定把资源让给另一个特定的线程;&emsp;yield:当某个线程获得cpu时，它让出这个机会，给与它优先级相同或者更高的线程

- setDaemon 设置守护线程

有一类型的线程，其目的就是无限循环，例如一个定时触发任务的线程或者日志记录线程。如果其不结束，JVM进程就无法结束，所以需要使用守护线程，将该目标线程设置为守护线程。JVM在退出时，不必关心守护线程是否结束。

**注意**：守护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，守护线程没有任何机会来关闭文件，这会导致数据丢失！

When all threads in a processor are Daemon, the processor ends. Daemon threads are usually used to log or do performance statistic calculation.
```java
Thread thread7 = new Thread(){
    public void run(){
        int seconds = 0;
        while(seconds<5){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seconds++;
            System.out.println("Thread 7 Seconds: "+seconds);
        }
    }
};
thread7.setDaemon(true);
thread7.start();
```
Nothing will happen for the codes above because there is no threads that really performance task besides the main thread and therefore the processor will end when the main thread is done, and the Daemon thread exists as nothing.

#### 3. synchronized
如果一个类，其方法都是有synchronized修饰的，那么该类就叫做线程安全的类

同一时间，只有一个线程能够进入 这种类的一个实例 的去修改数据，进而保证了这个实例中的数据的安全(不会同时被多线程修改而变成脏数据)
```java
class object{
    public int number = 10000;
    public /*synchronized*/ void add(){
        this.number++;
    }
    public /*synchronized*/ void subtract(){
        this.number--;
    }
}
public class Synchronize {
    public static void main(String args[]){

        for(int temp = 0;temp<100;temp++){
            object ob = new object();
            int threads = 100;
            List<Thread> addList = new ArrayList<>();
            List<Thread> subtractList = new ArrayList<>();
            /*Add to the object ob*/
            for(int i=0;i<threads;i++){
                Thread t = new Thread(){
                    public void run(){
                        try{
                            Thread.sleep(100);
                            ob.add();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                addList.add(t);
            }
            /*Subtract from the object ob*/
            for(int i=0;i<threads;i++){
                Thread t = new Thread(){
                    public void run(){
                        try{
                            Thread.sleep(100);
                            ob.subtract();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                subtractList.add(t);
            }
```
```java
            for(Thread t:addList){
                try{
                    t.join();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            for(Thread t:subtractList){
                try{
                    t.join();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(ob.number);
        }
    }
}
```
In the case aboved, we can have results like:
>10007
9995
9997
10004
9990
9999
10001
9994
10005
9997
10004

That is because we didnot `synchronized` the object when we modify it, thus other threads can have the chance to modify it at the same time, which has polluted the data.

However,if we have the `object` class like this (with key word `synchronized` at the function):
```java
class object{
    public int number = 10000;
    public synchronized void add(){
        this.number++;
    }
    public synchronized void subtract(){
        this.number--;
    }
}
```
We will have results like this:
>10000
10000
10000
10000
10000
10000
10000
10000
10000
10000
10000

That is the power of `synchronized` !!!



#### 4. Safety of threads
- `HashTable` VS `HashMap`
>如果你不需要线程安全，那么使用HashMap，如果需要线程安全，那么使用ConcurrentHashMap。HashTable已经被淘汰了，不要在新的代码中再使用它。

- `StringBuffer` VS `StringBuilder`
>StringBuffer 是线程安全的;&emsp;
StringBuilder 是非线程安全的;&emsp;
所以当进行大量字符串拼接操作的时候，如果是单线程就用StringBuilder会更快些，如果是多线程，就需要用StringBuffer 保证数据的安全性

- `Vector` VS `ArrayList`
> Vector是线程安全的类，而ArrayList是非线程安全的。

- 改变方法：
> 借助Collections.synchronizedList，可以把ArrayList转换为线程安全的List。与此类似的，还有HashSet,LinkedList,HashMap等等非线程安全的类，都通过工具类Collections转换为线程安全的


#### 5. DeadLock
```java
class testObject{
    String name;
    public testObject(String name){
        this.name = name;
    }
}
public class DeadLock {
    public static void main(String args[]){
        final testObject a = new testObject("a");
        final testObject b = new testObject("b");
        final testObject c = new testObject("c");

        Thread t1 = new Thread(){
            public void run(){
                System.out.println("t1 enter");
                try{
                    synchronized (a){
                        System.out.println("t1 take a");
                        Thread.sleep(1000);
                        synchronized (b){
                            System.out.println("t1 take b");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        Thread t2 = new Thread(){
            public void run(){
                System.out.println("t2 enter");
                try{
                    synchronized (b){
                        System.out.println("t2 take b");
                        Thread.sleep(1000);
                        synchronized (c){
                            System.out.println("t2 take c");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t2.start();

        Thread t3 = new Thread(){
            public void run(){
                System.out.println("t3 enter");
                try{
                    synchronized (c){
                        System.out.println("t3 take c");
                        Thread.sleep(1000);
                        synchronized (a){
                            System.out.println("t3 take a");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t3.start();
    }
}
```
Noted that in the codes aboved, we need to put the second synchronized object part inside the first synchronized part, in order to maintain the possession of the first object.
Otherwise, we would lose the possession of the first object and thus there will not be any DeadLock anymore.


#### 6. Interaction between threads
`wait`和`notify`都不是线程的方法，而是对象的方法。
```java
class numberObject{
    public int number=100;
    public synchronized int getNumber(){
        return number;
    }
    public synchronized void add(){
        number+=3;
        this.notify();                   //IT IS HERE
    }
    public synchronized void subtract(){
        if(number<=20){
            try{
                this.wait();             //IT IS HERE
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        number--;
    }
}

public class WaitNotify {
    public static void main(String args[]){
        numberObject number = new numberObject();

        /*subtract*/
        Thread t1 = new Thread(){
            public void run(){
                while (true){
                    number.subtract();
                    System.out.println("subtract "+number.getNumber());
                    try{
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };

        /*add*/
        Thread t2 = new Thread(){
            public void run(){
                while (true){
                    number.add();
                    System.out.println("add "+number.getNumber());
                    //this.notify(); //This should be a method of Object, shouldn't be here.
                    try{
                        Thread.sleep(2000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
```
- Noted that we NEED to put the `synchronized` key word with the function of the numberObject and within that function we need to put the `this.wait()` and `this.notify()` inside. And __DO NOT__ put the `this.wait()` and `this.notify()` in the thread function in main().
- 这里需要强调的是，wait方法和notify方法，并不是Thread线程上的方法，它们是Object上的方法。

- 因为所有的Object都可以被用来作为同步对象，所以准确的讲，wait和notify是同步对象上的方法。

- wait()的意思是： 让占用了这个同步对象的线程，临时释放当前的占用，并且等待。 所以调用wait是有前提条件的，一定是在synchronized块里，否则就会出错。

- notify() 的意思是，通知一个等待在这个同步对象上的线程，你可以苏醒过来了，有机会重新占用当前对象了。

- notifyAll() 的意思是，通知所有的等待在这个同步对象上的线程，你们可以苏醒过来了，有机会重新占用当前对象了。

#### 7. One More Thing About `Wait` And `Notify`
For functions like this:
```java
public synchronized T pull(){
        if(this.stack.isEmpty()){ //ATENTION HERE
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        else{
            this.notify();
            return this.stack.removeLast();
        }
    }
public synchronized void push(T t) {
        if (this.stack.size()==200){ //ATENTION HERE
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.stack.addLast(t);
        this.notifyAll();
    }
```
Suppose there are several push and several pull threads. &emsp;
When several push functions threads are executed consecutively at almost the same time, and we find that `stack.size() >= 200` for all these push function threads, these threads will consecutively release the possesion of the current object and wait. Then comes a `pull` threads, which pulls out an object from the stack and then notify one of the waiting `push` function thread. This `push` function thread is thus awaked, addLast() then notifyAll(). In the situation, all other waiting `push` function threads can be awaked consecutively, addLast() and notifyAll() because the previous awaked `push` function thread called the `notifyAll()` method.

The similar situation can happen for the `pull` function thread which causes the output of `null` because of the same reason as aboved.

However, if we use codes like this:
```java
public synchronized void push(T t) {
        while (this.stack.size()==200){ //ATENTION HERE
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        this.notifyAll();
        //Can be put below, really takes effect when exiting the function thread and releasing the current object
        this.stack.addLast(t);
    }
public synchronized T pull(){
        while(this.stack.isEmpty()){ //ATENTION HERE
            try{
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
            this.notify();
            return this.stack.removeLast();
    }
```
We can avoid the problems that might come along with the previous codes by re-checking the situation of `stack.size()` every time a `push`/`pull` is awaked because we need to satisfy the constraint of `stack.size()` in order to proceed.

#### 8. Thread Pool
`ThreadPool` 包含了内部类`TaskConsumeThread`，也包含了存放任务的列表`Tasks`

`TaskConsumeThread`继承了`Thread`

`ThreadPool`中包含一个存放任务的列表`Tasks`，初始时该`Tasks`为空，但是构造函数会初始化出来10个`TaskConsumeThread`线程，每个`TaskConsumeThread`线程由于判断到`Tasks`为空而处在等待状态。在每个`TaskConsumeThread`中重写了`run`方法（所以可以把类型为`Runnable`的`task`进行执行），某个被唤醒的`TaskConsumeThread`中的`run`方法会在锁定任务列表`Tasks`并获取其中一个任务`task`之后，唤醒其余`TaskConsumeThread`，释放任务列表锁，通过调用该`task`自己的`run`方法，进行任务的处理，接着继续判断是否任务列表为空，若是，则进入休眠+释放任务列表锁+等待被唤醒，若否，则重复上述动作（拿出一个任务，唤醒其余`TaskConsumeThread`，释放任务列表锁，调用该任务自己的方法来处理，判断是否任务列表为空……）


为什么`TaskConsumeThread`线程不会因为执行完一个`Runnable`类型的`task`之后结束，是因为在`TaskConsumeThread`内始终进行着`while(true)`，然后在while循环里面调用`task`的`run`方法，调用完之后一判断，诶，任务列表是空的，那这个线程就休眠去了，直到①被别的`TaskConsumeThread`唤醒 或者 ②所有的`TaskConsumeThread`都休眠了但是有新的任务加进来列表中，调用`ThreadPool.add`的过程中锁定了任务列表，添加了任务后，使用了任务列表的`notifyAll()`，某一个等待任务列表的幸运儿线程`TaskConsumeThread`被唤醒了，在判断任务列表不为空后进入了工作状态。




<center>

![2600](https://user-images.githubusercontent.com/17522733/68603487-a0fa5a80-04a8-11ea-873f-9b7fc2e450bf.png)
</center>

JDK5之后提供了线程池相关API： ExecutorService 和 Executors

1. ExecutorService： 真正的线程池接口。常见子类ThreadPoolExecutor
   1. void execute(Runnable command)：执行任务/命令，没有返回值，一般用来执行Runnable
   2. <T> Future<T> submit(Callable<T> task)：执行任务，有返回值，一般用来执行Callable
   3. void shutdown() ：关闭线程池
2. Executors：工具类、线程池的工具类，用于创建并返回不同类型的线程池。

---

使用实例：

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Solution1 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        service.shutdown();

    }
}
class MyThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
```





















<center>

## Course2
</center>

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

----

## 总结：

关于线程的创建及使用：

```java 
import java.util.concurrent.*;

public class Solution1 {
    public static void main(String[] args) throws InterruptedException {
        //1
        new MyThread1().start();

        //2
        new Thread(new MyThread2()).start();

        //3
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread3());
        new Thread(futureTask).start();
        try {
            Integer integer = futureTask.get();
            System.out.println(integer);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("MyThread1");
    }
}

class MyThread2 implements Runnable{

    @Override
    public void run() {
        System.out.println("MyThread2");
    }
}

class MyThread3 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("MyThread3");
        return 2333;
    }
}
```

