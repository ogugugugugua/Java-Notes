# JVM笔记

## 常见面试题：

1. 请谈谈对JVM的理解？java8虚拟机和之前的变化更新？
2. 什么是OOM？什么是栈溢出StackOverFlowError？怎么分析？
3. JVM的常用调优参数有哪些？
4. 内存快照如何抓取？怎么分析Dump文件？
5. 谈谈JVM中，类加载器的认识？

---

## JVM的位置

基于操作系统之上，相当于一个环境。

---

## JVM的体系结构

下图中绿色区域为线程共享数据，黄色区域为线程私有数据。

![未命名文件](https://user-images.githubusercontent.com/17522733/93140660-1cd5bc80-f6e3-11ea-8057-1746f222bb97.png)

显然黄色区域的栈、本地方法栈、程序计数器这三个线程私有数据并**不会**有垃圾回收的问题。

因此所谓的**JVM调优**，都会集中在**堆**和方法区那里，方法区可以看成是一个特殊的堆。

细化之后是这个样子：

![未命名文件 (3)](https://user-images.githubusercontent.com/17522733/93598189-a247be00-f9bc-11ea-8819-9a6816ceb2ef.png)

---

## 类加载器

### 作用：

加载.class文件。查看方法是`.getClassLoader()`

### 过程：

1. 类加载器收到类加载的请求
2. 将这个请求向上委托给父类加载器去完成，一直向上委托直到启动类加载器ROOT
3. 启动加载器检查是否能够加载当前该类，能加载就搞定结束，否则抛出异常通知子加载器进行加载
4. 重复步骤3

### 类别：

- 虚拟机自带的加载器
- 启动类的加载器/根加载器 `Bootstrap`： 
  - 用Native代码实现的类加载器。负责将`<Java_Runtime_Home>/lib`下面的类库加载到内存中(比如rt.jar)。由于引导类加载器涉及到虚拟机本地实现细节，所以开发者无法直接获取到启动类加载器的引用，所以不允许直接通过引用进行操作。
- 扩展类加载器 `ExtClassLoader`：
  - 由Sun的`sum.misc.Launcher$ExtClassLoader`实现的。负责将`<Java_Runtime_Home>/lib/ext`或者由系统变量`java.ext.dir`指定位置中的类库加载到内存中。开发者可以直接使用标准扩展类加载器。
- 应用程序加载器 `AppClassLoader`：
  - 由Sun的`sum.misc.Launcher$AppClassLoader`实现的。负责将系统类路径`CLASSPATH`中指定的类库加载到内存中。开发者可以直接使用应用程序加载器。

---

## 双亲委派机制

某个特定的类加载器在接到加载类的请求时，首先将加载任务委托给父类加载器，**依次递归**，如果父类加载器可以完成类加载任务，就成功返回；只有父类加载器无法完成此加载任务时，才自己去加载。

> 白话文描述： 用于保证安全。会逐层往下寻找类的所在包，比如先在ROOT根加载器中找，然后再去EXT扩展类加载器中找，最后在APP应用程序加载器中找，如果APP和ROOT中有同名包，则会优先执行ROOT中的那个。

几点思考：

1. Java虚拟机的第一个类加载器是Bootstrap，这个加载器很特殊，**它不是Java类，因此它不需要被别人加载，它嵌套在Java虚拟机内核里面，也就是JVM启动的时候Bootstrap就已经启动，它是用C++写的二进制代码（不是字节码）**，它可以去加载别的类。这也是我们在测试时为什么发现`System.class.getClassLoader()`结果为null的原因，这并不表示System这个类没有类加载器，而是它的加载器比较特殊，是`BootstrapClassLoader`，由于它不是Java类，因此获得它的引用肯定返回null。

2. 双亲委托机制的意义：防止内存中出现多份同样的字节码：

   如果两个类A和类B都要加载System类，如果不用委托而是自己各自加载，那么内存中就会出现两份System字节码。

   在使用委托机制的情况下，两次System类的加载都交给了Bootstrap来处理，就可以防止重复加载的情况发生。



---

## 沙箱安全机制

组成沙箱的基本组件：

- 字节码校验器（bytecode verifier）：确保Java类文件遵循Java语言规范。帮助实现内存保护。
- 类装载器（Class loader）：其在3个方面对Java沙箱其作用：
  - 防止恶意代码去干涉善意代码		//双亲委派机制
  - 守护了被信任的类库边界
  - 将代码归入保护域，确定了代码可以进行哪些操作

---

## Native

典型如`new Thread().start()`函数中有一行：`private native void start0();` 即为native函数。

凡是带了native关键字的方法，说明java的作用范围达不到了，会去调用底层C语言的库。这些函数都会进入**本地方法栈**，调用本地方法接口，即JNI。 

---

## PC寄存器

program counter register

每个线程都有一个程序计数器，是线程私有的，就是一个指针，指向方法区的方法字节码，在执行引擎读取下一条指令。用于线程切换出去和切换回来时，找到原来的执行位置。

---

## 方法区

Method area

被所有线程共享的，包含所有字段和方法字节码，以及一些特殊方法，比如构造函数，接口代码也在这里定义。即所有定义的方法的信息都保存在这个区域。

**静态变量，常量，类信息（构造方法，接口定义），运行时的常量池存在方法区中，但是实例变量存在堆内存中，和方法区无关。**

---

## 栈VS堆 

栈：FILO。线程结束，栈内存就释放，故对于栈来说不存在垃圾回收。

栈运行原理：栈帧。程序正在执行的方法一定在栈的顶部。栈满就会有StackOverFlowError。

栈，堆的交互关系：栈中的引用指向堆中的对象具体的实例。堆中的常量指向元空间中常量池里的东西。

![image-20200915211525421](https://user-images.githubusercontent.com/17522733/93254468-ab584580-f798-11ea-957d-e6ea90e0d372.png)

**画出在内存中一个对象实例化的过程。**

---

## 堆

堆内存的大小是可以调节的（调优）。 类的实例，方法，常量，变量等会放在堆中。保存的是引用类型的真实对象。

---

## 新生区VS老年区VS永久区

新生区：所有的对象都在Eden区诞生。minor gc能留下来的去到了幸存者区(0/1)。 

![image-20200916233558425](https://user-images.githubusercontent.com/17522733/93395012-7110a600-f875-11ea-931c-373cedf73a8e.png)



在物理实现上，`年轻代空间PSYongGen + 老年代空间ParOldGen = Runtime.getRuntime().totalMemory();` 也就是说元空间这个东西在逻辑上存在，在物理上不存在。

---

## 堆内存调优

### 当遇到OOM的时候，可以：

1. 尝试扩大堆内存的空间，比如 `-Xms1024m -Xmx1024m -XX:+PrintGCDetails`
2. 如果不行的话，分析内存，看一下哪里出现了问题

### 常用命令：

1. `-Xms` 设置初始化内存分配大小
2. `-Xmx` 设置最大分配内存
3. `-XX:+PrintGCDetails`  打印GC垃圾回收信息
4. `-XX:+HeapDumpOnOutOfMemoryError`  内存不足时dump

### 分析示例：

```java
public class Solution1 {
    byte[] array = new byte[1*1024*1024];
    public static void main(String[] args) {
        ArrayList<Solution1> list = new ArrayList<>();
        int count = 0;
        try{
            while (true){
                list.add(new Solution1());
                count = count+1;
            }
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }
    }
}
```

上述代码示例用于反复产生数组以撑爆堆空间，需要搭配一下的命令参数：

```shell
-Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
```

然后可以在输出窗口看到如下信息：

> java.lang.OutOfMemoryError: Java heap space
> Dumping heap to java_pid24136.hprof ...
> Heap dump file created [7725079 bytes in 0.010 secs]
> java.lang.OutOfMemoryError: Java heap space
> 	at Solution1.<init>(Solution1.java:4)
> 	at Solution1.main(Solution1.java:10)
>
> Process finished with exit code 0

在文件管理系统中找到如下文件`java_pid24136.hprof`，双击可以用`jprofiler`软件打开，如图

![image-20200918155900165](https://user-images.githubusercontent.com/17522733/93606386-44b96e80-f9c8-11ea-8174-495e05272efd.png)

可以看到“大对象”中有一个ArrayList很明显很大，点进去`Heap Walker --> Thread Dump`中可以发现有具体的代码错误提示：

![image-20200918160011353](https://user-images.githubusercontent.com/17522733/93606358-3a977000-f9c8-11ea-85e3-eb46c854042b.png)

通过这种方法便可以进行分析了。

---

## GC常用算法

1. ### 标记清除法

   （1）扫描，对活着的对象进行一个标记。（2）扫描，对没有标记的对象进行清除。

   优点：没有冗余空间

   缺点：两次扫描浪费时间。会产生内存碎片。

2. ### 标记整理法

   （1）扫描，对活着的对象进行一个标记。（2）扫描，对没有标记的对象进行清除。（3）扫描，将存活的对象移到一侧

3. ### 复制算法

   主要用于年轻代。对于两个幸存区from和to，谁空谁是to。

   当一个对象经历了15次minor GC都还存活的时候，就会进入老年区。使用命令`-XX:MaxTenuringThreshold=5`可以修改进入老年区前能抗多少次minor GC。

   **优点**：没有内存的碎片

   **缺点**：浪费了内存空间。极端情况下，若对象100%存活，那么每次minor GC的时候都会将幸存区的对象进行一次完整的复制，会有很大的消耗。

   根据优缺点**总结**：复制算法最佳使用场景是对象存活度较低的情况，也就是在新生区。

4. ### 引用计数法

   将对象的使用次数进行标记计数，没用过的就清除。

### 总结

- 内存效率（时间复杂度）：复制算法 > 标记清除算法 > 标记整理算法
- 内存整齐度：复制算法 = 标记整理算法 > 标记清除算法
- 内存利用率：标记整理算法 = 标记清除算法 > 复制算法
- **适用情况：**
  - 年轻代：对象存活率低，适用复制算法
  - 老年代：区域大，存活率高，当内存碎片不过多时适用标记清除算法，当碎片到达一定量时可以搭配使用标记整理算法。（分代收集算法）

## JMM

Java Memory Model

用于定义数据读写的规则。https://github.com/ogugugugugua/Java-Notes/blob/master/note/JMM.md



---

