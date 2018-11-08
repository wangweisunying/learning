
// Best Time to Buy and Sell Stock II
// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
//  However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

// Example
// Given an example [2,1,2,0,1], return 2

public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        
        int[] f = new int[prices.length]; //represent the max value got at day i;
        f[0] = 0;
        for(int i = 1; i < prices.length ; i++){
            f[i] = f[i - 1] + (prices[i] - prices[i - 1] > 0 ? prices[i] - prices[i - 1] : 0);
        }
        return f[prices.length - 1];
    }
}

public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0){
            return 0;
        }
        
        int curMax = 0; //represent the max value got at day i;
  
        for(int i = 1; i < prices.length ; i++){
            curMax = curMax + (prices[i] - prices[i - 1] > 0 ? prices[i] - prices[i - 1] : 0);
        }
        return curMax;
    }
}


























public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        int sum  = 0;
        for(int i  = 1 ; i < prices.length ; i ++){
            if(prices[i - 1] < prices[i]){
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }
}