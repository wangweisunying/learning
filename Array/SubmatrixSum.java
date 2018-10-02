// Submatrix Sum
// Given an integer matrix, find a submatrix where the sum of numbers is zero. Your code should return the coordinate of the left-up and right-down number.

// Example
// Given matrix

// [
//   [1 ,5 ,7],
//   [3 ,7 ,-8],
//   [4 ,-8 ,9],
// ]
// return [(1,1), (2,2)]

// Challenge
// O(n3) time.

public class Solution {
    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
// [[1,1,1,1,1,1,1,1,1,1,1,-10,1,1,1,1,1,1,1,1,1,1,1]]
    public int[][] submatrixSum(int[][] matrix) {
        int[][] res = new int[2][2];
        int m = matrix.length;
        if(m == 0){
            return res;
        } 
        int n = matrix[0].length;
        if(n == 0){
            return res;
        }
        
        //build the preSum matrix
        int[][] preSum = new int[m + 1][n + 1];
        
        for(int i = 1; i <= m ;i++){
            for(int j = 1 ; j <= n ; j++){
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }

        for(int s = 0 ; s < m ; s++){
            for(int e = s + 1 ; e<= m ; e++ ){
                HashMap<Integer ,Integer > map = new HashMap();
                for(int h = 0 ; h <= n ; h++){
                    int diff = preSum[e][h] - preSum[s][h];
                    if(map.containsKey(diff)){
                        int k = map.get(diff);
                        res[0][0] = s ;
                        res[0][1] = k ;
                        res[1][0] = e - 1;
                        res[1][1] = h - 1;
                        return res;
                    }
                    else{
                        map.put(diff , h);
                    }
                }
            }
        }
        return res;
    }
}


public class Solution {
    /**
     * @param matrix an integer matrix
     * @return the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
        int[][] result = new int[2][2];
        int M = matrix.length;
        if (M == 0) return result;
        int N = matrix[0].length;
        if (N == 0) return result;
        // pre-compute: sum[i][j] = sum of submatrix [(0, 0), (i, j)]
        int[][] sum = new int[M+1][N+1];
        for (int j=0; j<=N; ++j) sum[0][j] = 0;
        for (int i=1; i<=M; ++i) sum[i][0] = 0;
        for (int i=0; i<M; ++i) {
            for (int j=0; j<N; ++j)
                sum[i+1][j+1] = matrix[i][j] + sum[i+1][j] + sum[i][j+1] - sum[i][j];
        }
        for (int l=0; l<M; ++l) {
            for (int h=l+1; h<=M; ++h) {
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int j=0; j<=N; ++j) {
                    int diff = sum[h][j] - sum[l][j];
                    if (map.containsKey(diff)) {
                        int k = map.get(diff);
                        result[0][0] = l;   result[0][1] = k;
                        result[1][0] = h-1; result[1][1] = j-1;
                        return result;
                    } else {
                        map.put(diff, j);
                    }
                }
            }
        }
        return result;
    }
}