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