## 题目描述

将一个链表 *m* 位置到 *n* 位置之间的区间反转，要求时间复杂度 ![img](https://www.nowcoder.com/equation?tex=O(n)%20%5C)，空间复杂度 ![img](https://www.nowcoder.com/equation?tex=O(1)%20%5C)。
例如：
给出的链表为 1→2→3→4→5→*NULL*, ![img](https://www.nowcoder.com/equation?tex=m%20%3D%202%20%5C)，![img](https://www.nowcoder.com/equation?tex=n%20%3D%204%5C),
返回 1→4→3→2→5→*NULL*.
注意：
给出的 ![img](https://www.nowcoder.com/equation?tex=m%2Cn%5C)满足以下条件：
1≤*m*≤*n*≤链表长度

### 示例1

#### 输入

```
{1,2,3,4,5},2,4
```

#### 输出

```
{1,4,3,2,5}
```

---



## 方法1：

使用了一个长度为`n-m+2`大小的数组来存放数组里的指针，然后挨个翻转。

## 方法2：

只是用3个核心指针进行翻转。过程类似冒牌排序，不断地往前进，把最前面的那个节点丢到固定指针pre的后面，直到把区间内的所有节点都依次丢到后面，即完成了翻转。

![链表内指定区间反转](https://user-images.githubusercontent.com/17522733/94339724-67690a00-fffc-11ea-91a7-3871aec7def5.png)