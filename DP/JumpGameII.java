// Jump Game II
// Given an array of non-negative integers, you are initially positioned at the first index of the array.

// Each element in the array represents your maximum jump length at that position.

// Your goal is to reach the last index in the minimum number of jumps.

// Example
// Given array A = [2,3,1,1,4]

// The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

public class Solution {
    /**
     * @param A: A list of integers
     * @return: An integer
     */
    public int jump(int[] nums) {
        int n = nums.length;
        if( n == 0){
            return 0;
        }
        int[] f = new int[n];
        f[0] = 0 ;
        for(int k = 1 ; k < n ; k ++){
            f[k] = Integer.MAX_VALUE;
        }
        for(int i = 0 ; i < n ; i ++){
            for(int j = nums[i]  ; j >= 0 ; j --){
                if(i + j < n){
                    f[i + j] = Math.min(f[i + j] , f[i] + 1);  
                }                
            } 
        }
        return f[n - 1]; 
    }
}