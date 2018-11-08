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
        if(prices == null || prices.length == 0){
            return 0;
        }
        int n = prices.length;
        int[][] f = new int[n][5]; // represents n day status of the max profit
        // 5 stage status are  0 : 2 tras remain  1 : 1 buy 2 sell : 2 1buy1sell :3 2buy 1 sell  4 : 2buy 2 sell
        for(int i = 0 ; i < n ; i++ ){
            if(i == 0){
                f[i][1] = 0;
                f[i][2] = 0;
                f[i][3] = 0;
                f[i][4] = 0; 
                continue;
            }

            //buy                no profit yet , still hold the preday stock
            f[i][1] = Math.max(f[i - 1][0] , f[i - 1][1] + prices[i] - prices[i - 1] );
            //sell               sell now , not buy yet
            f[i][2] = Math.max(f[i - 1][1] + prices[i] - prices[i - 1] , f[i - 1][2] );
            f[i][3] = Math.max(f[i - 1][2] , f[i - 1][3] + prices[i] - prices[i - 1]);
            f[i][4] = Math.max(f[i - 1][3] + prices[i] - prices[i - 1] , f[i - 1][4] );
        }
        return Math.max(f[n - 1][2] , f[n - 1][4]);
    }
}





































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





