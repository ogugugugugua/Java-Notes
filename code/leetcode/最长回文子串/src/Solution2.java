public class Solution2 {
    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("baabccc",7));;
    }
    public static int getLongestPalindrome(String A, int n) {
        int left = 0, right = 0;
        int maxLen = 0;
        for (int i = 1; i < n; i++) {

            //str长度为奇数
            left = i-1;
            right = i+1;
            while (left >= 0 && right < n && A.charAt(left) == A.charAt(right)) {
                maxLen = Math.max(maxLen, right - left + 1);
                left--;
                right++;
            }

            //str长度为偶数
            left = i-1;
            right = i;
            while (left >= 0 && right < n && A.charAt(left) == A.charAt(right)) {
                maxLen = Math.max(maxLen, right - left + 1);
                left--;
                right++;
            }
        }
        return maxLen;
    }
}
