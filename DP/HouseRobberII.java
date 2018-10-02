// House Robber II
// After robbing those houses on that street,
//  the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle.
//   That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

// Example
// nums = [3,6,4], return 6

public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return nums[0];
        }
        int n = nums.length;
        // not rob 0
        int[][] f = new int[n + 1][2];
        int i;
        f[1][0] = 0;
        f[1][1] = 0;
        for(i = 2 ; i <=n ; i++){
            f[i][0] = Math.max(f[i - 1][1] , f[i - 1][0]);
            f[i][1] = f[i - 1][0] + nums[i - 1];
        }
        int notRob0 = Math.max(f[n][0] , f[n][1]);
        
        // not rob n
        int[][] g = new int[n][2];
        g[0][0] = 0; // not rob
        g[0][1] = 0; // rob
        for(i = 1 ; i < n ; i++){
            g[i][0] = Math.max(g[i - 1][1] , g[i - 1][0]);
            g[i][1] = g[i - 1][0] + nums[i - 1];
        }
        int notRobN = Math.max(g[n - 1][0] , g[n - 1][1]);
        return Math.max(notRob0 , notRobN);
    }
}


