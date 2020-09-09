import java.util.ArrayList;
import java.util.Scanner;
/**
 * 计算a+b
 * 输入数据包括多组。
 * 每组数据一行,每行的第一个整数为整数的个数n(1 <= n <= 100), n为0的时候结束输入。
 * 接下来n个正整数,即需要求和的每个正整数。
 *
 * 输出a+b的结果
 *
 * 示例输入：
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 * 0
 *
 * 示例输出：
 * 10
 * 15
 */
public class a_plus_b_4 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> result = new ArrayList<>();
        while(scanner.hasNext()){
            int n = scanner.nextInt();
            if(n==0){
                break;
            }
            int sum = 0;
            for(int i =0;i<n;i++){
                sum += scanner.nextInt();
            }
            result.add(sum);
        }
        for (Integer integer : result) {
            System.out.println(integer);
        }
    }
}
