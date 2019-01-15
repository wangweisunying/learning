// 546. Remove Boxes
// Given several boxes with different colors represented by different positive numbers. 
// You may experience several rounds to remove boxes until there is no box left.
//  Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
// Find the maximum points you can get.

// Example 1:
// Input:

// [1, 3, 2, 2, 2, 3, 4, 3, 1]
// Output:
// 23
// Explanation: 
// [1, 3, 2, 2, 2, 3, 4, 3, 1] 
// ----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
// ----> [1, 3, 3, 3, 1] (1*1=1 points) 
// ----> [1, 1] (3*3=9 points) 
// ----> [] (2*2=4 points)
// Note: The number of boxes n would not exceed 100.

//  For an entry in \text{dp[l][r][k]}dp[l][r][k], ll represents the starting index of the subarray, 
// rr represents the ending index of the subarray and kk represents the number of elements similar to the r^{th}r 
// th
//   element following it which can be combined to obtain the point information to be stored in \text{dp[l][r][k]}dp[l][r][k]. e.g
class Solution {

    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
    }

    public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] != 0) return dp[l][r][k];
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);

        
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                //ABCA =                                           AA                       +               BC
                // ABACA  =                                    AA                           +   BAC
                // ABACA  =                                    ABAA                         +   C
                dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
                //break;//每种组合都需要尝试, 贪心这里是错误的！
            }
        }
        return dp[l][r][k];
    }
}


class Solution {

    public int removeBoxes(int[] boxes) {
        int[][][] f = new int[100][100][100];
        return dfs(f , boxes , 0 , boxes.length - 1 , 0);
    }
    private int dfs(int[][][] f , int[] boxes , int l , int r, int k){
        if(l > r ) return 0;
        if(f[l][r][k] != 0) return f[l][r][k];
        while(l < r && boxes[r] == boxes[r - 1]){
            r--;
            k++;
        }
        f[l][r][k] = dfs(f , boxes , l , r - 1 , 0) + (k + 1)* (k + 1);
        for(int i = r - 1 ; i >= l ; i--){
            if(boxes[i] == boxes[r]){
                f[l][r][k] = Math.max(f[l][r][k] , dfs(f , boxes , l , i , k + 1) + dfs(f , boxes , i + 1 , r - 1 , 0));
            }
        }
        return f[l][r][k];
    }
}





//memo search
    public int removeBoxes(int[] boxes) {
        List<Integer> list = new ArrayList();
        for(int i : boxes) list.add(i);
        Map<List<Integer> , Integer> memo = new HashMap();
        return dfs(memo , list , 0);
    }
    private int dfs(Map<List<Integer> , Integer> memo , List<Integer> list , int cur){
        if(list.size() == 0) return cur;
        if(memo.containsKey(list))return memo.get(list) + cur;
        int max = 0;
        for(List<Integer> canList : split(list)){
            int len = list.size() - canList.size();
            max = Math.max(max , dfs(memo , canList , len * len ));
        }
        memo.put(list , max);
        return max + cur;
    }
    private static List<List<Integer>> split(List<Integer> list){
        List<List<Integer>> res = new ArrayList();
        int s = 0 , e = 0;
        while(e < list.size()){
            if(list.get(s) != list.get(e)){
                List<Integer> listTmp = new ArrayList();
                for(int i = 0 ; i < s ; i++){
                    listTmp.add(list.get(i));
                }
                for(int i = e ; i < list.size() ; i++){
                    listTmp.add(list.get(i));
                }
                res.add(listTmp);
                s = e;
            }
            e++;
        }
        List<Integer> listTmp = new ArrayList();
        for(int i = 0 ; i < s ; i++){
            listTmp.add(list.get(i));
        }
        for(int i = e ; i < list.size() ; i++){
            listTmp.add(list.get(i));
        }
        res.add(listTmp);
        return res;
    }



// tle
class Solution {
    public int removeBoxes(int[] boxes) {
        List<Integer> list = new ArrayList();
        for(int i : boxes) list.add(i);
        return dfs(list , 0);
    }
    private int dfs(List<Integer> list , int cur){
        if(list.size() == 0) return cur;
        int max = 0;
        for(List<Integer> canList : split(list)){
            int len = list.size() - canList.size();
            max = Math.max(max , dfs(canList , len * len ));
        }
        return max + cur;
    }
    private static List<List<Integer>> split(List<Integer> list){
        List<List<Integer>> res = new ArrayList();
        int s = 0 , e = 0;
        while(e < list.size()){
            if(list.get(s) != list.get(e)){
                List<Integer> listTmp = new ArrayList();
                for(int i = 0 ; i < s ; i++){
                    listTmp.add(list.get(i));
                }
                for(int i = e ; i < list.size() ; i++){
                    listTmp.add(list.get(i));
                }
                res.add(listTmp);
                s = e;
            }
            e++;
        }
        List<Integer> listTmp = new ArrayList();
        for(int i = 0 ; i < s ; i++){
            listTmp.add(list.get(i));
        }
        for(int i = e ; i < list.size() ; i++){
            listTmp.add(list.get(i));
        }
        res.add(listTmp);
        return res;
    }
}