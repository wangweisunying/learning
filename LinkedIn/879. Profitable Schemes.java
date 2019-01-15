// 879. Profitable Schemes
// DescriptionHintsSubmissionsDiscussSolution
// There are G people in a gang, and a list of various crimes they could commit.

// The i-th crime generates a profit[i] and requires group[i] gang members to participate.

// If a gang member participates in one crime, that member can't participate in another crime.

// Let's call a profitable scheme any subset of these crimes that generates at least P profit, and the total number of gang members participating in that subset of crimes is at most G.

// How many schemes can be chosen?  Since the answer may be very large, return it modulo 10^9 + 7.

 

// Example 1:

// Input: G = 5, P = 3, group = [2,2], profit = [2,3]
// Output: 2
// Explanation: 
// To make a profit of at least 3, the gang could either commit crimes 0 and 1, or just crime 1.
// In total, there are 2 schemes.
// Example 2:

// Input: G = 10, P = 5, group = [2,3,5], profit = [6,7,8]
// Output: 7
// Explanation: 
// To make a profit of at least 5, the gang could commit any crimes, as long as they commit one.
// There are 7 possible schemes: (0), (1), (2), (0,1), (0,2), (1,2), and (0,1,2).
 

// Note:

// 1 <= G <= 100
// 0 <= P <= 100
// 1 <= group[i] <= 100
// 0 <= profit[i] <= 100
// 1 <= group.length = profit.length <= 100


// 01 knap sack

// 01 背包 问题 dp 用memo dfs 写一遍
// f[n][G][P] g 个人 P 的profit 的 次数 
class Solution {
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int n = group.length;
        int mod = (int)1e9 + 7;
        int[][][] f = new int[n + 1][G + 1][P + 1];
        f[0][0][0] = 1;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 0 ; j <= G ; j++){
                for(int k = 0 ; k <= P ; k++){
                    f[i][j][k] = f[i - 1][j][k] % mod;
                    if(j - group[i - 1] >= 0){
                        int tmpIndex = Math.max(k - profit[i - 1] , 0);
                        f[i][j][k] += f[i - 1][j - group[i - 1]][tmpIndex] % mod;
                    }
                    f[i][j][k] %= mod;                    
                }
            }
        }
        int res = 0;
        for(int i = 0 ; i <= G ; i++){
            res += f[n][i][P];
            res %= mod;
        }
        return res;
    }
}


// memo search 
class Solution {
    int mod = (int)1e9 + 7;
    public int profitableSchemes(int G, int P, int[] group, int[] profit) {
        int n = group.length;
        int[][][] memo = new int[n][ G + 1][ P + 1];
        for(int[][] x : memo){
            for(int[] y : x) Arrays.fill(y , -1);
        }
        return dfs(memo , G , P , group , profit , 0);
    }
    private int dfs(int[][][] memo , int G ,int P ,int[] group ,int[] profit , int index){
        if(index == group.length) return 0;
        if(memo[index][G][P] != -1) return memo[index][G][P];
        int res = 0;
        res += dfs(memo , G , P , group ,profit ,index + 1)%mod;
        if(G - group[index] >=0 ){
            if( P - profit[index] <= 0 )res++;
            int tmpPro = Math.max(P - profit[index] , 0);
            res += dfs(memo , G - group[index] , tmpPro , group , profit , index +1 )%mod;
        }
        memo[index][G][P] = res%mod;
        return memo[index][G][P];
    }
}