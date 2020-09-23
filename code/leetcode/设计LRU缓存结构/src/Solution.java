import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[][] operators = new int[][]{{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.LRU2(operators, 3)));
    }

    /**
     * 运行时间: 1702ms 占用内存: 164460KB
     * @param operators
     * @param k
     * @return
     */
    public int[] LRU (int[][] operators, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(k);
        HashMap<Integer, Integer> fre = new HashMap<>(k);
        int freq = 0;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0]==1){
                if (map.size()<k){
                    fre.put(operators[i][1],freq++);
                    map.put(operators[i][1], operators[i][2]);
                }else {
                    List<Map.Entry<Integer,Integer>> list = new ArrayList<>(fre.entrySet());
                    Collections.sort(list, (o1, o2) -> (o1.getValue() - o2.getValue()));
                    int least_freq = list.get(0).getKey();
                    map.remove(least_freq);
                    fre.remove(least_freq);
                    map.put(operators[i][1],operators[i][2]);
                    fre.put(operators[i][1],freq++);
                }
            } else if (operators[i][0] == 2 && map.containsKey(operators[i][1])) {
                fre.put(operators[i][1],freq++);
                result.add(map.get(operators[i][1]));
            }else
                result.add(-1);
        }

        int[] output = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }
        return output;
    }

    /**
     * 运行时间: 587ms 占用内存:  137708KB
     * @param operators
     * @param k
     * @return
     */
    public int[] LRU2 (int[][] operators, int k) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(k); //运用该数据结构，有序存储
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0]==1){
                if (map.size()<k)
                    map.remove(operators[i][1]);
                else
                    map.remove(map.keySet().toArray()[0]);
                map.put(operators[i][1],operators[i][2]);
            }else {
                if (operators[i][0] == 2 && map.containsKey(operators[i][1])) {
                    int removed = map.remove(operators[i][1]);
                    map.put(operators[i][1], removed);
                    result.add(removed);
                }else
                    result.add(-1);
            }
        }
        int[] output = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }
        return output;
    }
}
