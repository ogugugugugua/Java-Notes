public class Solution {
    public static void main(String[] args) {
        String w1 = "horse";
        String w2 = "ros";
        System.out.println(new Solution().fun(w1, w2));
    }

    public int fun(String w1, String w2){
        int n = w1.length();
        int m = w2.length();
        if (n * m == 0) {
            return 0;
        }

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i < n+1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i < m+1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m + 1; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + 1,
                           Math.min(dp[i][j - 1] + 1,
                                    dp[i - 1][j - 1] + (w1.charAt(i - 1) != w2.charAt(j - 1) ? 1 : 0)
                        ));
            }
        }
        return dp[n][m];
    }
}
