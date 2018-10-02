// BestTimetoBuyandSellStock
// Say you have an array for which the ith element is the price of a given stock on day i.

// If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

// Example
// Given array [3,2,3,1,2], return 1.

public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 0 || prices.length == 1){
            return 0;
        }
        int curMax = 0;
        int max = 0;
        int buy = prices[0];
        int i = 1 , n = prices.length;
        for(; i < n ; i ++){
            if(prices[i] - buy >= 0){
                curMax = prices[i] - buy;
            }
            else{
                curMax = 0;
                buy = prices[i];
            }
            max = Math.max(curMax , max);
        }
        return max;
    }
}

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;  //just remember the smallest price
        int profit = 0;
        for (int i : prices) {
            min = i < min ? i : min;
            profit = (i - min) > profit ? i - min : profit;
        }

        return profit;
    }
}