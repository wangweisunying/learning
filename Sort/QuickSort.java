public class Solution {
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] nums) {
        if(nums.length == 0){
            return;
        }
        quickSort(nums , 0 , nums.length - 1);
    }
    private void quickSort(int[] nums , int start , int end){
        if(start >= end){
            return;
        }
        int l = start , r = end , midVal = nums[(l + r) / 2]; // nums 会变化 不要存index
        while(l <= r){
            while(l <= r && nums[l] < midVal){
                l++;
            }
            while(l <= r && nums[r] > midVal){
                r--;
            }
            if(l <= r){
                int tmp = nums[r];
                nums[r] = nums[l];
                nums[l] = tmp;
                l++;
                r--;
            }
            
        }
        quickSort(nums , start , r);
        quickSort(nums , l , end);
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