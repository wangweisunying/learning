// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

// Note: You can only move either down or right at any point in time.

// Example:

// Input:
// [
//   [1,3,1],
//   [1,5,1],
//   [4,2,1]
// ]
// Output: 7
// Explanation: Because the path 1→3→1→1→1 minimizes the sum.

//f[i][j] = Math.min(f[i - 1][j] , f[i][j - 1]) + grid[i][j]; 
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] f = new int[m][n];
        
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(i == 0 && j == 0){
                    f[i][j] = grid[i][j];
                    continue;
                }
                if(i == 0){
                    f[i][j] = f[i][j - 1] + grid[i][j];
                    continue;
                }
                if(j == 0){
                    f[i][j] = f[i - 1][j] + grid[i][j];
                    continue;
                }
                f[i][j] = Math.min(f[i - 1][j] , f[i][j - 1]) + grid[i][j];
            }
        }
        return f[m - 1][n - 1];

    }
}