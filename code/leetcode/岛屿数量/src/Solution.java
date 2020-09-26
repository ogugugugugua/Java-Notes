//v2

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void setZero(char[][] grid, int i, int j){
        int lenX = grid.length;
        int lenY = grid[0].length;
        if(i-1>=0 && grid[i-1][j]=='1'){
            grid[i-1][j] = 0;
            setZero(grid,i-1,j);
        }
        if(j-1>=0 && grid[i][j-1]=='1'){
            grid[i][j-1] = 0;
            setZero(grid,i,j-1);
        }
        if(i+1<lenX && grid[i+1][j]=='1'){
            grid[i+1][j] = 0;
            setZero(grid,i+1,j);
        }
        if(j+1<lenY && grid[i][j+1]=='1'){
            grid[i][j+1] = 0;
            setZero(grid,i,j+1);
        }
    }
    public static int solve (char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int res = 0;
        int lenX = grid.length;
        int lenY = grid[0].length;
        for(int x = 0; x<lenX; x++){
            for(int y = 0; y<lenY; y++){
                if(grid[x][y]=='1'){
                    res++;
                    grid[x][y]= '0';                                //当前点清零
                    Queue<Integer> queue = new LinkedList<>();      //使用队列能够避免进行递归操作
                    //element = x*lenY+y ; x = element/lenY; y = element%lenY;
                    queue.offer(x*lenY+y);
                    while (!queue.isEmpty()) {
                        int n = queue.poll();
                        int i = n / lenY;
                        int j = n % lenY;
//                        grid[i][j] = '0';                         //清零的操作如果在这里进行就会导致重复地把同一个压进队列，导致运行时间暴增
                        if (i > 0 && grid[i-1][j] == '1') {
                            grid[i-1][j] = '0';                     //清零的操作应该直接在这里进行
                            queue.add((i - 1) * lenY + j);
                        }
                        if (i < lenX - 1 && grid[i+1][j] == '1') {
                            grid[i+1][j] = '0';
                            queue.add((i + 1) * lenY + j);
                        }
                        if (j > 0 && grid[i][j-1] == '1') {
                            grid[i][j-1] = '0';
                            queue.add(i * lenY + j - 1);
                        }
                        if (j < lenY - 1 && grid[i][j+1] == '1') {
                            grid[i][j+1] = '0';
                            queue.add(i * lenY + j + 1);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] grid ={{'1','1','0','0','0'},{'0','1','0','1','1'},{'0','0','0','1','1'},{'0','0','0','0','0'},{'0','0','1','1','1'}};
        System.out.println(solve(grid));
    }
}
