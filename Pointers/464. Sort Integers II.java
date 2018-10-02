
// 464. Sort Integers II
// Given an integer array, sort it in ascending order. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

// Example
// Given [3, 2, 1, 4, 5], return [1, 2, 3, 4, 5].
[3,2,1,4,5]
Expected
[1,2,3,4,5]

public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] nums) {
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


public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] nums) {
        if(nums.length == 0){
            return;
        }
        quickSort(nums, 0 , nums.length - 1);
    }
    private void quickSort(int[] nums , int s , int e){
        if(s >= e){
            return;
        }
        int l = s , r = e , mV = nums[(s + e) / 2];
        while(l <= r){
            while(l <= r && nums[l] < mV){
                l++;
            }
            while(l <= r && nums[r] > mV){
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
        quickSort(nums , s , r);
        quickSort(nums , l , e);
    }
}