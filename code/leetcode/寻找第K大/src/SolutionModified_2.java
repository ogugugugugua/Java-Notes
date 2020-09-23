/**
 * 这个解法完全就是经典的快速排序了，由于有一些不需要排序的子集也进行了排序，所以会相对前两个方法更加耗时和耗空间
 */
public class SolutionModified_2 {
    public static void sort(int[] a, int low, int high){
        if(low>high) return;
        int i = low, j = high, index = a[i];
        while(i<j){
            while(a[j]<index && i<j){
                j--;
            }
            if(i<j){
                a[i] = a[j];
                i++;
            }
            while(a[i]>index && i<j){
                i++;
            }
            if(i<j){
                a[j] = a[i];
                j--;
            }
        }
        a[i] = index;
        sort(a,low,i-1);
        sort(a,i+1,high);
    }
    public static void quickSort(int[] a, int n){
        sort(a, 0, n-1);
    }
    public int findKth(int[] a, int n, int K) {
        quickSort(a,n);
        return a[K-1];
    }
}
