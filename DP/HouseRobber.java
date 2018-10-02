// House Robber
// You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you 
// from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

// Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

// Example
// Given [3, 8, 4], return 8.

// Challenge
// O(n) time and O(1) memory.


public class Solution {
    /**
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        if(A.length == 0){
            return 0;
        }
        int n = A.length;
        long[][] f = new long[n + 1][2];
        f[0][0] = 0; //  not rob 0
        f[0][1] = 0; // rob 1
        
        for(int i = 1 ; i <= n ;i ++){
            f[i][0] = Math.max(f[i - 1][1] , f[i - 1][0]); //not rob =  max (rob , not rob )
            f[i][1] = f[i - 1][0] + A[i - 1]; // rob = not rob + get

        }
        return Math.max(f[n][0] , f[n][1]);
    }
}