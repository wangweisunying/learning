// A non-negative numbers can be regarded as product of its factors.
// Write a function that takes an integer n and return all possible combinations of its factors.

// Example
// Given n = 8
// return [[2,2,2],[2,4]]
// // 8 = 2 x 2 x 2 = 2 x 4.

// Given n = 1
// return []

// Given n = 12
// return [[2,6],[2,2,3],[3,4]]




public class Solution {
    /**
     * @param n an integer
     * @return a list of combination
     */

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    void dfs(int start, int remain) {
        if (remain == 1) {
            if (path.size() != 1) {
                ans.add(new ArrayList<>(path)); //deep copy
            }
            return;
        }

        for (int i = start; i <= remain; i++) {
            if (i > remain / i) {
                break; // 5 2  and 2 5
            }
            if (remain % i == 0) {
                path.add(i);                  //进栈
                dfs(i, remain / i);
                path.remove(path.size() - 1); //出栈
            }
        }

        path.add(remain);
        dfs(remain, 1); // 此时再加入结果
        path.remove(path.size() - 1);
    }

    public List<List<Integer>> getFactors(int n) {
        // write your code here
        dfs(2, n);
        return ans;
    }
}

public class Solution {
    /**
     * @param n an integer
     * @return a list of combination
     */
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> getFactors(int n) {
        dfs(2 , n);
        return res;
    }
    private void dfs(int start , int remain){
        if(remain == 1){
            if(path.size() > 1){
                res.add(new ArrayList(path));
            }
            return;
        }
        for(int i = start ; i <= remain ; i ++){
            if(i > remain / i){
                break;
            }
            if(remain % i != 0){
                continue;
            }
            path.add(i);
            dfs(i , remain / i);
            path.remove(path.size() - 1);
        }
        
        path.add(remain);
        dfs(remain , 1);
        path.remove(path.size() - 1);
    }
}