import java.util.ArrayList;
import java.util.Scanner;
/**
 * 计算a+b
 * 输入的第一行包括一个正整数t(1 <= t <= 100), 表示数据组数。
 * 接下来t行, 每行一组数据。
 * 每行的第一个整数为整数的个数n(1 <= n <= 100)。
 * 接下来n个正整数, 即需要求和的每个正整数。
 *
 * 输出a+b的结果
 *
 * 示例输入：
 * 2
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 *
 * 示例输出：
 * 10
 * 15
 */
public class a_plus_b_5 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        ArrayList<Integer> result = new ArrayList<>();
        for(int i =0;i<t;i++){
            int n = scanner.nextInt();
            int sum = 0;
            for(int j =0;j<n;j++){
                sum += scanner.nextInt();
            }
            result.add(sum);
        }
        for (Integer integer : result) {
            System.out.println(integer);
        }
    }

}
