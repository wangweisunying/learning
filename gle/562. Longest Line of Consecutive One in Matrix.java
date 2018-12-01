// 562. Longest Line of Consecutive One in Matrix
// DescriptionHintsSubmissionsDiscussSolution
// Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
// Example:
// Input:
// [[0,1,1,0],
//  [0,1,1,0],
//  [0,0,0,1]]


// [[0,1,1,0],
//  [0,1,1,0],
//  [0,0,0,1]]
// Output: 3
// Hint: The number of elements in the given matrix will not exceed 10,000.
//from the dataset knowing bfs or brute force is impossible! 
// dp from brute force  to dp (trying to record everything  I visited )
class Solution {
    public int longestLine(int[][] M) {
        if(M == null || M.length == 0){
            return 0;
        }
        int m = M.length;
        int n = M[0].length;
        int[][][] f = new int[m][n][4];

        int max = 0 ;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                if(M[i][j] == 1){
                    f[i][j][0] = i - 1 >= 0 ? (f[i - 1][j][0] + 1) : 1;
                    f[i][j][1] = j - 1 >= 0 ? (f[i][j - 1][1] + 1) : 1;
                    f[i][j][2] = (i - 1 >= 0 && j - 1 >= 0) ? (f[i - 1][j - 1][2] + 1) : 1;
                    f[i][j][3] = (i - 1 >= 0 && j + 1 < n ) ? (f[i - 1][j + 1][3] + 1) : 1;
                    for(int k = 0 ; k < 4 ; k++){
                        max = Math.max(max , f[i][j][k]);
                    }
                }
            }
        }
        return max;
    }
}