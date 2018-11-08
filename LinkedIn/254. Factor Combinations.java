// 254. Factor Combinations
// DescriptionHintsSubmissionsDiscussSolution
// Numbers can be regarded as product of its factors. For example,

// 8 = 2 x 2 x 2;
//   = 2 x 4.
// Write a function that takes an integer n and return all possible combinations of its factors.

// Note:

// You may assume that n is always positive.
// Factors should be greater than 1 and less than n.
// Example 1:

// Input: 1
// Output: []
// Example 2:

// Input: 37
// Output:[]
// Example 3:

// Input: 12
// Output:
// [
//   [2, 6],
//   [2, 2, 3],
//   [3, 4]
// ]
// Example 4:

// Input: 32
// Output:
// [
//   [2, 16],
//   [2, 2, 8],
//   [2, 2, 2, 4],
//   [2, 2, 2, 2, 2],
//   [2, 4, 4],
//   [4, 8]
// ]


public List<List<Integer>> getFactors(int n) {
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    helper(result, new ArrayList<Integer>(), n, 2);
    return result;
}

public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
    if (n <= 1) {
        // 判断质数
        if (item.size() > 1) {
            result.add(new ArrayList<Integer>(item));
        }
        return;
    }
    
    for (int i = start; i <= n; ++i) {
        if (n % i == 0) {
            item.add(i);
            helper(result, item, n/i, i);
            item.remove(item.size()-1);
        }
    }
}








//找到所有因子 然后combination dfs ,可以选重复选
class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<Integer> src = new ArrayList();
        int s = 2;
        while( s <= n / 2){
            if(n % s == 0){
                src.add(s);
            }
             s++;
        }
        List<List<Integer>> res = new ArrayList();
        if(n == 1){
            return res;
        }
        dfs(res , src , 0 , 1 , new ArrayList() , n);
        return res;
    }
    private void dfs(List<List<Integer>> res , List<Integer> src , int index , int pro ,List<Integer> cur , int n ){
        if(pro == n){
            res.add(new ArrayList(cur));
            return;
        }
        for(int i = index ; i < src.size() ; i++){
            if(src.get(i) * pro > n){
                break;
            }
            cur.add(src.get(i));
            dfs(res, src , i, pro * src.get(i), cur , n);
            cur.remove(cur.size() - 1);
        }
    }
}



