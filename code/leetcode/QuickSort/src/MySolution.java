import java.util.Arrays;

public class MySolution {
    public static void sort(int[] a, int low, int high){
        int i = low, j = high;
        if (low > high) {
            return;
        }
        int temp = a[i];        //挖坑，取当前区间的第一个数为基准
        while (i < j) {
            while (i < j && a[j] >= temp) {
                j--;            //j-- 由后向前找比它小的数
            }
            if (i<j){
                a[i] = a[j];        //找到后挖出此数填前一个坑 a[i] 中
                i++;
            }
            while (i < j && a[i] < temp) {
                i++;            //i++ 由前向后找比它大的数
            }
            if (i<j){
                a[j] = a[i];        //找到后也挖出此数填到前一个坑 a[j] 中
                j--;
            }
        }
        a[i] = temp;
        sort(a, low, i-1);
        sort(a, i+1, high);
    }

    public static void quickSort(int[] a){
        sort(a, 0, a.length - 1);
    }

    public static void main(String[] args) {
        int a[] = { 49, 38, 65, 97, 76, 13, 27, 49 };
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

}
