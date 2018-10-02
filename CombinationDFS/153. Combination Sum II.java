// 153. Combination Sum II
// Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

// Each number in C may only be used once in the combination.

// Example
// Given candidate set [10,1,6,7,2,1,5] and target 8,

// A solution set is:

// [
//   [1,7],
//   [1,2,5],
//   [2,6],
//   [1,1,6]
// ]
// Notice
// All numbers (including target) will be positive integers.
// Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
// The solution set must not contain duplicate combinations.



public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null || nums.length == 0){
            return res;
        }
        Arrays.sort(nums);
        dfs(res,nums,new ArrayList(), 0 , target);
        return res;
    }
    private void dfs( List<List<Integer>> res,int[] nums,List<Integer> cur , int index ,  int target){
        if(target == 0){
            res.add(new ArrayList(cur));
            return;
        }
        for(int i = index ; i < nums.length ; i++){
            if(target - nums[i] < 0){
                break;
            }
            if(i > index && nums[i] == nums[i - 1]){ // i > index 去重！不是i > 0 只与本层有关系 以 1，1，1，1，1, 1   =  3 为例 结果只取index位的 任何》index 位都不取
                continue;
            }
            cur.add(nums[i]);
            dfs(res, nums , cur , i + 1, target - nums[i]);
            cur.remove(cur.size() - 1);
        }
    }
}


public class Solution {
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        if(nums == null){
            return res;
        }
        Arrays.sort(nums);
        helper(res, nums, target, new ArrayList() ,0 );
        return res;
    }
    private void helper( List<List<Integer>> res , int[] nums , int remain , List<Integer> curCombine , int startIndex){
        if(remain == 0){
            res.add(new ArrayList(curCombine));
        }
        
        for(int i = startIndex ; i < nums.length ; i++){
            if(nums[i] > remain){
                break;
            }
            if(i > startIndex && nums[i] == nums[i - 1]){  // 去重 (subsets ii)
                continue;
            }
            
            curCombine.add(nums[i]);
            helper(res, nums, remain - nums[i], curCombine , i + 1);//不允许重复，从下个开始选
            curCombine.remove(curCombine.size() - 1);
        }
    }
}