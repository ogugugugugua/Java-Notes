package 面试准备;

public class twinNumbers {
    public static boolean verify(int n){
        if (n==2) return true;
        for (int i = 2; i < n; i++) {
            if (n%i==0)
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        int n = 100000000;
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (verify(i)&&verify(i+2))
                size++;
        }
        System.out.println(size);
    }
}
