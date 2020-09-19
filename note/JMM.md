# JMM

## Java内存模型。

更准确说是“**Java线程内存模型**”。其与CPU缓存模型类似，是标准化的，屏蔽了底层不同计算机的区别。



![未命名文件 (7)](https://user-images.githubusercontent.com/17522733/93672081-6558f580-faa8-11ea-8eea-69497c849c2e.png)

这里牵涉到一个可见性问题，每一个线程都会在主内存中获取一个所需内容的副本，用于自己线程。那么不同的线程之间就会可能出现各自工作内存中同一对象的值不一致问题。

### 我们可以对这个现象进行一个模拟：

```java
public class Solution1 {
    public static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {							//线程A
            @Override
            public void run() {
                System.out.println("Waiting");
                while (!flag){}
                System.out.println("End waiting....");
            }
        }).start();

        Thread.sleep(2000);								    //确保线程A已经启动

        new Thread(new Runnable() {							//线程B
            @Override
            public void run() {
                flag = true;
            }
        }).start();
    }
}
```

而在控制台处的输出：

> Waiting

可见在线程A中的while循环并没有停止，尽管flag的值在表面上已经被线程B修改成了true。

因此需要在flag前面加上**volatile**修饰，表示该共享变量在多个线程的工作内存之间的可见性：

```java
public static volatile boolean flag = false;
```

此时的控制台输出就会和预想的一样：

> Waiting
>
> End waiting....

## JMM数据原子操作

|    操作类型    |                      解析                      |
| :------------: | :--------------------------------------------: |
|  read（读取）  |                从主内存读取数据                |
|  load（载入）  |        将主内存读取到的数据写入工作内存        |
|  use（使用）   |            从工作内存读取数据来计算            |
| assign（赋值） |        将计算好的值重新赋值到工作内存中        |
| store（存储）  |            将工作内存数据写入主内存            |
| write（写入）  |    将store过去的变量值赋值给主内存中的变量     |
|  lock（锁定）  |      将主内存变量加锁，标识为线程独占状态      |
| unlock（解锁） | 将主内存变量解锁，解锁后其他线程可以锁定该变量 |

![未命名文件 (5)](https://user-images.githubusercontent.com/17522733/93671584-79025d00-faa4-11ea-91cf-2d876b2bebd9.png)

## JMM缓存不一致问题

**总线加锁（性能太低，已弃用）**：CPU从主内存读取数据到告诉缓存，会在总线对这个数据加锁，直到用完释放锁之后其他CPU才能访问该数据。本质上已经把并行操作变成了串行操作。

**MESI缓存一致性协议**：多个CPU从主内存读取同一个数据到各自的高速缓存，当其中某个CPU修改了缓存里的数据，该数据会马上同步回主内存，其他**CPU通过总线嗅探机制**可以感知到数据的变化从而将自己缓存里的数据失效。（如上图）

## Volatile可见性底层实现原理

底层实现主要是通过汇编lock前缀指令，其会锁定这块内存区域的缓存（缓存行锁定）并回写到主内存。这种回写是马上实现的，能保证实时性。

并发编程三大特性：可见性，原子性，有序性

volatile保证可见性和有序性，但是不保证原子性。保证原子性需要借助synchronized这样的锁机制。其不保证原子性的原因就在于JMM模型的机制，每个线程有独立的高速缓存，在不加锁的情况下，同时对同一对象进行修改，就会导致缓存不一致的问题。



