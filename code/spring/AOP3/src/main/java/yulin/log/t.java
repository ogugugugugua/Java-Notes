package yulin.log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class t {
    public static void main(String[] args) {
        t t1 = new t();
        System.out.println(t1.reverseWords("a good   example"));;
    }
    public String reverseWords(String s) {
        s = s.trim();
        List<String> stringList = Arrays.asList(s.split("\\+"));
        Collections.reverse(stringList);
        return String.join(" ",stringList);
    }
}
