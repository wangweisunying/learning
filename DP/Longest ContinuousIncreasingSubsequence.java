
// Longest Continuous Increasing Subsequence
// Give an integer array，find the longest increasing continuous subsequence in this array.

// An increasing continuous subsequence:

// Can be from right to left or from left to right.
// Indices of the integers in the subsequence should be continuous.
// Example
// For [5, 4, 2, 1, 3], the LICS is [5, 4, 2, 1], return 4.

// For [5, 1, 2, 3, 4], the LICS is [1, 2, 3, 4], return 4.

public class Solution {
    /**
     * @param A: An array of Integer
     * @return: an integer
     */ 
    public int longestIncreasingContinuousSubsequence(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n  = nums.length;
        int[] f = new int[n]; // 以 i 结尾的最长 不一定是全局最长
        f[0] = 1;
        int i , j , max = 1; //最小值
        for(i = 1 ; i < n ; i++){
            f[i] = 1;
            if(nums[i] > nums[i - 1]){
                f[i] = Math.max(f[i] , f[i - 1] + 1);
            }
            max = Math.max(max , f[i]); // 每次都需要更新
        }
        int[] r = new int[n];
        r[n - 1] = 1 ;
        for(i = n - 2 ; i >= 0 ; i--){
            r[i] = 1;
            if(nums[i] > nums[i + 1]){
                r[i] = Math.max(r[i] , r[i + 1] + 1);
            }
            max = Math.max(max , r[i]);
        }
        return  max;
    }
}

