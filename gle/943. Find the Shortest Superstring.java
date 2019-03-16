// 943. Find the Shortest Superstring
// Given an array A of strings, find any smallest string that contains each string in A as a substring.

// We may assume that no string in A is substring of another string in A.

 
// Example 1:

// Input: ["alex","loves","leetcode"]
// Output: "alexlovesleetcode"
// Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
// Example 2:

// Input: ["catg","ctaagt","gcta","ttca","atgcatc"]
// Output: "gctaagttcatgcatc"
 

// Note:

// 1 <= A.length <= 12
// 1 <= A[i].length <= 20
// ["catg","ctaagt","gcta","ttca","atgcatc"]
// "gctaagttcatgcatc"


//  7s almost tle dfs + 剪枝
class Solution {
    public String shortestSuperstring(String[] str) {
        String[] res = new String[1];
        res[0] = String.join("", str);
        int target = (1<<str.length) - 1;
        int[][] cost = new int[str.length][str.length];
        for(int i = 0 ; i < str.length ; i ++){
            for(int j = 0 ; j < str.length ; j++){
                if(i == j) continue;
                cost[i][j] = getRes(str[i] ,str[j]);
            }
        } 
        dfs(res ,"", str , 0 , target , cost ,  0);
        return res[0];
    }
    private void dfs(String[] res , String cur , String[] str  , int curStatus , int target , int[][] cost , int preIndex){
        if(res[0].length() <= cur.length()) return;
        if(curStatus == target){
            if(res[0].length() > cur.length()){
                res[0] = cur;
            }
            return;
        }      
        for(int i = 0 ; i < str.length ; i++){
            if(((curStatus >> i) & 1) == 1 )continue;
            curStatus |= 1<<i;
            String next = "";
            if(cur.equals("")){
                next = str[i];
            }
            else{
                next = cur + str[i].substring(str[i].length() - cost[preIndex][i]);
            }
            dfs(res , next, str , curStatus , target , cost , i);
            curStatus ^= 1<<i;
        }
    }
    private int getRes(String cur , String next){
        if(cur.contains(next)) return 0;
        String tmp = next;
        while(tmp.length() > 0){
            if(cur.endsWith(tmp)){
                return next.length() - tmp.length();
            }
            tmp = tmp.substring(0 , tmp.length() - 1);
        }

        return next.length();
    }
}



// graph[i][j] means the length of string to append when A[i] followed by A[j]. eg. A[i] = abcd, A[j] = bcde, then graph[i][j] = 1
// Then the problem becomes to: find the shortest path in this graph which visits every node exactly once. This is a Travelling Salesman Problem.
// Apply TSP DP solution. Remember to record the path.
// Time complexity: O(n^2 * 2^n)

class Solution {
    public String shortestSuperstring(String[] A) {
        int n = A.length;
        int[][] graph = new int[n][n];
        // build the graph
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = calc(A[i], A[j]);
                graph[j][i] = calc(A[j], A[i]);
            }
        }
        int[][] dp = new int[1 << n][n];
        int[][] path = new int[1 << n][n];
        int last = -1, min = Integer.MAX_VALUE;
		
        // start TSP DP
        for (int i = 1; i < (1 << n); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    int prev = i - (1 << j);
                    if (prev == 0) {
                        dp[i][j] = A[j].length();
                    } else {
                        for (int k = 0; k < n; k++) {
                            if (dp[prev][k] < Integer.MAX_VALUE && dp[prev][k] + graph[k][j] < dp[i][j]) {
                                dp[i][j] = dp[prev][k] + graph[k][j];
                                path[i][j] = k;
                            }
                        }
                    }
                }
                if (i == (1 << n) - 1 && dp[i][j] < min) {
                    min = dp[i][j];
                    last = j;
                }
            }
        }
		
        // build the path
        StringBuilder sb = new StringBuilder();
        int cur = (1 << n) - 1;
        Stack<Integer> stack = new Stack<>();
        while (cur > 0) {
            stack.push(last);
            int temp = cur;
            cur -= (1 << last);
            last = path[temp][last];
        }
		
        // build the result
        int i = stack.pop();
        sb.append(A[i]);
        while (!stack.isEmpty()) {
            int j = stack.pop();
            sb.append(A[j].substring(A[j].length() - graph[i][j]));
            i = j;
        }
        return sb.toString();
    }
    private int calc(String a, String b) {
        for (int i = 1; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return b.length() - a.length() + i;
            }
        }
        return b.length();
    }
}