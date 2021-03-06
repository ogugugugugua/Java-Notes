# [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)

> 难度 简单

给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

**示例:**

```
输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

**进阶:**

如果你已经实现复杂度为 O(*n*) 的解法，尝试使用更为精妙的分治法求解。

---

一个数组有很多个子数组，求哪个子数组的和最大。可以按照子数组的最后一个元素来分子问题，确定子问题后设计状态`dp[i] := [0..i] `中，以` nums[i] `结尾的最大子数组和状态的推导是按照 `i `从 0 到 `n - 1 `按顺序推的，推到`dp[i]`，时，`dp[i - 1], ..., dp[0]` 已经计算完。因为子数组是连续的，所以子问题 `dp[i] `其实只与子问题 `dp[i - 1] `有关。如果 `[0..i-1]` 上以 `nums[i-1]` 结尾的最大子数组和(缓存在 `dp[i-1]` )为非负数，则以 `nums[i] `结尾的最大子数组和就在` dp[i-1] `的基础上加上 `nums[i] `就是` dp[i] `的结果否则以` i `结尾的子数组就不要` i-1 `及之前的数，因为选了的话子数组的和只会更小。

按照以上的分析，状态的转移可以写出来，如下

```
dp[i] = nums[i] + max(dp[i - 1], 0)
```


这个是单串 `dp[i] `的问题，状态的推导方向，以及推导公式如下![](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/08/09/2-2-1.png)

在本题中，`f(dp[i-1], ..., dp[0]) `即为` max(dp[i-1], 0) + nums[i]`，`dp[i] `仅与` dp[i-1] `1 个子问题有关。因此虽然绿色部分的子问题已经计算完，但是推导当前的橙色状态时，只需要 `dp[i-1] `这一个历史状态。

