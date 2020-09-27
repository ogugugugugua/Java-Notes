//二分法

public class Solution {
    public static int sqrt(int x) {
        int left = 1;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return mid;
            } else if (mid * mid < x) {
                left = mid + 1;
            }else if (mid * mid >x){
                right = mid - 1;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        System.out.println(sqrt(2));
    }
}