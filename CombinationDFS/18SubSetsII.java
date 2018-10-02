
// Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

// Example
// Input: [1,2,2]
// Output:

// [
//   [2],
//   [1],
//   [1,2,2],
//   [2,2],
//   [1,2],
//   []
// ]

public class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList();
        dfs(nums , new ArrayList() , 0 , res);
        return res;
    }
    private void dfs(int[] nums , List<Integer> cur , int index , List<List<Integer>> res){
        res.add(new ArrayList(cur));
        for(int i = index ; i < nums.length ; i++){
            if(i > index && nums[i] == nums[i - 1]){ //index用法一定要掌握,此时前一个已经处理过了 后面相同的就排除掉
                continue;
            }
            cur.add(nums[i]);
            dfs(nums , cur , i + 1, res);
            cur.remove(cur.size() - 1);
        }
    }
}


public class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums.length == 0){
            res.add(new ArrayList());
            return res;
        }
        Arrays.sort(nums);
        dfs(res , nums , new ArrayList() , 0);
        return res;
    }
    private void dfs(List<List<Integer>> res ,int[] nums , List<Integer> cur ,int startIndex){
        res.add(new ArrayList(cur));
        for(int i = startIndex ; i < nums.length ; i ++){
            if(i > startIndex && nums[i] == nums[i - 1]){
        // 以 1 2 2 为例 i > startIndex 表示之前 i = startIndex 时已经加过 第一个（准确说是前一个）2 并且递归 找到了以第一个2 开头的所有子集  
        // ，之后删除！删除！第一个2！！后！！！ 进入for的下一个 也就是第二个 2 ，此时 cur 里只有 1 ，而 第二个2 不允许单独存在的（画递归树），要跳过
        //实在理解不了就当越界处理
                continue;
            }
            
    //找到以cur + numsi 开头的 所有的子集放入res
            cur.add(nums[i]);
            dfs(res , nums , cur ,i + 1);
            //找完了 删掉 numsi 下一个 nums i+ 1上
            cur.remove(cur.size() - 1);
            
        }
    }
}




































public class Solution {
    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        if(nums.length == 0){
            res.add(new ArrayList());
            return res;
        }
        Arrays.sort(nums);
        dfs(res , nums , new ArrayList() , 0);
        return res;
    }
    private void dfs(List<List<Integer>> res ,int[] nums , List<Integer> cur ,int startIndex){
        res.add(new ArrayList(cur));
        for(int i = startIndex ; i < nums.length ; i ++){
            if(i > startIndex && nums[i] == nums[i - 1]){
                continue;
            }
            cur.add(nums[i]);
            dfs(res , nums , cur ,i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}