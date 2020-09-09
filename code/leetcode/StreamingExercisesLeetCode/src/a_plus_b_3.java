import java.util.ArrayList;
import java.util.Scanner;
/**
 * 计算a+b
 * 输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
 *
 * 输出a+b的结果
 *
 * 示例输入：
 * 1 5
 * 10 20
 * 0 0
 *
 * 示例输出：
 * 6
 * 30
 */
public class a_plus_b_3 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();
        int i = 0;
        while(scanner.hasNext()){
            listA.add(scanner.nextInt());
            listB.add(scanner.nextInt());
            if (listA.get(i) == 0 && listB.get(i) == 0) {
                break;
            }
            i++;
        }
        for(int j =0;j<i;j++){
            System.out.println(listA.get(j)+listB.get(j));
        }
    }
}
