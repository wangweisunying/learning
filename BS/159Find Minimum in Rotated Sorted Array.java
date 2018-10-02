
// 159Find Minimum in Rotated Sorted Array
// Suppose a sorted array is rotated at some pivot unknown to you beforehand.

// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

// Find the minimum element.

// Example
// Given [4, 5, 6, 7, 0, 1, 2] return 0

// Notice
// You may assume no duplicate exists in the array.
public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */


    //draw a axis and you will find nums[s]  always greater than nums[e];
    public int findMin(int[] nums) {
        
        int s  = 0 , e = nums.length - 1;
        if(nums[s] < nums[e]){
            return nums[s];
        }
        while(s + 1 < e){
            int mid = (s + e) / 2;
            if(nums[mid] > nums[s]){
                s = mid;
            }
            else if(nums[mid] < nums[e]){
                e = mid;
            }
            // else{ // this wont be the case
            //     s = mid;
            // }
        }
        return nums[s] < nums[e] ? nums[s] : nums[e];
    }
}

public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // res <= nums[lastIndex]  find the first position and < = so ingore +-1
        if(nums == null || nums.length == 0){
            return -1;
        }
        int start = 0 , end = nums.length - 1 , target = nums[nums.length - 1];
        while(start + 1 < end){
            int mid = (start + end) / 2; 
            if(nums[mid] == target){
                end = mid;
            }
            else if (nums[mid] < target){
                end = mid;
            }
            else{
                start = mid;
            }
        }
        if(nums[start] <= target){
            return nums[start];
        }
        if(nums[end] <= target){
            return nums[end];
        }
        return -1;
    }
}