// 410. Split Array Largest Sum

// Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

// Note:
// If n is the length of array, assume the following constraints are satisfied:

// 1 ≤ n ≤ 1000
// 1 ≤ m ≤ min(50, n)
// Examples:

// Input:
// nums = [7,2,5,10,8]
// m = 2

// Output:
// 18

// Explanation:
// There are four ways to split nums into two subarrays.
// The best way is to split it into [7,2,5] and [10,8],
// where the largest sum among the two subarrays is only 18.


class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for(int i = 1 ; i <= n ; i++) preSum[i] = preSum[i - 1] + nums[i - 1];
        int[][] memo = new int[n + 1][m];
        for(int[] x : memo) Arrays.fill(x , -1);
        return recursion(preSum , memo , m - 1 , n );
    }
    private int recursion(int[] preSum , int[][] memo , int m , int n){
        if(m == 0) return preSum[n] - preSum[0];
        if(memo[n][m] != -1) return memo[n][m];
        int res = Integer.MAX_VALUE;
        for(int i = 1 ; i < n ; i++){
            res = Math.min(res , Math.max(recursion(preSum , memo , m - 1 , i) , preSum[n] - preSum[i]));
        }
        memo[n][m] = res;
        return res;
    }
}