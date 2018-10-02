// 521.Remove Duplicate Numbers in Array
// Given an array of integers, remove the duplicate numbers in it.

// You should:

// Do it in place in the array.
// Move the unique numbers to the front of the array.
// Return the total number of the unique numbers.
// Example
// Given nums = [1,3,1,4,4,2], you should:

// Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
// Return the number of unique integers in nums => 4.
// Actually we don't care about what you place in ?, we only care about the part which has no duplicate integers.

// Challenge
// Do it in O(n) time complexity.
// Do it in O(nlogn) time without extra space.
// Notice
// You don't need to keep the original order of the integers.

public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int s = 0;
        
        HashSet<Integer> set = new HashSet();
        set.add(nums[s]);
        for(int i = 1 ; i < nums.length ; i++){
            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            nums[++s] = nums[i];
        }
        return s + 1;
    }
}




public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        Arrays.sort(nums);
        int  s = 0 , f =  0;
        while(s < nums.length && f < nums.length){
            while(f < nums.length && nums[s] == nums[f]){
                f++;
            }
            if(f < nums.length){
                nums[++s] = nums[f++];
            }
            
        }
        return s + 1;
    }
}


public class Solution {
    /*
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int s = 0;
        for(int i = 1;i < nums.length ; i++){
            if(nums[i]!=nums[s]){
               nums[s+1] = nums[i]; 
               s++;
            } 
        }
        return s+1;
    }
}