// 221. Maximal Square
// DescriptionHintsSubmissionsDiscussSolution
// Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

// Example:

// Input: 

// 1 0 1 0 0
// 1 0 1 1 1
// 1 1 1 1 1
// 1 0 0 1 0

// Output: 4


// dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1.


class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] f = new int[m][n];
        int max = 0 ;
        for(int i = 0 ; i < m ; i ++ ){
            for(int j = 0 ; j < n ; j++){
                if(matrix[i][j] == '1'){
                    if(i == 0 & j == 0){
                        f[i][j] = 1;
                    }
                    else if(i == 0){
                        f[i][j] = 1;
                    }
                    else if(j == 0){
                        f[i][j] = 1;
                    }
                    else{
                        f[i][j] = Math.min(Math.min(f[i - 1][j - 1] , f[i][j - 1]) , f[i - 1][j]) + 1;
                    }
                    max = Math.max(max , f[i][j] * f[i][j]);
                }
            }
        }
        return max;
    }
}
// [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]

// 16;
// 4;