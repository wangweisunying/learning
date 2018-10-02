// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

// Example
// Given n = 4 and k = 2, a solution is:

// [
//   [2,4],
//   [3,4],
//   [2,3],
//   [1,2],
//   [1,3],
//   [1,4]
// ]
public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new Arraylist();
        dfs(res , n , 1 , k , new ArraYList());
        return res;
    }
    private dfs( List<List<Integer>> res , int n, int index , int k , List<Integer> cur){
        if(k == 0){
            res.add(new ArrayList(cur));
            return;
        }
        for(int i = index ; i <=n ; i++){
            cur.add(i);
            dfs(res , n , i + 1, k - 1 , cur);
            cur.remove(cur.size() - 1);
        }
    }
}















public class Solution {
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        List<List<Integer>> res = new ArrayList();
        if(n == 0 || k == 0){
            return res;
        }
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++){
            arr[i] = i + 1;
        }
        dfs(res , arr , new ArrayList() , k , 0);
        return res;
    }
    private void dfs(List<List<Integer>> res , int[] arr , List<Integer> cur , int k , int startIndex){
        if(k == 0){
            res.add(new ArrayList(cur));
        }
        for(int i = startIndex ; i < arr.length ; i ++ ){
            cur.add(arr[i]);
            dfs(res , arr , cur , k - 1 , i + 1);
            cur.remove(cur.size() - 1);
        }
    }
}