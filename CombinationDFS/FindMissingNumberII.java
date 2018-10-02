
// Giving a string with number from 1-n in random order, but miss 1 number.Find that number.

// Example
// Given n = 20, str = 19201234567891011121314151618

// return 17



public class Solution {
    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    int res = -1;
    boolean flag = false; // 全局变量 控制 跳出递归
    public int findMissing2(int n, String str) {
        int[] arr = new int[n];
        for(int i = 0 ; i < arr.length ; i++){
            arr[i] = i + 1;
        }
        boolean[] visited = new boolean[n];
        dfs(arr , str , visited );
        return res;
    }
    private void dfs(int[] arr , String str , boolean[] visited){
        if(flag){
            return;
        }
        if(str.equals("")){ 
            System.out.println("done");
            for(int i = 0 ; i < visited.length ; i ++){
                if(!visited[i]){
                    res = arr[i];
                }
            }
            flag = true;
            return;
        }
        for(int i = 0 ; i < arr.length ; i ++){
            String cur = String.valueOf(arr[i]);
            if(!str.startsWith(cur) || visited[i]){
                continue;
            }
            visited[i] = true;
            dfs(arr , str.substring(cur.length()) , visited);
            visited[i] = false;
        }
        
    }
}