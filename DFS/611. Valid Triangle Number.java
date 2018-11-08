// 611. Valid Triangle Number
// DescriptionHintsSubmissionsDiscussSolution
// Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
// Example 1:
// Input: [2,2,3,4]
// Output: 3
// Explanation:
// Valid combinations are: 
// 2,3,4 (using the first 2)
// 2,3,4 (using the second 2)
// 2,2,3

//这是3 sum 的变种 双指针
class Solution {
    public int triangleNumber(int[] nums) {
        if(nums == null || nums.length < 3){
            return -1;
        }
        Arrays.sort(nums);
        int ct = 0;
        for(int k = 2 ; k < nums.length ; k++){
            int i = 0 , j = k - 1;
            while(i < j){
                if(nums[i] + nums[j] < nums[k]){
                    //参考 3sum smaller
                    ct+= j - i;
                    j--;
                }
                else{
                    i++;
                }
            }
        }
        return ct;
    }
}