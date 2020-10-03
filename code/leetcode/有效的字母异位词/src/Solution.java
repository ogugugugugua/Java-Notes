public class Solution {
    public static void main(String[] args) {
        String s = "rat", t = "tar";
        System.out.println(new Solution().isAnagram(s, t));
    }
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] S = s.toCharArray();
        char[] T = t.toCharArray();
        int[] arr = new int[256];
        for (char c : S) {
            arr[c]++;
        }
        for (char c : T) {
            if (--arr[c] < 0) {
                return false;
            }
        }
        return true;
    }
}
