
// Kth Largest Element
// Find K-th largest element in an array.

// Example
// In array [9,3,2,4,8], the 3rd largest element is 4.

// In array [1,2,3,4,5], the 1st largest element is 5, 2nd largest element is 4, 3rd largest element is 3 and etc.

// Challenge
// O(n) time, O(1) extra memory.

// Notice
// You can swap elements in the array
public class Solution {
    /**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        qs(0 , nums.length - 1 ,nums);
        return nums[n-1];
    }
    private void qs(int start ,int end , int[] nums){
        if(start >= end){
            return;
        }
        int s = start , e = end , p = nums[(s + e) / 2];
        while(s <= e){
            while(s <= e && nums[s] > p){
                s++;
            }
            while(s <= e && nums[e] < p){
                e--;
            }
            if(s <= e){
                int tmp = nums[s];
                nums[s] = nums[e];
                nums[e] = tmp;
                s++;
                e--;
            }
        }
        qs(start , e , nums);
        qs(s, end ,nums);
    }
}


public class Solution {
    /**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
       
        return  helper(0 , nums.length - 1 ,nums , n - 1);
    }
    private int helper(int start ,int end , int[] nums ,int k){
        if(start >= end){
            return nums[k];
        }
        int s = start , e = end , p = nums[(s + e) / 2];
        while(s <= e){
            while(s <= e && nums[s] > p){
                s++;
            }
            while(s <= e && nums[e] < p){
                e--;
            }
            if(s <= e){
                int tmp = nums[s];
                nums[s] = nums[e];
                nums[e] = tmp;
                s++;
                e--;
            }
        }
        if(k <= e){
            return helper(start , e , nums , k);
        }
        if(k >= s){
            return helper(s , end ,nums, k);
        }
        return nums[k];
    }
    
}



class Solution {
    /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
    public int kthLargestElement(int k, int[] nums) {
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length){
            return -1;
        }
        return partition(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int partition(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[k];
        }
        
        int left = start, right = end;
        int pivot = nums[(start + end) / 2];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        
        if (k <= right) {
            return partition(nums, start, right, k);
        }
        if (k >= left) {
            return partition(nums, left, end, k);
        }
        return nums[k];
    }    
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
};