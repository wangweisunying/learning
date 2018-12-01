// 518. Coin Change 2
// DescriptionHintsSubmissionsDiscussSolution
// You are given coins of different denominations and a total amount of money.
//  Write a function to compute the number of combinations that make up that amount.
//  You may assume that you have infinite number of each kind of coin.

// Note: You can assume that

// 0 <= amount <= 5000
// 1 <= coin <= 5000
// the number of coins is less than 500
// the answer is guaranteed to fit into signed 32-bit integer
 

// Example 1:

// Input: amount = 5, coins = [1, 2, 5]
// Output: 4
// Explanation: there are four ways to make up the amount:
// 5=5
// 5=2+2+1
// 5=2+1+1+1
// 5=1+1+1+1+1
 

// Example 2:

// Input: amount = 3, coins = [2]
// Output: 0
// Explanation: the amount of 3 cannot be made up just with coins of 2.
 

// Example 3:

// Input: amount = 10, coins = [10] 
// Output: 1

// state transfer f[i][j] = select pre j coins to get amount i  
//back sack 问题
class Solution {
    public int change(int amount, int[] coins) {
        int[][] f = new int[amount + 1][coins.length + 1];
        for(int i = 0 ; i <= coins.length ; i++){
            f[0][i] = 1;
        }
        for(int i = 1 ; i <= amount ; i++){
            for(int j = 1 ; j <= coins.length ; j ++){
                //dont select j
                f[i][j] = f[i][j - 1];
                //use jth , j can select unlimited times , we need to know how many ways to make up amount j - coins[i-1] by using first j coins
                if(i - coins[j - 1] >= 0){
                    f[i][j] += f[i - coins[j - 1]][j];
                }        
            }
        }
        return f[amount][coins.length];        
    }
}







class Solution {
    public int change(int amount, int[] coins) {
        int[][] f = new int[amount + 1][coins.length + 1];
        for(int i = 0 ; i <= coins.length ; i++){
            f[0][i] = 1;
        }
        for(int i = 1 ; i <= amount ; i++){
            for(int j = 1 ; j <= coins.length ; j++){
                //dont use j th coin
                f[i][j] = f[i][j - 1];
                //use j th coin  since we can use unlimited same coin!!!, we need to know how many ways to make up amount j - coins[i-1] by using first i coins(including ith), which is dp[i][j-coins[i-1]]
                if( i - coins[j - 1] >= 0){
            
                    f[i][j] += f[i - coins[j - 1]][j];
                }
            }
        }
        return f[amount][coins.length];
    }                                                                                                                                      
}


// Firstly, We have been familiar with this transfer function
// f[0] = true
// f[j] = f[j - coins[i]] | f[j] {coins[i] <= j <= amount and j is in decreasing order}
// this is the solution to 0/1 problems
// Secondly, transfer function to this problem as follows:
// f[0] = 1
// f[j] = f[j - coins[i]] + f[j] {coins[i] <= j <= amount }
// for example:
// coin [1, 2] aoumt = 4;
// f[1] = 1 f[2] = 1 f[3] = 1 f[4] = 1
// f[2] = f[2] + f[0] = 2 f[3] = f[1] + f[3] = 2 f[4] = f[2] + f[4] = 3
// Caution:enumerate j in increasing order, if you enumerate j in decreasing order, that means the coins[i] will be just used one time
// Plus if each coin does not only has value, but also has weight. So if we want to use a certain amount to chose the maximum sum of weight of all of these combinations. And each coin could be chosen one time. The transfer funtion willl be:
// f[0] = 0
// f[j] = max(f[j - coins[i]] + weight[i], f[j]} {coins[i] <= j <= amount decreasing}
// Finally, if each coin could be chosen many times, the function will be shifted to :
// f[0] = 0
// f[j] = max(f[j - coins[i]] + weight[i], f[j]} {coins[i] <= j <= amount inrceasing}

// and here is my concise code to this problem

 public static int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int i= 0; i < coins.length; i++) {
    	for (int j = coins[i]; j <= amount; j++) 
    		dp[j] = dp[j - coins[i]] + dp[j];
    }
    return dp[amount];
}


// This is a classic knapsack problem. Honestly, I'm not good at knapsack problem, it's really tough for me.

// dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
// State transition:

// not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.
// using the ith coin, since we can use unlimited same coin, we need to know how many ways to make up amount j - coins[i-1] by using first i coins(including ith), which is dp[i][j-coins[i-1]]
// Initialization: dp[i][0] = 1

// Once you figure out all these, it's easy to write out the code:

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }