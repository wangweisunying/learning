// 486. Predict the Winner
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed by the player 2 
// and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player.
//  This continues until all the scores have been chosen. The player with the maximum score wins.

// Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.

// Example 1:
// Input: [1, 5, 2]
// Output: False
// Explanation: Initially, player 1 can choose between 1 and 2. 
// If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
// So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
// Hence, player 1 will never be the winner and you need to return False.
// Example 2:
// Input: [1, 5, 233, 7]
// Output: True
// Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
// Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
// Note:
// 1 <= length of the array <= 20.
// Any scores in the given array are non-negative integers and will not exceed 10,000,000.
// If the scores of both players are equal, then player 1 is still the winner.

// The dp[i][j] saves how much more scores that the first-in-action player will get from i to j than the second player.
//  First-in-action means whomever moves first. You can still make the code even shorter but I think it looks clean in this way.






//dp
public boolean PredictTheWinner(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n];
    for (int i = 0; i < n; i++) { 
        dp[i][i] = nums[i]; 
    }
    for (int len = 1; len < n; len++) {
        for (int i = 0; i < n - len; i++) {
            int j = i + len;
            dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
        }
    }
    return dp[0][n - 1] >= 0;
}


//在 l 到 r 区间中 我和对手的分差!~!!
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return getMaxScoreDif(nums , 0 , nums.length - 1) >= 0;
    }
    private int getMaxScoreDif(int[] nums , int l , int r){
        if(l == r) return nums[l];

        //在 l 到 r 区间中 我和对手的分差 （-  getMaxScoreDif(nums , l + 1 , r) 对手和我的分差 - 一下就是我和对手的分差！1）
        return Math.max(nums[l] - getMaxScoreDif(nums , l + 1 , r) , nums[r] -  getMaxScoreDif(nums , l , r - 1));
    }
}
// memo search

//在 l 到 r 区间中 我和对手的分差!!
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];
        for(int[] x : memo)Arrays.fill(x, -1);
        
        return getMaxScoreDif(nums , 0 , nums.length - 1 , memo) >= 0;
    }
    private int getMaxScoreDif(int[] nums , int l , int r , int[][] memo){
        if(l == r) return nums[l];
        if(memo[l][r] != -1) return memo[l][r];
        //在 l 到 r 区间中 我和对手的分差 （-  getMaxScoreDif(nums , l + 1 , r) 对手和我的分差 - 一下就是我和对手的分差！1）
        return memo[l][r] = Math.max(nums[l] - getMaxScoreDif(nums , l + 1 , r , memo) , nums[r] -  getMaxScoreDif(nums , l , r - 1 ,memo));
    }
}




// bit manipulation memo dfs
// 所有game thery

class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int sum = 0;
        for(int i : nums) sum += i;
        if(sum == 0) return true;
        return dfs(nums , 0 , 0, 0 , new boolean[nums.length] , new HashMap());
    }
    private boolean dfs(int[] nums , int mine , int other ,int ct , boolean[] visited , Map<String, Boolean> memo ){
        if(ct == nums.length) return mine >= other;
        String key = hash(visited) + "_" + mine;
        if(memo.containsKey(key))return memo.get(key);
        int i = 0;
        while(i < nums.length && visited[i]) i++;
        int j = nums.length - 1;
        while(j >= 0 && visited[j]) j--;
        
        visited[i] = true;
        if(!dfs(nums , other , mine + nums[i], ct + 1 , visited , memo )){
            visited[i] = false;
            memo.put(key, true);
            return true;
        }
        visited[i] = false;

        visited[j] = true;
        if(!dfs(nums , other , mine + nums[j], ct + 1 , visited , memo )){
            visited[j] = false;
            memo.put(key, true);
            return true;
        }
        visited[j] = false;
        memo.put(key, false);
        return false;
    }
    private int hash(boolean[] can){
        int key = 0;
        for(int i = 0 ; i < can.length ; i++){
            if(can[i]) key |= 1<<i;
        }
        return key;
    }
}

// [0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0]
// Output:
// false
// Expected:
// true


// 裸搜dfs 也能过 testcase 小 更快
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int sum = 0;
        for(int i : nums) sum += i;
        if(sum == 0) return true;
        return dfs(nums , 0 , 0, 0 , new boolean[nums.length]);
    }
    private boolean dfs(int[] nums , int mine , int other ,int ct , boolean[] visited){
        if(ct == nums.length) return mine >= other;
        int i = 0;
        while(i < nums.length && visited[i]) i++;
        int j = nums.length - 1;
        while(j >= 0 && visited[j]) j--;
        visited[i] = true;
        if(!dfs(nums , other , mine + nums[i], ct + 1 , visited )){
            visited[i] = false;
            return true;
        }
        visited[i] = false;

        visited[j] = true;
        if(!dfs(nums , other , mine + nums[j], ct + 1 , visited )){
            visited[j] = false;
            return true;
        }
        visited[j] = false;
        return false;
    }
}
