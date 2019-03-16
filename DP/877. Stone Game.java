// 877. Stone Game

// Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, 
// and each pile has a positive integer number of stones piles[i].

// The objective of the game is to end with the most stones.  
// The total number of stones is odd, so there are no ties.

// Alex and Lee take turns, with Alex starting first. 
//  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row. 
//  This continues until there are no more piles left, at which point the person with the most stones wins.

// Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.

// Example 1:

// Input: [5,3,4,5]
// Output: true
// Explanation: 
// Alex starts first, and can only take the first 5 or the last 5.
// Say he takes the first 5, so that the row becomes [3, 4, 5].
// If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
// If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
// This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 

// Note:

// 2 <= piles.length <= 500
// piles.length is even.
// 1 <= piles[i] <= 500
// sum(piles) is odd.
// Accepted




// 存的是本剧与对手最大差值
class Solution {
    public boolean stoneGame(int[] piles) {
        int[][] f = new int[piles.length][piles.length];
        for(int i = piles.length - 1; i >= 0 ; i--){
            for(int j = i ; j < piles.length ; j++){
                if(i == j){
                    f[i][j] = piles[i];
                }
                else{

                    // 上局的对手是自己 
                    f[i][j] = Math.max(-f[i + 1][j] + piles[i] , -f[i][j - 1] + piles[j]); 
                }
                 
            }
        }
        return f[0][piles.length - 1] > 0;
    }
}


class Solution {
    public boolean stoneGame(int[] piles) {
        int[][][] f = new int[piles.length][piles.length][2];
        for(int i = piles.length - 1; i >= 0 ; i--){
            for(int j = i ; j < piles.length ; j++){
                if(i == j){
                    f[i][j][0] = piles[i];
                    f[i][j][1] = 0;
                }
                else{
                    if(f[i + 1][j][1] + piles[i] >=  f[i][j - 1][1] + piles[j] ){
                        f[i][j][0] = f[i + 1][j][1] + piles[i];
                        f[i][j][1] = f[i + 1][j][0];
                    }
                    else{
                        f[i][j][0] = f[i][j - 1][1] + piles[j];
                        f[i][j][1] = f[i][j - 1][0];
                    } 
                }
                 
            }
        }
        return f[0][piles.length - 1][0] - f[0][piles.length - 1][1] > 0;
    }
}




//  bool stoneGame(vector<int>& p) {
//         int n = p.size();
//         vector<vector<int>> dp(n, vector<int>(n, 0));
//         for (int i = 0; i < n; i++) dp[i][i] = p[i];
//         for (int d = 1; d < n; d++)
//             for (int i = 0; i < n - d; i++)
//                 dp[i][i + d] = max(p[i] - dp[i + 1][i + d], p[i + d] - dp[i][i + d - 1]);
//         return dp[0][n - 1] > 0;
//     }












// memo seacch 
class Solution {
    public boolean stoneGame(int[] piles) {
        boolean[][] visited = new boolean[piles.length][piles.length];
        boolean[][] memo = new boolean[piles.length][piles.length];
        return dfs(piles , 0 , piles.length - 1 , 0 , 0 , visited , memo);        
    }
    private boolean dfs(int[] piles , int s, int e , int me , int opp , boolean[][] visited , boolean[][] memo){
        if(visited[s][e]) return memo[s][e];
        boolean res = false;
        if(s == e){
            res = me + piles[s] > opp;
            visited[s][e] = true;
            memo[s][e] = res;
            return res;
        }
        res = dfs(piles , s + 1 , e , opp , me + piles[s] , visited , memo) ||
                dfs(piles , s , e - 1 , opp , me + piles[e] , visited , memo);
        visited[s][e] = true;
        memo[s][e] = res;        
        return res;  
    }
}