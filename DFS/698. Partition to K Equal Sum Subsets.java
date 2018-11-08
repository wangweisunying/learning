// 698. Partition to K Equal Sum Subsets
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

// Example 1:
// Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
// Output: True
// Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
// Note:

// 1 <= k <= len(nums) <= 16.
// 0 < nums[i] < 10000.


// it is a combination dfs problem 
// 1. use index to avoid the duplicate selection before the index
// 2. use boolean[] to record the position that visited in the previous round!(only works for mutiple round) , since index already get rid of dup in this round ,
// visited is only used for next rounds 
// 3. once found the one solution , start from the beginning of the array to seach again ,this time , use visited to eliminate the visited position.
// 4. do k times above and return the result  
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length < k){
            return false;
        }
        int target = 0;
        for(int i : nums){
            target += i;
        } 
        if(target % k != 0){
            return false;
        }
        Arrays.sort(nums);
        return dfs(nums , new boolean[nums.length] , k , 0 , 0 , target / k);
    }
    private boolean dfs(int[] nums, boolean[] visited ,int k , int index , int cur , int target){
        if(k == 0){
            return true;
        }
        for(int i = index ; i < nums.length ; i++){
            if(visited[i]){
                continue;
            }
            if(cur + nums[i] > target){
                break;
            }
            else if(cur + nums[i] == target){
                visited[i] = true;
                if(dfs(nums , visited , k - 1 , 0 , 0 , target)){
                    return true;
                }
                visited[i] = false;
            }
            else{
                visited[i] = true;
                if(dfs(nums , visited , k , i + 1 ,cur + nums[i]  , target)){
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}