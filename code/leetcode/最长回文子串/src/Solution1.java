import java.util.Arrays;

public class Solution1 {
    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("baabccc",7));;
    }
    public static int getLongestPalindrome(String A, int n) {
        int maxLen = 1;
        boolean[][] lps = new boolean[n][n];
        /////////////////////////////////////////
        for (int right = 0; right < n; right++) {
            for (int left = 0; left <= right; left++) {
                if (right - left < 2) {
                    lps[left][right] = (A.charAt(left) == A.charAt(right));
                } else {
                    if (lps[left + 1][right - 1] && A.charAt(left) == A.charAt(right)) {
                        lps[left][right] = true;
                        maxLen = Math.max(maxLen, (right - left + 1));
                    }
                }
            }
        }
        return maxLen;
    }
}
