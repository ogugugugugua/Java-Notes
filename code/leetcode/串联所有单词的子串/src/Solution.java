import java.util.*;

public class Solution {
    public boolean checkStart(Collection<Integer> map, int len){
        int sum = 0;
        for (Integer i : map) {
            sum += i;
        }
        return sum == len-1;
    }

    public boolean allChecked(Map<String, Integer> map) {
        for (Integer i : map.values()) {
            if (i!=0) {
                return false;
            }
        }
    }
    public List<Integer> findSubstring(String s, String[] words) {
        int left = 0, right = 0, start = 0;
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word: words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }


        while (right < s.length()) {
            String w = s.substring(left,right);
            right++;
            if (map.containsKey(w)){
                if (map.get(w) == 1){
                    map.remove(w);
                }else {
                    map.put(w, map.get(w)-1);
                }
                start = (this.checkStart(map.values(), words.length)) ? left : start;
                left = right-1;
            }
        }

        for (Integer i : map.values()) {
            if (i!=0) {
                return new ArrayList<>();
            }
        }
        result.add(start);
        return result;
    }

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo","bar"};
        System.out.println(new Solution().findSubstring(s, words));
    }
}
