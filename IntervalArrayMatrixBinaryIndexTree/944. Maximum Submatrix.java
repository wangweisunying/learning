
// 944. Maximum Submatrix
// Given an n x n matrix of positive and negative integers, find the submatrix with the largest possible sum.

// Example
// Given matrix = 
// [
// [1,3,-1],
// [2,3,-2],
// [-1,-2,-3]
// ]
// return 9.
// Explanation:
// the submatrix with the largest possible sum is:
// [
// [1,3],
// [2,3]
// ]


public class Solution {
    /**
     * @param matrix: the given matrix
     * @return: the largest possible sum
     */
    public int maxSubmatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return 0;
        }

        int res = Integer.MIN_VALUE;
        int m = matrix.length , n = matrix[0].length;
        int[][] sumMatrix = new int[m + 1][n];
        for(int i = 1 ; i <= m ; i ++){
          for(int j = 0 ; j < n ; j++){
            sumMatrix[i][j] = matrix[i - 1][j] + sumMatrix[i - 1][j]; 
          }
        }

        for(int i = 0 ; i <= m ; i ++){
          for(int j = i + 1 ;  j <= m ; j++){
            int[] tmp = new int[n];
            for(int k = 0 ; k < n ; k++){
              tmp[k] = sumMatrix[j][k] - sumMatrix[i][k];
            }
            res = Math.max(res , findMax(tmp));
          }
        }
        return res;
    }
    

    private int findMax(int[] nums){
      int res = Integer.MIN_VALUE;
      int sum = 0;
      for(int i = 0 ; i < nums.length ; i++){
        sum += nums[i];
        sum = Math.max(sum , 0);
        res = Math.max(sum , res);
      }
      return res;
    }
}