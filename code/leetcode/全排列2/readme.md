# [47. 全排列 II](https://leetcode-cn.com/problems/permutations-ii/)

> 难度中等

给定一个可包含重复数字的序列，返回所有不重复的全排列。

**示例:**

```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```



解法

- 低效率版本：使用和[全排列1](https://github.com/ogugugugugua/Java-Notes/tree/fe5d8f27ae9ed189b7095ff058e82ed2dfb2c858/code/leetcode/%E5%85%A8%E6%8E%92%E5%88%97)相同的套路，只是把结果集类型换乘HashSet来规避重复数组，这样并没有进行剪枝，所以会有很多无用的重复计算。
- 高效率版本：剪枝。

