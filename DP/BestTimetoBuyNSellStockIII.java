// Best Time to Buy and Sell Stock III
// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete at most two transactions.

// Example
// Given an example [4,4,6,1,1,4,2,5], return 6.

public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices.length == 1){
            return 0;
        }
        int n = prices.length;
        int[][] f = new int[n + 1][5];
        
        f[0][0] = 0; // 买卖 0 次
        int i , j ;
        for(i = 1 ; i < 5 ; i++){
            f[0][i] = Integer.MIN_VALUE;
        }
        //5 stage 
        
        for(i = 2 ; i <=n ; i ++){
            for( j = 0 ; j < 5 ; j++){
                if(j == 0){ // no trade
                    f[i][j] = 0;
                }
                if( j == 2|| j == 4){ //sell stock
                    f[i][j] = Math.max(f[i - 1][j] , f[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
                if( j == 1 || j == 3){ // buy stock
                    f[i][j] = Math.max(f[i - 1][j] + prices[i - 1] - prices[i - 2], f[i - 1][j - 1]);
                }
            }
        }
        return Math.max(Math.max(f[n][2], f[n][4]), 0);
        
    }
}





