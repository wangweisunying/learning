
// 135. Combination Sum
// Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

// The same repeated number may be chosen from C unlimited number of times.

// Example
// Given candidate set [2,3,6,7] and target 7, a solution set is:

// [7]
// [2, 2, 3]
// Notice
// All numbers (including target) will be positive integers.
// Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
// The solution set must not contain duplicate combinations.

public class Solution {
    /**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList();
        Arrays.sort(nums);
        dfs(res , nums , target , new ArrayList() , 0 );
        return res;
    }
    private void dfs(List<List<Integer>> res ,int[] nums, int target , List<Integer> cur ,int index){ //index 作用不要指针回到之前的区间去
        if(target == 0){
            res.add(new ArrayList(cur));
            return;
        }
        for(int i = index ; i < nums.length ; i++){
            if(target - nums[i] < 0){
                break;
            }
            if(i > index && nums[i] == nums[i - 1]){
                continue;
            }
            cur.add(nums[i]);
            dfs(res, nums , target - nums[i] , cur , i);
            cur.remove(cur.size() - 1);
        }
    }
}
