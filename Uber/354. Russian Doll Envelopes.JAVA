// 354. Russian Doll Envelopes
// DescriptionHintsSubmissionsDiscussSolution
// You have a number of envelopes with widths and heights given as a pair of integers (w, h).
// One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

// What is the maximum number of envelopes can you Russian doll? (put one inside other)

// Note:
// Rotation is not allowed.

// Example:

// Input: [[5,4],[6,4],[6,7],[2,3]]
// Output: 3 
// Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


    // sort Arr by width
    //  i index envelopes can contain max count;
    // f[i] = max(f[i] , 1 + f[j] (0 ~ i - 1))
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        
        Arrays.sort(envelopes , (a , b) -> (a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]));
        int n = envelopes.length;
        int[] f = new int[n];
        Arrays.fill(f , 1);
        int max = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < i ; j++){
                if(envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1])f[i] = Math.max(f[i] , 1 + f[j]);
            }
            max = Math.max(max , f[i]);
        }
        return max;
    }
}





// memo search MLE (MEMORY EXCEED)
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int m = 0;
        int n = 0;
        for(int[] env : envelopes){
            m = Math.max(m , env[0]);
            n = Math.max(n , env[1]);
        }
        int[][] memo = new int[m + 1][n + 1];
        for(int [] x : memo) Arrays.fill(x , -1);
        return dfs(memo , envelopes , new boolean[envelopes.length] , 0 , 0);   
    }
    private int dfs(int[][] memo , int[][] envelopes , boolean[] visited , int w ,int h){
        if(memo[w][h] != -1) return memo[w][h];
        int res = 0;
        for(int i = 0 ; i < envelopes.length ; i++){
            if(visited[i] || w >= envelopes[i][0] || h >=  envelopes[i][1]) continue;
            visited[i] = true;
            res = Math.max(res , 1 + dfs(memo , envelopes , visited ,  envelopes[i][0] ,  envelopes[i][1]));
            visited[i] = false;
        }
        memo[w][h] = res;
        return res;
    }
}