import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

// not working
public class Solution {
    public static void main(String[] args) {
        int[] arr = {1,2,8,6,4};
        int[] res = LIS(arr);
        System.out.println(Arrays.toString(res));
    }
    public static int[] LIS (int[] arr) {
        int[] vec = new int[arr.length];
        int[] maxLen = new int[arr.length];
        int index = 0;

        for (int i = 0; i < vec.length; i++) {
            vec[i] = Integer.MIN_VALUE;
        }
        vec[0] = arr[0];
        maxLen[0] = 1;

        for (int i = 1; i < arr.length; i++) {
            index = 0;
            if (arr[i] >= vec[i - 1]) {
                vec[i] = arr[i];
                maxLen[i] = maxLen[i - 1] + 1;
            }else {
                //在vec数组保存了的递增元素中寻找第一个比当前元素大的那个元素
                while (index < vec.length) {
                    if (vec[index] > arr[i]) {
                        break;
                    }
                    index++;
                }
                vec[index] = arr[i];        //用当前元素替换
                maxLen[i] = maxLen[index];  //最大长度与之前一样
            }
        }
        System.out.println(Arrays.toString(maxLen));

        //TODO
        int[] res = new int[Arrays.stream(maxLen).max().getAsInt()];
        int position = res.length;
        for (int i = maxLen.length-1; i >0 ; i--) {
            if (maxLen[i] == position) {
                position--;
                res[position] = arr[i];
            }
            if (position==0)
                break;
        }
        return res;
    }
}
