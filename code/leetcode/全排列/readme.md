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

