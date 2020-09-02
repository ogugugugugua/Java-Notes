import java.util.HashSet;
import java.util.Set;

public class MySolution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> vocab = new HashSet<>();
        int maxLen = s.length() > 0 ? 1:0;
        for (int i = 0; i < s.length(); i++) {
            vocab.add(s.charAt(i));
            for (int j = i+1; j < s.length(); j++) {
                if (!vocab.contains(s.charAt(j))){
                    vocab.add(s.charAt(j));
                }
                else {
                    maxLen = Math.max(maxLen, vocab.size());
                    vocab.clear();
                    break;
                }
            }
        }
        maxLen = Math.max(maxLen, vocab.size());
        return maxLen;
    }

}
