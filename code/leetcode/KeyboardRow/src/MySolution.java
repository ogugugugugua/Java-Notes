import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class MySolution {
    HashSet<Character> set1 = new HashSet();
    HashSet<Character> set2 = new HashSet();
    HashSet<Character> set3 = new HashSet();

    public void init(){
        Character[] char1 = new Character[]{'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'};
        this.set1.addAll(Arrays.asList(char1));
        char1 = new Character[]{'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'};
        this.set2.addAll(Arrays.asList(char1));
        char1 = new Character[]{'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        this.set3.addAll(Arrays.asList(char1));
    }

    public boolean inOneSet(HashSet<Character> set, String str){
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if (!set.contains(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public String[] findWords(String[] words) {
        this.init();
        ArrayList<String> result = new ArrayList<String>();
        for (String str : words) {
            if (this.inOneSet(this.set1, str) || this.inOneSet(this.set2, str) || this.inOneSet(this.set3, str)) {
                result.add(str);
            }
        }
        String[] output = (String[])result.toArray(new String[result.size()]);
        return  output;
    }

    public static void main(String[] args) {
        MySolution t = new MySolution();
        String[] input = new String[]{"Hello","Alaska", "Dad", "Peace"};
        String[] output = t.findWords(input);
        System.out.println(Arrays.toString(output));
    }
}
