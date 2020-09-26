//v1: 请检查是否存在数组越界等非法访问情况 用例通过率: 93.75% 运行时间: 130ms 占用内存: 21952KB
//case通过率为93.75%
//Exception in thread "main" java.lang.StackOverflowError
//at Solution.setZero(Solution.java:15)
//思路和代码都没错，本地运行成功，但是OJ的系统出现了StackOverflowError

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
        int res = 0;
        int lenX = grid.length;
        int lenY = grid[0].length;
        for(int x = 0; x<lenX; x++){
            for(int y = 0; y<lenY; y++){
                if(grid[x][y]=='1'){
                    res++;
                    setZero(grid, x, y);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[][] grid ={{1,1,0,0,0},{0,1,0,1,1},{0,0,0,1,1},{0,0,0,0,0},{0,0,1,1,1}};
        System.out.println(solve(grid));
    }
}
