// PartitionEqualSubsetSum
// Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

// Example
// Given nums = [1, 5, 11, 5], return true
// two subsets: [1, 5, 5], [11]

// Given nums = [1, 2, 3, 9], return false

// Notice
// Each of the array element will not exceed 100.
// The array size will not exceed 200.
public class Solution {
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for(int i = 0; i < len ; i++ ){
            sum += nums[i];
        }
        if(sum % 2 == 1){
            return false;
        }
        sum /= 2;
        
        boolean [] dp = new boolean[sum + 1];
        for(int i = 0; i <=sum ; i ++)
            dp[i] = false;
        dp[0] = true;
        for(int i = 0; i < len; i++){
            for(int j= sum ; j >= nums[i]; j--){
                dp[j] |= dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
}









//dfs tle
public class Solution {
    /**
     * @param nums: a non-empty array only positive integers
     * @return: true if can partition or false
     */
    boolean res = false;
    public boolean canPartition(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for(int x : nums){
            sum += x;
        }
    
        dfs(nums , 0 , 0 ,  sum );
        return res;
    }
    private void dfs(int[] nums , int startIndex ,int get , int rest){
        if(res){
            return;
        }
        if(get == rest){
            res = true;
            return;
        }
        for(int i = startIndex ; i < nums.length ; i++){
            if(get > rest){
                break;
            }
            //确保只选第一个 回溯时无重复选择
            if(i > startIndex && nums[i] == nums[i - 1]){
                continue;
            }
            dfs(nums , i + 1 , get + nums[i] , rest - nums[i]);
        }
    }

}

// []
// [1]
// [1, 1]
// [1, 1, 1]
// [1, 1, 1, 1]
// [1, 1, 1, 1, 1]
// [1, 1, 1, 1, 1, 1]
// [1, 1, 1, 1, 1, 1, 2]
// [1, 1, 1, 1, 1, 1, 2, 9]
// 9 7  9 8
// [1, 1, 1, 1, 1, 1, 9]
// 9 7  9 8
// [1, 1, 1, 1, 1, 2]
// [1, 1, 1, 1, 1, 2, 9]
// 9 7  9 8
// [1, 1, 1, 1, 1, 9]
// 9 7  9 8
// 1 4  1 5
// [1, 1, 1, 1, 2]
// [1, 1, 1, 1, 2, 9]
// 9 7  9 8
// 9 7  9 8
// 1 3  1 4
// 1 4  1 5
// 9 7  9 8
// 1 2  1 3
// 1 3  1 4
// 1 4  1 5
// 9 7  9 8
// 1 1  1 2
// 1 2  1 3
// 1 3  1 4
// 1 4  1 5
// 9 7  9 8
// 1 0  1 1
// 1 1  1 2
// 1 2  1 3
// 1 3  1 4
// 1 4  1 5
// 9 7  9 8


