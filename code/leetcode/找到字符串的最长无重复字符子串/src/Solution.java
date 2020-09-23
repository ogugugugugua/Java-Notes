import java.util.HashSet;

/**
 * 此算法效率很低
 */
public class Solution {
    public static int maxLength (int[] arr) {
        int max = 0;
        int tempMax = 0;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i<arr.length;i++){
            set.add(arr[i]);
            tempMax = 0;
            for(int j = i+1; j<arr.length; j++){
                if(!set.contains(arr[j])){
                    set.add(arr[j]);
                    tempMax++;
                }else{
                    max = Math.max(max, tempMax+1);
                    break;
                }
            }
            max = Math.max(max, tempMax+1);
            set.clear();
        }
        return max;
    }

    public static void main(String[] args) {
        int[] input = {2,2,3,4,5,3};
        System.out.println(maxLength(input));
    }
}
