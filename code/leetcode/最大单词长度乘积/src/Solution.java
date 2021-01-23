import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        String[] words = new String[]{"abcw","baz","fo","bar","xtfn","abcdef"};
        maxProduct(words);
    }

    public static boolean overlap(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
        }
        return true;
    }
    public static int maxProduct(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        int maxLen = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {

            }
        }
        return 0;
    }
}
