package 单串.最大子数组和系列.最大子矩阵;

public class Solution {
    public int[] getMaxMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int ansMax = Integer.MIN_VALUE;
        int[] ansArr = new int[4];
        int[][] colPreSum = new int[m][n];  // 列上的前缀和

        for (int j = 0; j < n; j++) {
            colPreSum[0][j] = matrix[0][j];
            for (int i = 1; i < m; i++) {
                colPreSum[i][j] = colPreSum[i-1][j] + matrix[i][j];
            }
        }

        int[] tmpArr = new int[n];
        for (int fromRow = 0; fromRow < m; fromRow++) {
            for (int toRow = fromRow; toRow < m; toRow++) {
                // 第fromRow行到第toRow行进行合并
                for (int col = 0; col < n; col++) {
                    tmpArr[col] = fromRow == 0 ? colPreSum[toRow][col] : colPreSum[toRow][col] - colPreSum[fromRow - 1][col];
                }

                // 求一维数组的最大子数组和
                int[] maxArrayRes = getMaxArray(tmpArr);
                int max = maxArrayRes[0];
                int maxFromCol = maxArrayRes[1];
                int maxToCol = maxArrayRes[2];

                if (max > ansMax) {
                    ansMax = max;
                    ansArr[0] = fromRow;
                    ansArr[1] = maxFromCol;
                    ansArr[2] = toRow;
                    ansArr[3] = maxToCol;
                }
            }
        }

        return ansArr;
    }
    private int[] getMaxArray(int[] nums) {
        // 求一维数组的最大子数组和
        int len = nums.length;
        int max = nums[0];
        int maxFromCol = 0;
        int maxToCol = 0;
        int fromCol = 0;
        int preMax = nums[0];
        for (int col = 1; col < len; col++) {
            if (preMax <= 0) {
                if (nums[col] > max) {
                    max = nums[col];
                    maxFromCol = col;
                    maxToCol = col;
                }
                preMax = nums[col];
                fromCol = col;
            } else {
                preMax = preMax + nums[col];
                if (preMax > max) {
                    max = preMax;
                    maxFromCol = fromCol;
                    maxToCol = col;
                }
            }
        }

        return new int[]{max, maxFromCol, maxToCol};
    }
}
