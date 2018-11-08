// 31. Next Permutation
// DescriptionHintsSubmissionsDiscussSolution
// Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

// The replacement must be in-place and use only constant extra memory.

// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

// 1,2,3 → 1,3,2
// 3,2,1 → 1,2,3
// 1,1,5 → 1,5,1

// 如果上来想不出方法，可以试着找找规律，我们关注的重点应是原数组末尾。

// 从末尾往左走，如果一直递增，例如...9,7,5，那么下一个排列一定会牵扯到左边更多的数，直到一个非递增数为止，
// 例如...6,9,7,5。对于原数组的变化就只到6这里，和左侧其他数再无关系。6这个位置会变成6右侧所有数中比6大的最小的数，而6会进入最后3个数中，且后3个数必是升序数组。
class Solution {
    public  void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2){
            return;
        }
        for(int i = nums.length - 2; i >= 0 ; i --){
            if(nums[i] < nums[i + 1]){
                int index  = bs(nums , i + 1 , nums.length - 1 , nums[i]);
                int tmp = nums[i];
                nums[i] = nums[index];
                nums[index] = tmp;
                Arrays.sort(nums , i + 1 ,nums.length);
                return;
            }
        }
        Arrays.sort(nums);

    }
    private int bs(int[] nums, int s , int e , int target){
        while(s + 1 < e){
            int mid = (s + e) / 2;
            if(nums[mid] <= target){
                e = mid;
            }
            else{
                s = mid;
            }
        }
        return s;
    }
}







































class Solution {
    public  void nextPermutation(int[] nums) {
        
        int i = nums.length - 1;
        for( ; i > 0 ; i--){
            if(nums[i] > nums[i - 1]){
                int index = bs(nums , i , nums.length - 1 , nums[i - 1]);
                int tmp = nums[index];
                nums[index] = nums[i - 1];
                nums[i - 1] = tmp;
                Arrays.sort(nums , i , nums.length);
                break;
            }
        } 
        if(i == 0){
            Arrays.sort(nums);
        }
        
    }
    private  int bs(int[] nums ,int s , int e , int target ){
        while(s + 1 < e ){
            int mid = (s + e) / 2;
            if(nums[mid] <= target){
                e = mid;
            }
            else{
                s = mid;
            }
        }
        if(nums[e] > target){
            return e;
        }
        return s;
    }
}