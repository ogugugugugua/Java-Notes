import java.util.ArrayList;
import java.util.Scanner;
/**
 * 计算a+b
 * 输入第一行包括一个数据组数t(1 <= t <= 100)
 * 接下来每行包括两个正整数a,b(1 <= a, b <= 10^9)
 *
 * 输出a+b的结果
 *
 * 示例输入：
 * 2
 * 1 5
 * 10 20
 *
 * 示例输出：
 * 6
 * 30
 */
public class a_plus_b_2 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();
        for(int i =0;i<t;i++){
            listA.add(scanner.nextInt());
            listB.add(scanner.nextInt());
        }
        for(int i =0;i<t;i++){
            System.out.println(listA.get(i)+listB.get(i));
        }
    }

    public static void main2(String[] args){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] listA = new int[t];
        int[] listB = new int[t];
        for(int i =0;i<t;i++){
            listA[i] = scanner.nextInt();
            listB[i] = scanner.nextInt();
        }
        for(int i =0;i<t;i++){
            System.out.println(listA[i]+listB[i]);
        }
    }
}
