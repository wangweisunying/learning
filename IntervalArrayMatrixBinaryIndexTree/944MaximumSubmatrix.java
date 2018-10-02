// MaximumSubmatrix
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
// [1,2],
// [2,3]
// ]


public class Solution {
    /**
     * @param matrix: the given matrix
     * @return: the largest possible sum
     */
    public int maxSubmatrix(int[][] matrix) {
        // write your code here
    }
}
































// travese the column  form all the 1 d arry n do the maxsubarray
public class Solution {
    /**
     * @param matrix: the given matrix
     * @return: the largest possible sum
     */
    public int maxSubmatrix(int[][] matrix) {
        
        if (matrix == null || matrix.length == 0) {
          return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
          return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] colSum = new int[m + 1][n];
        
        for(int i = 0 ; i < m ; i ++){
            for(int j = 0 ; j < n ;j ++){
                colSum[i + 1][j] = colSum[i][j] + matrix[i][j];
            }
        }
        int max = Integer.MIN_VALUE;
        for(int u = 0 ; u < m ; u++){
            for(int d = u + 1 ; d < m + 1 ; d ++){
                int[] curArr = compress(colSum , u , d);
                int curMax = maxSubArray(curArr);
                max = Math.max(max , curMax);
            }
        }
        return max;
    }
    private int maxSubArray(int[] curArr){
        
        
        int max = curArr[0];
        int curMax = curArr[0];
        for(int i = 1 ; i < curArr.length ; i ++){
            curMax = Math.max(curMax + curArr[i] , curArr[i]);
            max = Math.max(curMax , max);
        }
        return max;
    }
    private int[] compress(int[][] colSum , int  u , int d){
        int[] res = new int[colSum[0].length];
        for(int i = 0 ; i < res.length ; i ++){
            res[i] = colSum[d][i] - colSum[u][i]; 
        }
        return res;
    }
}








public class Solution {
  int n, m;
  
  /**
   * @param matrix: the given matrix
   * @return: the largest possible sum
   */
  public int maxSubmatrix(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    if (matrix[0] == null || matrix[0].length == 0) {
      return 0;
    }
    
    n = matrix.length;
    m = matrix[0].length;
    
    int[][] prefixColSum = getPrefixColSum(matrix);
    
    int max = Integer.MIN_VALUE;
    for (int up = 0; up < n; up++) {
      for (int down = up; down < n; down++) {
        int[] arr = compression(matrix, up, down, prefixColSum);
        max = Math.max(max, maximumSubarray(arr));
      }
    }
    
    return max;
  };
  
  private int maximumSubarray(int[] arr) {
    int min = 0, max = Integer.MIN_VALUE, sum = 0;
    
    for (int i = 0; i < m; i++) {
      sum += arr[i];
      max = Math.max(max, sum - min);
      min = Math.min(min, sum);
    }
    
    return max;
  }
  
  private int[] compression(int[][] matrix, int up, int down, int[][] prefixColSum) {
    int[] arr = new int[m];
    for (int i = 0; i < m; i++) {
      arr[i] = prefixColSum[down + 1][i] - prefixColSum[up][i];
    }
    
    return arr;
  }
  
  private int[][] getPrefixColSum(int[][] matrix) {
    int[][] sum = new int[n + 1][m];
    
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        sum[i + 1][j] = sum[i][j] + matrix[i][j];
      }
    }
    
    return sum;
  }
}