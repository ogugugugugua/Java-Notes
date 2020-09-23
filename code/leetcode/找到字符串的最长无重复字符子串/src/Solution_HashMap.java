import java.util.HashMap;
import java.util.LinkedHashMap;

public class Solution_HashMap {
    public int maxLength (int[] arr) {
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        int length = 0;
        int temp = 0;
        for(int i =0;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],i);
                temp++;
            }else{
                i = map.get(arr[i])+1;
                map.clear();
                length = Math.max(length,temp);
                temp = 1;
            }
        }
        return Math.max(length,temp);
    }
}
