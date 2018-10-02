
// k Sum
// Given n distinct positive integers, integer k (k <= n) and a number target.

// Find k numbers where sum is target. Calculate how many solutions there are?

// Example
// Given [1,2,3,4], k = 2, target = 5.

// There are 2 solutions: [1,4] and [2,3].

// Return 2.


public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] nums, int k, int target) {
        int n = nums.length;
        int[][][] f = new int[n + 1][k + 1][target + 1];
        
        //init
        for(int i = 0 ; i <= n ; i++){
            f[i][0][0] = 1;
        }
    
        for(int i = 1; i <= n ; i++ ){
            for(int j = 1 ; j<= i && j <= k ;j ++){
                for(int t = 1 ; t <= target ; t++){
                    if(t - nums[i - 1] >= 0 ){ //select
                        f[i][j][t] = f[i - 1][j - 1][t - nums[i - 1]];
                    }
                    f[i][j][t] += f[i - 1][j][t]; //dont select
                }
                
            }
        }
        return f[n][k][target];
    
    }
}