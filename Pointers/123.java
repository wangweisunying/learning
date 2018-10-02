
// 31. Partition Array
// Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

// All elements < k are moved to the left
// All elements >= k are moved to the right
// Return the partitioning index, i.e the first index i nums[i] >= k.

// Example
// If nums = [3,2,2,1] and k=2, a valid answer is 1.

// Challenge
// Can you partition the array in-place and in O(n)?

// Notice
// You should do really partition in array nums instead of just counting the numbers of integers smaller than k.

// If all elements in nums are smaller than k, then return nums.length

public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        return partition(nums , 0 , nums.length - 1, k);
    }
    public int partition(int[] nums ,int s , int e, int k){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int l = s ,  r = e;
        
        while(l < r){
            while(l < r && nums[l] < k){
                l++;
            }
            while(l < r && nums[r] >= k){
                r--;
            }
            if(l < r){
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
                r--;
            }
        }
        if(nums[l] >= k){
            return l;
        }
        return l + 1;
    }
}


public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int s = 0 , e = nums.length - 1;
        while(s < e){ // 最中间值还未判断
            while(s <e && nums[s] < k){
                s++;
            }
            while(s < e && nums[e] >= k){
                e--;
            }
            if(s < e){
                int tmp = nums[s];
                nums[s] = nums[e];
                nums[e] = tmp;
                s++;
                e--;
                
            }
        }
        
        //判断最中间值 
        if(nums[s] >= k){
            return s;
        } 
        else{
            return s + 1;
        }
    }
}