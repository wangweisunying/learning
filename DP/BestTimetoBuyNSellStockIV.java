// Best Time to Buy and Sell Stock IV
// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete at most k transactions.

// Example
// Given prices = [4,4,6,1,1,4,2,5], and k = 2, return 6.

// Challenge
// O(nk) time.


public class Solution {
    /**
     * @param K: An integer
     * @param prices: An integer array
     * @return: Maximum profit
     */
    public int maxProfit(int K, int[] prices) {
        if(prices.length < 2){
            return 0;
        }
        int n = prices.length;
        int i  , j;
        if(K > n / 2){
            int res = 0;
            for(i = 1 ; i < n; i++){
                if(prices[i] > prices[i - 1]){
                    res += prices[i] - prices[i - 1];
                }
            }
            return res;
        }
        
        int[][] f = new int[n + 1][2*K + 1];
        
        // init 
        f[0][0] = 0;
        for( j = 0 ; j < 2 * K + 1 ; j ++){
            f[0][j] = Integer.MIN_VALUE;
        }

        
        for( i = 2 ; i <=n ; i++){
            for( j = 0 ; j < 2 * K + 1 ; j ++){
                if(j == 0){
                    f[i][j] = 0;
                    continue;
                }
                if(j % 2 == 0){
                            //无股票     不买                 刚刚卖掉   
                    f[i][j] = Math.max(f[i - 1][j] , f[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
                else{      //have stock       没有卖  价格随市场变化                          刚刚买入 市场变化为0
                    f[i][j] = Math.max(f[i - 1][j] + prices[i - 1] - prices[i - 2] , f[i - 1][j - 1]);
                }
            }
        }
        int max = 0;
        //比较没有股票
        for( i = 0 ; 2 * i < 2 * K + 1 ; i++ ){
            max = Math.max(max , f[n][2 * i]);
        }
        return max;

    }
}