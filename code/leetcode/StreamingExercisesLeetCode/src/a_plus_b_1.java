import java.util.ArrayList;
import java.util.Scanner;

/**
 * 计算a+b
 * 输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据包括多组。
 * 输出a+b的结果
 *
 * 示例输入：
 * 1 5
 * 10 20
 *
 * 示例输出：
 * 6
 * 30
 */
public class a_plus_b_1 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();
        while(scanner.hasNext()){
            listA.add(scanner.nextInt());
            listB.add(scanner.nextInt());
        }
        for(int i=0;i<listA.size();i++){
            System.out.println(listA.get(i)+listB.get(i));
        }
    }
}
