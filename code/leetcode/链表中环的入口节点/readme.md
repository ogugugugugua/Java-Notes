## 题目描述

对于一个给定的链表，返回环的入口节点，如果没有环，返回null

拓展：

你能给出不利用额外空间的解法么？

---



方法1：使用HashSet简单搞定

方法2：

```java
public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            ////利用快慢指针找相遇点
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                ////设置以相同速度的新指针从起始位置出发
                ListNode slow2 = head;
                while (slow != slow) {      //未相遇循环。
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow2;
            }
        }
        return null;
    }
```

![链表中环的入口节点 (1)](https://user-images.githubusercontent.com/17522733/94061426-a83b0600-fde5-11ea-8575-361dfbc4271e.png)

## 快慢指针：

当两个指针在C点相遇时，慢指针走了`(a+b)`长度，由于快指针是慢指针速度的2倍，所以快指针走了`2(a+b)`长度。

另一个角度，此时快指针走了`a+b`后又绕了`n`圈的`(b+c)`。

所以有等式`2(a+b) = a+b+n(b+c)`。

所以`a`的长度相当于慢指针从B出发走`n`圈的`(b+c)`再减去`b`，即相当于慢指针走`n-1`圈的`(b+c)`再加上`c`。

变量替换`k=n-1`，即`a`的长度相当于`k`圈的`(b+c)+c`。

所以从C出发一个慢指针，从A出发一个相同速度的慢指针，他们会在B点相遇。