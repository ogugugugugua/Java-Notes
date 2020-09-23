/**
 * 这个更容易理解，和普通的
 */
public class SolutionModified_1 {
    public static int sort(int[] a, int low, int high, int k){
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
        a[i] = index;   //其实在这里就可以知道：index的值处于数组的第i+1大。因为前面的第
        if(i<k-1){
            return sort(a, i+1, high, k);
        }else if(i>k-1){
            return sort(a, low, i-1, k);
        }else{          //i==k-1，即index的值处于数组的第i+1大，返回即可
            return a[i];
        }
    }

    public int findKth(int[] a, int n, int K) {
        return sort(a,0,n-1,K);
    }
}
