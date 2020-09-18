import java.util.ArrayList;

public class Solution {
    byte[] array = new byte[1*1024*1024];
    public static void main(String[] args) {
        ArrayList<Solution> list = new ArrayList<>();
        int count = 0;
        try{
            while (true){
                list.add(new Solution());
                count = count+1;
            }
        }catch (OutOfMemoryError error){
            error.printStackTrace();
        }
    }
}
