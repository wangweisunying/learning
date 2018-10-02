
// Sort Colors II
// Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

// Example
// Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

// Challenge
// A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?

// Notice
// You are not suppose to use the library's sort function for this problem.

// k <= n


public class Solution {
    /**
     * @param colors: A list of integer
     * @param k: An integer
     * @return: nothing
     */
    public void sortColors2(int[] nums, int k) {
        qs(nums, 0 , nums.length - 1);
    }
    private void qs(int[] nums, int s, int e){
        if(s >= e){
            return;
        }
        int l = s , r = e ;
        int pivot = nums[(l + r) / 2];
        while(l <= r){
            while(l <= r && nums[l] < pivot){ // can not use  =  "3 ,2, 1, 4, 5" the right pointer is going to pass the pivort
                l++;
            }
            while(l <= r && nums[r] > pivot ){
                r--;
            }
            if(l <= r){
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++;
                r--;
            }
        }
        qs(nums , s , r);
        qs(nums , l , e);

    }
}