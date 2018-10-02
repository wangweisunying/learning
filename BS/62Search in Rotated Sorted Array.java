// 62Search in Rotated Sorted Array
// Suppose a sorted array is rotated at some pivot unknown to you beforehand.

// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

// You are given a target value to search. If found in the array return its index, otherwise return -1.

// You may assume no duplicate exists in the array.

// Example
// For [4, 5, 1, 2, 3] and target=1, return 2.

// For [4, 5, 1, 2, 3] and target=0, return -1.

// Challenge
// O(logN) time


public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int s = 0 , e = nums.length - 1;
        while(s + 1 < e){
            int mid = s + (e - s) / 2;
            if(nums[mid] == target){
                return mid;
            }
            if(nums[mid] < target){
                if(nums[mid] >= nums[s] || target <= nums[e]){
                    s = mid;
                }
                else{
                    e = mid;
                }
            }
            else{
                if(target >= nums[s] || nums[mid] <= nums[e]){
                    e = mid;
                }
                else{
                    s = mid;
                }
            }

        }
        if(nums[s] == target){
            return s;
        }
        if(nums[e] == target){
            return e;
        }
        return -1;
    }
}


public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] nums, int target) {
        //first get the edge then take 2 parts do bs
        if(nums == null || nums.length == 0){
            return -1;
        }
        int e = findE(nums);
        
        if(target >= nums[0] && target <= nums[e] ){
            return binarySearch(nums,target,0,e);
        }
        if(target >= nums[e + 1] && target <= nums[nums.length - 1]){
            return binarySearch(nums,target,e + 1,nums.length - 1);
        }
        return - 1;
        
    }
    public static int binarySearch(int[] nums, int target ,int s ,int e){
        while( s + 1 < e){
            int mid = (s + e) / 2;
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        if(nums[s] == target){
            return s;
        }
        if(nums[e] == target){
            return e;
        }
        return -1;
    }
    public static int findE(int[] nums){
        int s = 0 , e = nums.length - 1;
        while( s + 1 < e){
            int mid = (s + e) / 2;
            if(nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]){
                return mid;
            }
            else if(nums[mid] >= nums[0] ){
                s = mid;
            }
            else if(nums[mid] <= nums[nums.length - 1]){
                e = mid;
            }
        }
        return nums[s] > nums[e]? s : e ;
    }
    
}