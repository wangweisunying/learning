// 673. Number of Longest Increasing Subsequence
// DescriptionHintsSubmissionsDiscussSolution
// Given an unsorted array of integers, find the number of longest increasing subsequence.

// Example 1:
// Input: [1,3,5,4,7]
// Output: 2
// Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
// Example 2:
// Input: [2,2,2,2,2]
// Output: 5
// Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
// Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.


// Related Topics 

// Similar Questions 
// Longest Increasing SubsequenceLongest Continuous Increasing Subsequence
// Java	

class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] len = new int[n];
        int[] ct = new int[n];
        int maxLen = 0 , maxCt = 0;
        for(int i = 0 ; i < n ; i++){
            len[i] = 1;
            ct[i] = 1;
            for(int j = 0 ; j < i ; j++){
                if(nums[i] > nums[j]){
                    if(len[i] < len[j] + 1){
                        len[i] = len[j] + 1;
                        ct[i] = ct[j];
                    }
                    else if(len[i] = len[j] + 1){
                        ct[i] += ct[j];
                    }
                }
            }
            if(maxLen < len[i]){
                maxLen = len[i];
                maxCt = ct[i];
            }
            else if(maxLen == len[i]){
                maxCt += ct[i];
            }
        }
        return maxCt;
        
    }
}
