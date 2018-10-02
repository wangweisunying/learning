
// 608Two Sum II - Input array is sorted
// Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

// The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

// Example
// Given nums = [2, 7, 11, 15], target = 9
// return [1, 2]

// Notice
// You may assume that each input would have exactly one solution.

public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        int s = 0 , e = nums.length - 1;
        int[] res = new int[2];
        while(s < e){
            int now = nums[s] + nums[e];
            if(now == target){
                res[0] = s + 1;
                res[1] = e + 1;
                return res; 
            }
            else if(now < target){
                s++;
            }
            else{
                e--;
            }
        }
        return res;
    }
}