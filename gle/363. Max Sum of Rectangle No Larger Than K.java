// 363. Max Sum of Rectangle No Larger Than K

// Share
// Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

// Example:

// Input: matrix = [[1,0,1],[0,-2,3]], k = 2
// Output: 2 
// Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
//              and 2 is the max number no larger than k (k = 2).
// Note:

// The rectangle inside the matrix must have an area > 0.
// What if the number of rows is much larger than the number of columns?


class Solution {

    // 将每行、列的sum 2d 转一维暴力计算
    public int maxSumSubmatrix(int[][] matrix, int g) {
        int m = matrix.length , n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for(int i = 0 ; i < m ; i++){
            int[] arr = new int[n];
            for(int j = i ; j < m ;j++){
                for(int k = 0 ; k < n ; k++){
                    arr[k] += matrix[j][k];
                }
                res = Math.max(res , findMaxSub(arr , g));
            }
            
        }
        return res;
    }

    //preSum 穷举
    private int findMaxSub(int[] nums , int g){
        int max = Integer.MIN_VALUE;
        int[] preSum = new int[nums.length + 1];
        for(int i = 1; i <= nums.length ; i++ ){
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        for(int i = 1; i <= nums.length ; i++ ){
            for(int j = 0 ; j < i ; j++){
                if(preSum[i] - preSum[j] <= g ){
                    max = Math.max(max ,preSum[i] - preSum[j]);
                }
            }
        }
        return max;
    }
}
