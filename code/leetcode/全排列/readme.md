# [46. 全排列](https://leetcode-cn.com/problems/permutations/)

> 难度中等

给定一个 **没有重复** 数字的序列，返回其所有可能的全排列。

**示例:**

```
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```



## 题解

这题使用的是回溯/递归算法，在实现上和DFS有比较接近的地方。

在dfs这个递归函数里面，

- 如果递归调用次数达到数组长度，则将遍历路径加到结果集中，否则：

- 对于数组中的每一个未读数字，我们都会依次：
  - 将其标记为已读	
  - 加入到遍历路径的数组中
  - 对其子序列调用dfs函数进行操作
  - 将其标记为未读
  - 从遍历路径的数组中取出

```java
private void dfs(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 在非叶子结点处，产生不同的分支，这一操作的语义是：
        //在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, len, depth + 1, path, used, res);
         // 注意：下面这两行代码发生 「回溯」，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
```

![](https://pic.leetcode-cn.com/0bf18f9b86a2542d1f6aa8db6cc45475fce5aa329a07ca02a9357c2ead81eec1-image.png)



做题的时候，建议 先画树形图 ，画图能帮助我们想清楚递归结构，想清楚如何剪枝。拿题目中的示例，想一想人是怎么做的，一般这样下来，这棵递归树都不难画出。

在画图的过程中思考清楚：

分支如何产生；
题目需要的解在哪里？是在叶子结点、还是在非叶子结点、还是在从跟结点到叶子结点的路径？
哪些搜索会产生不需要的解的？例如：产生重复是什么原因，如果在浅层就知道这个分支不能产生需要的结果，应该提前剪枝，剪枝的条件是什么，代码怎么写？







----

### 题型一：排列、组合、子集相关问题

提示：这部分练习可以帮助我们熟悉「回溯算法」的一些概念和通用的解题思路。解题的步骤是：先画图，再编码。去思考可以剪枝的条件， 为什么有的时候用 used 数组，有的时候设置搜索起点 begin 变量，理解状态变量设计的想法。

- [全排列](https://leetcode-cn.com/problems/permutations/)（中等）
- [全排列](https://leetcode-cn.com/problems/permutations-ii/) II（中等）：思考为什么造成了重复，如何在搜索之前就判断这一支会产生重复；
- [组合总和](https://leetcode-cn.com/problems/combination-sum/)（中等）
- [组合总和](https://leetcode-cn.com/problems/combination-sum-ii/) II（中等）
- [组合](https://leetcode-cn.com/problems/combinations/)（中等）
- [子集](https://leetcode-cn.com/problems/subsets/)（中等）
- [子集](https://leetcode-cn.com/problems/subsets-ii/) II（中等）：剪枝技巧同 47 题、39 题、40 题；
- 第k[个排列](https://leetcode-cn.com/problems/permutation-sequence/)（中等）：利用了剪枝的思想，减去了大量枝叶，直接来到需要的叶子结点；
- 复原 [IP](https://leetcode-cn.com/problems/restore-ip-addresses/) 地址（中等）

### 题型二：Flood Fill

提示：Flood 是「洪水」的意思，Flood Fill 直译是「泛洪填充」的意思，体现了洪水能够从一点开始，迅速填满当前位置附近的地势低的区域。类似的应用还有：PS 软件中的「点一下把这一片区域的颜色都替换掉」，扫雷游戏「点一下打开一大片没有雷的区域」。

下面这几个问题，思想不难，但是初学的时候代码很不容易写对，并且也很难调试。我们的建议是多写几遍，忘记了就再写一次，参考规范的编写实现（设置 visited 数组，设置方向数组，抽取私有方法），把代码写对。

- [图像渲染](https://leetcode-cn.com/problems/flood-fill/)（Flood Fill，中等）

- [岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)（中等）

- [被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)（中等）

- [单词搜索](https://leetcode-cn.com/problems/word-search/)（中等）

  说明：以上问题都不建议修改输入数据，设置 visited 数组是标准的做法。可能会遇到参数很多，是不是都可以写成成员变量的问题，面试中拿不准的记得问一下面试官

### 题型三：字符串中的回溯问题

提示：字符串的问题的特殊之处在于，字符串的拼接生成新对象，因此在这一类问题上没有显示「回溯」的过程，但是如果使用 StringBuilder 拼接字符串就另当别论。
在这里把它们单独作为一个题型，是希望朋友们能够注意到这个非常细节的地方。

- [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)（中等），题解；
- [字母大小写全排列](https://leetcode-cn.com/problems/letter-case-permutation/)（中等）；
- [括号生成](https://leetcode-cn.com/problems/generate-parentheses/)（中等） ：这道题广度优先遍历也很好写，可以通过这个问题理解一下为什么回溯算法都是深度优先遍历，并且都用递归来写。

### 题型四：游戏问题

回溯算法是早期简单的人工智能，有些教程把回溯叫做暴力搜索，但回溯没有那么暴力，回溯是有方向地搜索。「力扣」上有一些简单的游戏类问题，解决它们有一定的难度，大家可以尝试一下。

- N [皇后](https://leetcode-cn.com/problems/n-queens/)（困难）：其实就是全排列问题，注意设计清楚状态变量，在遍历的时候需要记住一些信息，空间换时间；
- [解数独](https://leetcode-cn.com/problems/sudoku-solver/)（困难）：思路同「N 皇后问题」；
- [祖玛游戏](https://leetcode-cn.com/problems/zuma-game/)（困难）
- [扫雷游戏](https://leetcode-cn.com/problems/minesweeper/)（困难）

