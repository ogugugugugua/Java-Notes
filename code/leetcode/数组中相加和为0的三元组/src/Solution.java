import java.util.ArrayList;
import java.util.Collections;
//v1:  运行超时:您的程序未能在规定时间内运行结束，请检查是否循环有错或算法复杂度过大。
// 用例通过率: 0.00% 运行时间: 2001ms 占用内存: 0KB
//case通过率为0.00%
public class Solution {
    public static ArrayList<Integer> abc(int a, int b, int c) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(a); res.add(b); res.add(c);
        Collections.sort(res);

        return res;
    }
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num.length; j++) {
                for (int k = 0; k < num.length; k++) {
                    if ((num[i] + num[j] + num[k] == 0) && i != k && j != k && i!=j) {
                        ArrayList<Integer> abc = abc(num[i], num[j], num[k]);
                        if (!res.contains(abc))
                            res.add(abc);
                    }
                }
            }
        }
        return res;
    }
}
