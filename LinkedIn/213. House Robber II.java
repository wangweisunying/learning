// 213. House Robber II
// Medium

// 677

// 19

// Favorite

// Share
// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

// Example 1:

// Input: [2,3,2]
// Output: 3
// Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
//              because they are adjacent houses.
// Example 2:

// Input: [1,2,3,1]
// Output: 4
// Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//              Total amount you can rob = 1 + 3 = 4.

class Solution {
    //要么留头 ， 要么留尾
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int n = nums.length;
        return Math.max(robI(nums, 0 , n - 2) , robI(nums, 1 , n - 1));
    }
    public int robI(int[] nums , int s , int e){
        int n = nums.length;
        int[][] f = new int[n + 1][2];
        
        for(int i = s + 1 ; i <= e + 1; i++){
            f[i][0] = f[i - 1][1] + nums[i - 1];
            f[i][1] = Math.max(f[i - 1][0] , f[i - 1][1]);
        }
        return Math.max(f[e + 1][0] , f[e + 1][1]);
    }
}
