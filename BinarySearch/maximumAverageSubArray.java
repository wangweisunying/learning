
// Maximum Average Subarray
// Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

// Example
// Given nums = [1,12,-5,-6,50,3], k = 4, return 12.75.

// Explanation:
// Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
// Notice
// 1 <= k <= n <= 30,000.
// Elements of the given array will be in the range [-10,000, 10,000].

public class Solution {
    /**
     * @param nums: an array
     * @param k: an integer
     * @return: the maximum average value
     */
    public double findMaxAverage(int[] nums, int k) {
        int i;
        int start = 0;
        int res = Integer.MIN_VALUE;
        for(i = 0 ; i < k ; i ++){
            start += nums[i];
            res = Math.max(res , start); 
        }
        
        for(i = 1 ; i <= nums.length - k ; i ++){
            start = start - nums[i - 1] + nums[i + k - 1];
            res = Math.max(res , start); 
        }
        return (double)res / (double)k;
    }
}