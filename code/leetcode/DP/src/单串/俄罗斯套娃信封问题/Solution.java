package 单串.俄罗斯套娃信封问题;

import com.sun.xml.internal.ws.api.client.WSPortInfo;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public static void main(String[] args) {
        int[][] envelopes = new int[][]{{46,89},{50,53},{52,68},{72,45},{77,81}};
        System.out.println(new Solution().maxEnvelopes(envelopes));
    }
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length==0) {
            return 0;
        }
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b){
                if(a[0]==b[0]){
                    return a[1] - b[1];
                }else {
                    return a[0] - b[0];
                }
            }
        });
//        System.out.println(Arrays.deepToString(envelopes));
        for (int i = 1; i < envelopes.length; i++) {
            int prevMaxLen = 0;
            for (int j = 0; j < i; j++) {
                if (envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]) {
                    prevMaxLen = Math.max(prevMaxLen,dp[j]);
                }
            }
            dp[i] = prevMaxLen + 1;
        }
//        System.out.println(Arrays.toString(dp));
        Arrays.sort(dp);
        return dp[dp.length - 1];
    }
}
