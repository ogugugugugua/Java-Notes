# 动态规划

动态规划可以看成一种由于保存了过去计算过的值从而使程序运行更快的算法~

## 动态规划问题特点：

1. 计数：
   1. 有多少种方式走到右下角
   2. 有多少种方式选出k个数是的和是sum
2. 求最大最小值：
   1. 从左上角走到右下角路径的最大数字和
   2. 最长上升序列长度
3. 求存在性：
   1. 取石子游戏，先手是否必胜
   2. 能不能选出k个数使得和是sum

---

## 经典问题引入：

> 斐波那契数列 `fib(n) = fib(n-1) + fib(n-2)`，如果按照指定n大小去求值的话，就会重复计算很多次，算法的复杂度是`O(n^2)`，因此我们可以考虑换个方向来计算：已知`fib(1)`和`fib(2)`先计算`fib(3)`，然后算`fib(4)`，再算`fib(5)`……这样每往后算一位都可以直接拿前面算出来的放在内存里的值，就会很快，算法复杂度降为`O(n)`，这样的思想即为动态规划。

---

## 问题1：

下面这个是经典的动态规划问题。横轴为时间，上面每一组横向代表工作时间段，每一组中的红字代表赚钱数量，现在需要max一天中所赚的钱。

![image-20200911112838223](https://user-images.githubusercontent.com/17522733/92904627-4fea2880-f423-11ea-8cb1-61de7f5aead7.png)

这个问题可以用“选/不选”的方式来考虑：有函数`OPT(i)`代表对**第i个下标来说的当前最优值**，比如对于第`i=8`个任务，那么`OPT(8)=MAX(4+OPT(5) , OPT(7))`,即：如果选择做第8个任务，可以获得收益为4，但是上一个任务最多是第5个任务了，因为第6第7个都会有重叠；如果选择不做第8个任务，那就变成了是否选择做第7个任务的最优解了。

上述思想泛化之后可以抽象为：`OPT(i) = MAX( V_i + OPT(prev(i))  ,  OPT(i-1))`，其中V_i为当前任务收益，prev(i)为前一个最接近的 可行任务，比如prev(7)=3。

由上述的抽象关系可以知道其分解后类似斐波那契数列，同理可以从问题规模最小的时候开始算起。其计算过程可以列表为如下：

|  i   | V_i  | prev(i) |           OPT(i)           |
| :--: | :--: | :-----: | :------------------------: |
|  1   |  5   |    0    | MAX(5+OPT(0), OPT(0)) = 5  |
|  2   |  1   |    0    | MAX(1+OPT(0), OPT(1)) = 5  |
|  3   |  8   |    0    | MAX(8+OPT(0), OPT(2)) = 8  |
|  4   |  4   |    1    | MAX(4+OPT(1), OPT(3)) = 9  |
|  5   |  6   |    0    | MAX(6+OPT(0), OPT(4)) = 9  |
|  6   |  3   |    2    | MAX(3+OPT(2), OPT(5)) = 9  |
|  7   |  2   |    3    | MAX(2+OPT(3), OPT(6)) = 10 |
|  8   |  4   |    5    | MAX(4+OPT(6), OPT(7)) = 13 |

便可以知道最后算出来max收入为13。

### 代码：

https://github.com/ogugugugugua/Java-Notes/blob/master/code/leetcode/DP/src/Main.java

### 测试数据：

https://github.com/ogugugugugua/Java-Notes/blob/master/code/leetcode/DP/src/data.txt

---



## 问题2：

给出一组用空格分隔的数字，在其中选出一组数字，使得其和最大，要求所选数字不相邻。

解法：





---

## 问题3：

给出一组用空格分隔的数字，在其中选出一组数字，使其和为固定值。

### 递归解法：

对于数组`arr`和固定值`S`

- 如果选择`arr[i]`，其值为`V_i`，则需要在剩下的i-1个数中拼凑出`S - V_i`。
- 如果不选`arr[i]`，则需要在剩下的`i-1`个数中拼凑出`S`。 
- 那么我们定义一个名为`public boolean rec_subset(int[] arr, int i, int S)`的函数，其中`arr`是数组，`i`是下标，S是目标固定值，其运算规则可以定义为`rec_subset(arr, i, S) = rec_subset(arr, i-1, S-V[i]) || rec_subset(arr, i-1, S)`，对于这个递归函数，其出口可以有3个可能：

1. `if(S==0) {return true;}`即当已经拼凑出这个固定值了，就可以返回true
2. `if(i==0) {return arr[i]==S;}` 即当数组已经遍历到边界了，若相等即拼凑成功返回true，否则返回false
3. `if(arr[i]>S) {return rec_subset(arr, i-1, S)}` 即当前值>固定值，只需要跳过当前值即可，减少遍历数量。(对所有正整数数组有效)。

### 非递归解法：

定义一个二维数组来保存过去计算出来的值。 

### 代码：

https://github.com/ogugugugugua/Java-Notes/blob/master/code/leetcode/DP/src/Main3.java

### 测试数据：

https://github.com/ogugugugugua/Java-Notes/blob/master/code/leetcode/DP/src/data.txt



---

来源教程： https://www.bilibili.com/video/BV12W411v7rd/?spm_id_from=333.788.videocard.0

----

## 动态规划与递归

动态规划是自底向上的：从最底下，最简单，问题规模最小的f(1)和f(2)开始网上推，直到找到想要的答案。这个过程一般由循环迭代完成计算。

```java
public int fibonacci(int n ){
	int[] dp = new int[n+1];
    dp[1] = dp[2] = 1;
    for(int i = 3; i <=n; i++){
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}
```

递归树是自顶向下的：有一个规模较大的问题向下逐渐分解规模，然后逐层返回答案。

```java 
public int fibonacci(int n){
	if(n==1 || n==2){
		return n;
	}
	return fibonacci(n-1) + fibonacci(n-2);
}
```

## 分类

动态规划一般可以分为 **线性动规**，**区域动规**，**树形动规**，**背包动规**四种。

1. 线性动规：拦截导弹，合唱队形，挖地雷，建学校，剑客决斗等；
2. 区域动规：石子合并， 加分二叉树，统计单词个数，炮兵布阵等；
3. 树形动规：贪吃的九头龙，二分查找树，聚会的欢乐，数字三角形等；
4. 背包问题：01背包问题，完全背包问题，分组背包问题，二维背包，装箱问题，挤牛奶（同济ACM第1132题）等；

应用实例：

最短路径问题 ，项目管理，网络流优化等；

