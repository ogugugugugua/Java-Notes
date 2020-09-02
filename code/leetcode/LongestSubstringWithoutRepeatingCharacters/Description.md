Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
             
---
解法：

If a substring s_{ij} from index i to j−1 is already checked to have no duplicate characters. We only need to check if s[j] is already in the substring s_{ij}

By using HashSet as a sliding window, checking if a character in the current can be done in O(1).

