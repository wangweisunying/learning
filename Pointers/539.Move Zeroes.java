
// 539.Move Zeroes
// Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

// Example
// Given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

// Notice
// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.

//巧妙
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        int index = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        for(int i = index ; i < nums.length ; i++){
            nums[i] = 0;
        }    
        
    }
}

public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        int s = 0 , f = 1;
        while(s < nums.length && f < nums.length){
            while(s < nums.length && nums[s] != 0){
                s++;
            }
            while(f < nums.length && (nums[f] == 0 || s >= f)){
                f++;
            }
            if(s < nums.length && f < nums.length){
                nums[s] = nums[f];
                nums[f] = 0;
                s++;
                f++;
            }
        }
    }
}

