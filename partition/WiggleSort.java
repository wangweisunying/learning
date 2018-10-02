// Wiggle Sort
// Given an unsorted array nums, reorder it in-place such that

// nums[0] <= nums[1] >= nums[2] <= nums[3]....
// Example
// Given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

// Notice
// Please complete the problem in-place.


public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int i = 1;
        while(i + 1 < nums.length){
            int tmp = nums[i];
            nums[i] =nums[i + 1];
            nums[i + 1] = tmp;
            i+=2;
        }

    
    }
}


public class Solution {
    /**
     * @param nums a list of integer
     * @return void
     */
    public void wiggleSort(int[] nums) {
        // Write your code here
        for(int i=1; i<nums.length; i++) {
            if((i%2==1 && (nums[i] < nums[i-1]) || 
              (i%2==0) && (nums[i] > nums[i-1]))) {
                swap(nums, i-1, i);
            }
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}