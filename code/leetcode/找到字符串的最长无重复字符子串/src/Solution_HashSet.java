import java.util.HashSet;

public class Solution_HashSet {
    public int maxLength (int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        int i=0,j=0, n=arr.length, result=1;
        while(i<n &&j<n){
            if(!set.contains(arr[j])){
                set.add(arr[j++]);
                result = Math.max(result, j-i);
            }else{
                set.remove(arr[i++]);
            }
        }
        return result;
    }
}
