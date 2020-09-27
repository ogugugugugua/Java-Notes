import java.util.HashMap;
//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果

public class Solution {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer,Integer> map = new HashMap<>();
        boolean inNum1 = true;
        for(int i=0;i<array.length;i++){
            if(!map.containsKey(array[i])){
                map.put(array[i],1);
            }else{
                map.put(array[i],2);
            }
        }
        for(Integer key: map.keySet()){
            if(map.get(key).equals(1)){
                if(inNum1){
                    num1[0] = key;
                    inNum1 = false;
                }else{
                    num2[0] = key;
                    break;
                }
            }
        }
    }
}
