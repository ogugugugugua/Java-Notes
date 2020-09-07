## Collection

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122191848295.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

数组和集合的区别：

> 数组的长度固定。
>
> 集合的长度可变。

### List

集合的的最大目的就是为了存取；List集合的特点就是存取有序，可以存储重复的元素，可以用下标进行元素的操作

- ArrayList	底层是使用数组实现，所以查询速度快，增删速度慢

- LinkedList  是基于链表结构实现的，所以查询速度慢，增删速度快，提供了特殊的方法，对头尾的元素操作（进行增删查）



### Queue

队列是一种特殊的线性表，只允许在表的前端进行删除操作，而在表的后端进行插入操作。我们可以把LinkedList当做Queue来用，因为其实现了Queue接口：

```java
import java.util.LinkedList;
import java.util.Queue;
public class Main {
    public static void main(String[] args) {
        //add()和remove()方法在失败的时候会抛出异常(不推荐)
        Queue<String> queue = new LinkedList<String>();
        //添加元素
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        queue.offer("d");
        queue.offer("e");
        System.out.println("poll="+queue.poll()); //返回第一个元素，并在队列中删除
        System.out.println("element="+queue.element()); //返回第一个元素 
        System.out.println("peek="+queue.peek()); //返回第一个元素 
        for(String q : queue){
            System.out.println(q);
        }
    }
}
```

> **offer，add 区别：**
>
> 一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。
>
> 这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。
>
> **poll，remove 区别：**
>
> remove() 和 poll() 方法都是从队列中删除第一个元素。remove() 的行为与 Collection 接口的版本相似， 但是新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null。因此新的方法更适合容易出现异常条件的情况。
>
> **peek，element区别：**
>
> element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null。
>
> **总结：使用offer来增加，poll来删除，peek来查询。**

### Set

## StringBuilder/StringBuffer

用于多次修改字符串，而不产生新的未使用对象。

> StringBuilder:速度快，线程不安全。StringBuffer反过来。
>
> public StringBuffer append(String s) 将指定的字符串追加到此字符序列。
>
>  public StringBuffer reverse()  将此字符序列用其反转形式取代。
>
> public delete(int start, int end) 移除此序列的子字符串中的字符。
>
>  public insert(int offset, int i) 将 `int` 参数的字符串表示形式插入此序列中。
>
>  replace(int start, int end, String str) 使用给定 `String` 中的字符替换此序列的子字符串中的字符。

## Hash相关

### Hash表简介

> 非Hash表的特点是：`关键字key` 与 `其在表中的位置` 不存在一个确定关系。因此搜索效率取决于比较的次数。
>
> Hash表的特点是：`关键字key` 与 `其在表中的位置` 存在一个确定的关系。

Hash函数：用于建立 `关键字key` 与 `其在表中的位置` 的函数关系，比如`f(key)=key在表中的位置`，那么`f`即为Hash函数。

这种函数就是将任意长度的输入，变成固定长度的输出(即散列值)。显然是一种压缩映射，即Space(输入)>>Space(输出散列值)，因此不可能从散列值来唯一地确定输入值。

上面的原因导致会产生`Hash冲突`，即 `f(key2)==f(key1)，当key2!=key1`。

