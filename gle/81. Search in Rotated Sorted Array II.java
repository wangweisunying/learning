// 81. Search in Rotated Sorted Array II
// Medium

// 517

// 251

// Favorite

// Share
// Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

// (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

// You are given a target value to search. If found in the array return true, otherwise return false.

// Example 1:

// Input: nums = [2,5,6,0,0,1,2], target = 0
// Output: true
// Example 2:

// Input: nums = [2,5,6,0,0,1,2], target = 3
// Output: false

class Solution {
    public boolean search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return false;
        int s = 0 , e = nums.length - 1;
        while(s + 1 < e){
            int mid = (e - s) / 2 + s;
            if(nums[mid] == target){
                return true;
            }
            if(nums[mid] > nums[s]){
                if(target >= nums[s] && target < nums[mid]){
                    e = mid;
                }
                else{
                    s = mid;
                }

            }
            else if(nums[mid] < nums[s]){
                if(target < nums[s] && target > nums[mid]){
                    s = mid;
                }
                else{
                    e = mid;
                }
            }
            else{
                ++s;
            }
        }
        return nums[s] == target || nums[e] == target;
    }
}





public class Solution {
    public boolean search(int[] nums, int target) {
        // note here end is initialized to len instead of (len-1)
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[start]) { // nums[start..mid] is sorted
                // check if target in left half
                if (target < nums[mid] && target >= nums[start]) end = mid;
                else start = mid + 1;
            } else if (nums[mid] < nums[start]) { // nums[mid..end] is sorted
                // check if target in right half
                if (target > nums[mid] && target < nums[start]) start = mid + 1;
                else end = mid;
            } else { // have no idea about the array, but we can exclude nums[start] because nums[start] == nums[mid]
                start++;
            }
        }
        return false;
    }
}