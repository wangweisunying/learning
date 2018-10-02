// Unique Paths
// A robot is located at the top-left corner of a m x n grid.

// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.

// How many possible unique paths are there?

// Example
// Given m = 3 and n = 3, return 6.
// Given m = 4 and n = 5, return 35.

// Notice
// m and n will be at most 100.


public class Solution {
    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        f[0][0] = 1;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0; j < n ; j++){
                if(i == 0 || j ==0){
                    f[i][j] = 1;
                    continue;
                }
                f[i][j] = f[i - 1][j] + f[i][j - 1];  
            }
        }
        return f[m - 1][n - 1];
    }
}