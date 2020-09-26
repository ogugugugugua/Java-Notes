import java.util.ArrayList;
import java.util.Arrays;

//v2: 答案正确:恭喜！您提交的程序通过了所有的测试用例 用例通过率: 100.00% 运行时间: 603ms 占用内存: 46504KB
public class Solution {
    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(num);                                   //先对数组进行从小到大的排序，方便后面操作
        for (int i = 0; i < num.length - 2; i++) {
            if (i>0 && num[i]==num[i-1]) continue;          //这一行用于避免重复的三元组
            int j = i + 1;                                  //从i的右边第一位开始往右遍历
            int k = num.length - 1;                         //从数组的最右边开始往左遍历
            while (j < k) {                                 //保证对当前的i，所有满足条件的组合都找出来
                if (num[i] + num[k] + num[j] == 0) {        //符合条件
                    res.add(new ArrayList<>(Arrays.asList(num[i], num[j], num[k])));    //添加进结果集
                    j++;                                    //j指针往右走
                    k--;                                    //k指针往左走  （由于是有序数组，所以这样一起移动是不会遗漏的）
                    while (j < k && num[j] == num[j - 1]) { //排除重复的情况
                        j++;
                    }
                    while (j < k && k+1<num.length && num[k] == num[k + 1]) {   //排除重复的情况，而且要确保k不会越界
                        k++;
                    }
                } else if (num[i] + num[k] + num[j] < 0) {  //和小于0，证明需要把指针往大的方向移动，所以j++
                    j++;
                } else {
                    k--;                                    //和大于0，证明需要把指针往小的方向移动，所以k--
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num = {-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6};
        System.out.println(threeSum(num));
    }
}