/**
 * 这个解法把子集的遍历放到了findKth1函数里面，稍微有点蛋疼
 */
public class SolutionModified {
    public static int sort(int[] a, int low, int high){
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
        return i;
    }
    public static int findKth1(int[] a, int low, int high, int k){
        int part = sort(a,low, high);
        if(k-1==part-low){
            return a[part];
        }
        if(k-1 > part - low){
            return findKth1(a,part+1,high,k-(part+1-low));
        }
        if(k-1 < part - low){
            return findKth1(a,low,part-1,k);
        }
        return -1;
    }
    public static int findKth(int[] a, int n, int K) {
        return findKth1(a,0,n-1,K);
    }

    public static void main(String[] args) {
        int[] a = {1,3,5,2,2};
        System.out.println(findKth(a, 5, 3));
    }
}
