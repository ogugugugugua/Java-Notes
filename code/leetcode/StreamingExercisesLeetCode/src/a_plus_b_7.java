import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 计算a+b
 *
 * 输入数据有多组, 每行表示一组输入数据。
 * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
 *
 * 输出a+b的结果
 *
 * 示例输入：
 * 1 2 3
 * 4 5
 * 0 0 0 0 0
 *
 * 示例输出：
 * 6
 * 9
 * 0
 */
public class a_plus_b_7 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                String line = reader.readLine();
                if (line == null) {
                    reader.close();
                    break;
                }
                String[] strings = line.trim().split(" ");
                int sum = 0;
                for (int i = 0;i<strings.length;i++) {
                    sum += Integer.parseInt(strings[i]);
                }
                System.out.println(sum);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
