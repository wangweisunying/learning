// 34. Find First and Last Position of Element in Sorted Array

// Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

// Your algorithm's runtime complexity must be in the order of O(log n).

// If the target is not found in the array, return [-1, -1].

// Example 1:

// Input: nums = [5,7,7,8,8,10], target = 8
// Output: [3,4]
// Example 2:

// Input: nums = [5,7,7,8,8,10], target = 6
// Output: [-1,-1]


class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }
        int s = 0 , e = nums.length - 1;
        while( s + 1 < e){
            int mid = (e - s) / 2 + s;
            if(nums[mid] < target ){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        int[] res = new int[2];
        if(nums[s] == target){
            res[0] = s ++;
            while(s < nums.length){            
                if(nums[s] != target){
                    res[1] = s - 1;
                    return res; 
                }
                s++;
            }
            res[1] = s - 1;
            return res;
        }
        else if(nums[e] == target){
            res[0] = e++;
            while(e < nums.length){            
                if(nums[e] != target){
                    res[1] = e - 1;
                    return res; 
                }
                e++;
            }
            res[1] = e - 1;
            return res;

        }
        else{
            return new int[]{-1 , -1};
        }
        
    }
}