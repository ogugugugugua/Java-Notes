Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].



---
https://leetcode.com/problems/two-sum/solution/

解法：

由于这里我们需要获取指定元素的索引，除了传统的遍历一次数组之外，我们还可以使用HashMap<>()来加快索引速度，以空间换时间。We reduce the look up time from O(n) to O(1) by trading space for speed. A hash table is built exactly for this purpose, it supports fast look up in near constant time. I say "near" because if a collision occurred, a look up could degenerate to O(n) time. But look up in hash table should be amortized O(1) time as long as the hash function was chosen carefully.

