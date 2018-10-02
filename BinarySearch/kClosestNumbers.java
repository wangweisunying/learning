//  Find K Closest Elements
// Given a target number, a non-negative integer k and an integer array A sorted in ascending order,
//  find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. Otherwise,
//   sorted in ascending order by number if the difference is same.

// Example
// Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].

// Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].

// Challenge
// O(logn + k) time complexity.

// Notice
// The value k is a non-negative integer and will always be smaller than the length of the sorted array.
// Length of the given array is positive and will not exceed 10^4
// Absolute value of elements in the array and x will not exceed 10^4


public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] nums, int target, int k) {
        if(nums == null || nums.length < k){
            return null;
        }
        int[] res = new int[k];
        
        int s = 0 , e = nums.length - 1;
        while(s + 1 < e){
            int mid = (s + e) / 2;
            if(nums[mid] <= target){
                s = mid;
            }
            else{
                e = mid; 
            }
        }
        for(int i = 0 ; i < k ; i ++){
            if(s >= 0 && e < nums.length){
                res[i] = nums[e] - target < target - nums[s] ? nums[e++] : nums[s--];
                continue;
            }
            if(s < 0){
                res[i] = nums[e++];
                continue;
            }
            if(e == nums.length){
                res[i] = nums[s--];
                continue;
            }
        }
        return res;
        
    }
}