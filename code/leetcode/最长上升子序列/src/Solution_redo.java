/**
 * For practice purpose
 * 第一次重做练习：2020/09/30
 */
public class Solution_redo {
    public static void main(String[] args) {
        int[] arr = new int[]{-1,2,0,1,6,8,7,9,2,3,4,5,6,7,10};
        System.out.println(LTS(arr));
    }

    public static int LTS(int[] arr) {
        int[] dp = new int[arr.length];
        int maxLen = 1;
        for (int i = 0; i < arr.length; i++) {
            int maxSmallerVal_LTS = 0; //最大的小于当前值的元素 所对应的最长子序列长度
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    maxSmallerVal_LTS = Math.max(maxSmallerVal_LTS, dp[j]);
                }
            }
            dp[i] = maxSmallerVal_LTS + 1;  //当前元素对应最长子序列的长度 是 前面最长子序列(该序列最大值小于当前值) 的长度+1
            maxLen = Math.max(maxLen,dp[i]);    //更新最大值
        }
        return maxLen;
    }
}
