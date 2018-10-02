
// BestTimetoBuyandSellStockwithTransactionFee
// Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

// You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
//  You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

// Return the maximum profit you can make.

// Example
// Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
// Output: 8
// Explanation: The maximum profit can be achieved by:
// Buying at prices[0] = 1
// Selling at prices[3] = 8
// Buying at prices[4] = 4
// Selling at prices[5] = 9
// The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.

// [1,4,6,2,8,3,10,14]
// 3
// Output
// 5
// Expected
// 13
public class Solution {
    /**
     * @param prices: a list of integers
     * @param fee: a integer
     * @return: return a integer
     */
    public int maxProfit(int[] prices, int fee) {
        int cashwoStock = 0;
        int cashwStorck = -prices[0];
        for(int i = 1; i < prices.length ; i ++){
            cashwoStock = Math.max(cashwoStock , cashwStorck + prices[i] - fee );
            chaswStock = Math.max(chaswStock ,cashwoStock - prices[i]);
        }
        return cashwoStock;
    }
}