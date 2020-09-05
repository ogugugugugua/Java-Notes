## Collection

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