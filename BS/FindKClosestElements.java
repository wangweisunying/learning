// Given a target number, a non-negative integer k and an integer array A sorted in ascending order,
//  find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. 
//  Otherwise, sorted in ascending order by number if the difference is same.

// Example
// Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].

// Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].

// Challenge
// O(logn + k) time complexity.


public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] nums, int target, int k) {
        int[] res = new int[k];
        int i = 0 , j = nums.length - 1 , small = -1 ;
        while(i + 1 < j){
            int mid = (i + j) / 2 ; 
            if( nums[mid]  == target ){
                j = mid; 
            }
            else if(nums[mid] < target){
                i = mid;
            }
            else{
                j = mid;
            }
        }
        //找到左边界
        if(nums[j] < target) {
            small = j;
        }
        if(nums[i] < target){
            small = i;
        }
        
        int l = small  , r = small + 1;
        for(int g = 0 ; g < k ; g ++){
            if(isLeftCloser(nums , target , l , r)){
                res[g] = nums[l--];
            }
            else{
                res[g] = nums[r++];
            }
        }
        return res;
    }
    private boolean isLeftCloser(int[] nums, int target , int left ,int right){
        if(left < 0){
            return false;
        }
        if(right >= nums.length){
            return true;
        }
        if(target - nums[left] <= nums[right] - target){
            return true;
        }
        return false;
    }
}