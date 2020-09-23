import java.util.*;


public class Solution1 {
    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
    解题思路：采用二分查找的思想解决问题，首先用两个指针标记左、右，然后用mid所指的位置与关键字比较。
    若小于关键字，则在其右侧继续二分查找；
    若大于等于关键字，且其左邻元素也大于等于该关键字，则继续在mid的左侧二分查找，
    否则输出mid+1.若遍历后不存在这样的值，输出n+1。
     */
    public int upper_bound_ (int n, int v, int[] a) {
        int l = 0, r = n;
        while(l < r) {
            int mid = l + (r - l) / 2;
            if(a[mid] < v) l = mid + 1;
            else r = mid;
        }
        return l + 1;
    }

}