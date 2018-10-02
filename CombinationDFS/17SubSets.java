// 17. Subsets
// Given a set of distinct integers, return all possible subsets.

// Example
// If S = [1,2,3], a solution is:

// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]
// Challenge
// Can you do it in both recursively and iteratively?

// Notice
// Elements in a subset must be in non-descending order.
// The solution set must not contain duplicate subsets.

public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList();
        dfs(nums , new ArrayList() , 0 , res);
        return res;
    }
    private void dfs(int[] nums , List<Integer> cur , int index , List<List<Integer>> res){
        res.add(new ArrayList(cur));
        for(int i = index ; i < nums.length ; i++){
            cur.add(nums[i]);
            dfs(nums , cur , i + 1, res);
            cur.remove(cur.size() - 1);
        }
    }
}