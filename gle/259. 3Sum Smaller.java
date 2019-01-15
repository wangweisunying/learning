// 259. 3Sum Smaller
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of n integers nums and a target, find the number of index triplets i, j, k 

// with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

// Example:

// Input: nums = [-2,0,1,3], and target = 2
// Output: 2 
// Explanation: Because there are two triplets which sums are less than 2:
//              [-2,0,1]
//              [-2,0,3]
// Follow up: Could you solve it in O(n2) runtime?


class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if(nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        int ct = 0;
        for(int k = 2 ; k < nums.length ; k++){
            int s = 0 , e = k - 1;
            while(s < e){
                int now = nums[s] + nums[e] + nums[k];
                if(now < target){
                    ct += e - s;
                    s++;    
                }
                else{
                    e--;
                }
            } 
        }
        return ct;
    }
}