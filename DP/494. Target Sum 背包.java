// 494. Target Sum
// DescriptionHintsSubmissionsDiscussSolution
// You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

// Find out how many ways to assign symbols to make sum of integers equal to target S.

// Example 1:
// Input: nums is [1, 1, 1, 1, 1], S is 3. 
// Output: 5
// Explanation: 

// -1+1+1+1+1 = 3
// +1-1+1+1+1 = 3
// +1+1-1+1+1 = 3
// +1+1+1-1+1 = 3
// +1+1+1+1-1 = 3

// There are 5 ways to assign symbols to make the sum of nums be target 3.
// Note:
// The length of the given array is positive and will not exceed 20.
// The sum of elements in the given array will not exceed 1000.
// Your output answer is guaranteed to be fitted in a 32-bit integer.

//分层画图
https://leetcode.com/problems/target-sum/discuss/97335/Short-Java-DP-Solution-with-Explanation




public class Solution {
    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        if(s > sum || s < -sum) return 0;
        int[] f= new int[2 * sum + 1];
        f[sum] = 1;
        for(int i = 0 ; i < nums.length ; i++){
            int[] fnext = new int[2 * sum + 1];
            for(int j = 0 ; j < f.length ; j++ ){
                if(f[j] != 0){
                    fnext[j + nums[i]] += f[j];
                    fnext[j - nums[i]] += f[j];
                }    
            }
            f = fnext;
        }
        return f[sum + s];
    }
}



public class Solution {
    public int findTargetSumWays(int[] nums, int s) {
        int sum = 0; 
        for(int i: nums) sum+=i;
        if(s>sum || s<-sum) return 0;
        int[] dp = new int[2*sum+1];
        dp[0+sum] = 1;
        for(int i = 0; i<nums.length; i++){
            int[] next = new int[2*sum+1];
            for(int k = 0; k<2*sum+1; k++){
                if(dp[k]!=0){
                    next[k + nums[i]] += dp[k];
                    next[k - nums[i]] += dp[k];
                }
            }
            dp = next;
        }
        return dp[sum+s];
    }
}



//dfs memo //
// 用sum -> index 作为key 做memorization search ;
class Solution {
    public int findTargetSumWays(int[] nums, int sum) {
        Map<String ,Integer> map = new HashMap();
        return dfs(nums , sum , nums.length - 1  , map , "x");
        
    }
    private int dfs(int[] nums , int sum , int index  , Map<String , Integer> map ,String key){
        if(index < 0 ){
            if(sum == 0){
                return 1;
            }
            return 0;
        }
        if(map.containsKey(key)){
            return map.get(key);
        }
        int res = dfs(nums, sum - nums[index] , index - 1 , map , sum + "-" + index)
                  + dfs(nums, sum + nums[index] , index - 1 , map , sum + "+" + index);
        map.put(key , res);
        return res;
    }
}
//dfs 裸搜2^n
class Solution {
    public int findTargetSumWays(int[] nums, int sum) {
        int[] ct = new int[1];
        dfs(nums , sum , 0 , ct);
        return ct[0];
    }
    private void dfs(int[] nums , int sum , int index , int[] ct){
        if(index == nums.length ){
            if(sum == 0)ct[0]++;
            return;
        }
        dfs(nums, sum - nums[index] , index + 1 , ct);
        dfs(nums, sum + nums[index] , index + 1 , ct);
    }
}




public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        return helper(nums, 0, 0, S, new HashMap<>());
    }
    private int helper(int[] nums, int index, int sum, int S, Map<String, Integer> map){
        
        if (index == nums.length){
            if (sum == S){
                return 1;
            }else {
                return 0;
            }
        }
        String encodeString = index + "->" + sum;
        if (map.containsKey(encodeString)){
            return map.get(encodeString);
        }
        int curNum = nums[index];
        int add = helper(nums, index + 1, sum - curNum, S, map);
        int minus = helper(nums, index + 1, sum + curNum, S, map);
        map.put(encodeString, add + minus);
        return add + minus;
    }
}