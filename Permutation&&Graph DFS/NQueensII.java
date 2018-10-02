
// Follow up for N-Queens problem.

// Now, instead outputting board configurations, return the total number of distinct solutions.

// Example
// For n=4, there are 2 distinct solutions.

public class Solution {
    /**
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
public class Solution {
    /**
     * @param n: The number of queens.
     * @return: The total number of distinct solutions.
     */
    int res = 0;
    public int totalNQueens(int n) {
        if(n <= 0){
            return 0;
        }
        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++){
            arr[i] = i;
        }
        boolean[] visited = new boolean[n];
        dfs(arr  , visited , new ArrayList());
        return res;
        
    }
    private void dfs(int[] arr ,  boolean[] visited ,ArrayList<Integer> list){
        if(list.size() == arr.length){
            res++;
            return;
        }
        for(int i = 0 ; i < arr.length ; i++){
            if(visited[i]){
                continue;
            }

            if(!isQulify(list , arr[i])){
                continue;
            }

            list.add(arr[i]);
            visited[i] = true;
            dfs(arr , visited , list);
            visited[i] = false;
            list.remove(list.size() - 1);            
        }
    }
    private boolean isQulify(ArrayList<Integer> list , int i){
        int s1 = list.size() + i;  // 行 + 列
        int s2 = list.size() - i; // 行 - 列
        for(int j = 0 ; j < list.size() ; j ++){
            if( s1 == list.get(j) + j || s2 == j - list.get(j)){//行 -列
                return false;
            }
        }
        return true;    
    }
}