![image-20200906231421217](https://user-images.githubusercontent.com/17522733/92412248-79693280-f14b-11ea-98b2-c789b5a1ee62.png)

---

HashMap和HashTable的区别

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122192155107.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

---

### 在Java中具体的HashMap实现：

HashMap实际是一种“**数组+链表**”数据结构。在put操作中，通过内部定义算法寻止找到数组下标，将数据直接放入此数组元素中，若通过算法得到的该数组元素已经有了元素（俗称hash冲突，链表结构出现的实际意义也就是为了解决hash冲突的问题）。将会把这个数组元素上的链表进行遍历，将新的数据放到链表末尾。

![img](https://user-gold-cdn.xitu.io/2018/12/21/167cf3bc35bcc29e?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

上面看到的这些Node的实现：

```java
static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;	//存储数据的key
        V value;		//存储数据的Value
        Node<K,V> next; //下一个数据，出现哈希冲突时，该数组元素会出现链表结构，会使用next指向链表中下一个元素对象        
}
```



### HashMap的put方法详解：

> 1、首选判断table是否为空，数组长度为空，将会进行第一次初始化。（在实例化HashMap是，并不会进行初始化数组）
>
> 2、进行第一次resize()扩容之后。开始通过hash算法寻址找到数组下标。若数组元素为空，则创建新的数组元素。若数组元素不为空，同时hash相等，key不相等，同时不是TreeNode数据对象，将遍历该数组元素下的链表元素。若找到对应的元素，则覆盖，如果没有找到，就新建元素，放入上一个链表元素的next中，在放入元素之后，如果条件满足"链表元素长度>8"，则将该链表结构转为"红黑树结构"。
>
> 3、找到对应的数组元素或者链表元素，同时创建新的数据元素或者覆盖元素之后。如果条件满足元素大小size>允许的最大元素数量threshold，则再一次进行扩容操作。每次扩容操作，新的数组大小将是原始的数组长度的两倍。
>
> 4、put操作完成。

```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 判断数组table是否为空，长度是否为0，是则进行扩容数组初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        // 通过hash算法找到数组下标得到数组元素，为空则新建
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null); //新建即直接插入该键值对
        else {
            Node<K,V> e; K k;
             // 找到数组元素，hash相等同时key相等，则直接覆盖value
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            // key不相等，判断该数组元素是否对应为红黑树结构的对象
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value); //如果是则直接插入键值对
            else {
                // 该数组元素hash相等，key不等，同时链表长度<8.进行遍历寻找元素，有就覆盖无则新建
                for (int binCount = 0; ; ++binCount) {
                    if ((e = p.next) == null) {
                        // 新建链表中数据元素
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);// 链表长度>=8 结构转为 红黑树
                        break;
                    }
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize(); //扩容
        afterNodeInsertion(evict);
        return null;
    }
```

![img](https://user-gold-cdn.xitu.io/2018/12/21/167cf3bc3724604a?imageView2/0/w/1280/h/960/format/webp/ignore-error/1)

---

### HashMap的get方法详解：

通过目标key的hashCode和寻址算法得到数组下标，若数组元素中的key，hash分别和目标的一致，则直接返回。若不相等，则在链表或者红黑树中进行匹配寻找并返回

```java
final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            if (first.hash == hash && // always check first node，先判断hash相等
                ((k = first.key) == key || (key != null && key.equals(k)))) //再判断key相等
                return first; //相等则直接返回
            if ((e = first.next) != null) {	//存在链表或红黑树结构
                if (first instanceof TreeNode)	//判断是否为红黑树
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);	//直接在树中进行匹配
                do { //这段是在链表中进行匹配
                    if (e.hash == hash && //先判断hash相等
                        ((k = e.key) == key || (key != null && key.equals(k)))) //再判断key相等
                        return e; //相等则返回
                } while ((e = e.next) != null);
            }
        }
        return null;
    }
```

---

### HashMap的扩容resize方法简介：

由于扩容后需要把键值对重新放到对应的位置，这里使用一个特殊机制，以简化重新计算位置的操作：

假设原数组长度capacity为16，扩容后new capacity为32：

> capacity		: 00010000
>
> new capacity : 00100000

对于一个key，如果其hashCode在第5位为0，则取模后结果不变。如果为1，则得到的结果为原来结果+16。

总结：经过重新哈希rehash之后，元素的位置要么在原位置，要么在原位置再移动2次幂的位置。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190122192145386.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxeHg2NjYx,size_16,color_FFFFFF,t_70)

---

### Hash冲突处理方法

#### 开放定址法

m为表的长度

冲突后对增量di的三种取值方法：

- 线性探测再散列：
  $$
  di = 1,2,3,...,m-1
  $$

- 平方探测再散列：
  $$
  di = +1^2 , -1^2, +2^2, -2^2, +3^2, -3^2, ..., +k^2, -k^2
  $$

- 

- 随机探测再散列：
  $$
  di = 伪随机数列
  $$

![image-20200906232425870](https://user-images.githubusercontent.com/17522733/92412270-93a31080-f14b-11ea-942a-ef12e9b8715d.png)

---

#### 链地址法









### 与Java集合框架相关的有哪些最好的实践

如果是单列的集合，我们考虑用Collection下的子接口ArrayList和Set。

如果是映射，我们就考虑使用Map

- 是否需要同步：去找线程安全的集合类使用
- 迭代时是否需要有序(插入顺序有序)：去找Linked双向列表结构的
- 是否需要排序(自然顺序或者手动排序)：去找Tree红黑树类型的(JDK1.8)
- 估算存放集合的数据量有多大，无论是List还是Map，它们实现动态增长，都是有性能消耗的。在初始集合的时候给出一个合理的容量会减少动态增长时的消耗
- 使用泛型，避免在运行时出现ClassCastException
- 尽可能使用Collections工具类，或者获取只读、同步或空的集合，而非编写自己的实现。它将会提供代码重用性，它有着更好的稳定性和可维护性