# [40. 组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/)

> 难度中等

给定一个数组 `candidates` 和一个目标数 `target` ，找出 `candidates` 中所有可以使数字和为 `target` 的组合。

`candidates` 中的每个数字在每个组合中只能使用一次。

**说明：**

- 所有数字（包括目标数）都是正整数。
- 解集不能包含重复的组合。 

**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

**示例 2:**

```
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```

---

## 剪枝策略：

对于一个已经重新排序好了的数组而言，当前的题目需要有两种剪枝:

1. 大剪枝：如果相加和sum已经大于target，对于当前层而言直接返回即可。（提升效率）
2. 小剪枝：对于同一层而言，如果当前节点和前一个节点相同，则应该跳过当前节点，否则会产生重复。（防止重复+提升效率）

![](https://pic.leetcode-cn.com/1599718525-iXEiiy-image.png)

![](https://pic.leetcode-cn.com/1599716342-gGiISM-image.png)

