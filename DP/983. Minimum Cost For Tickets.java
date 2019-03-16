// 983. Minimum Cost For Tickets
// Medium

// 203

// 3

// Favorite

// Share
// In a country popular for train travel, you have planned some train travelling one year in advance.  
// The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

// Train tickets are sold in 3 different ways:

// a 1-day pass is sold for costs[0] dollars;

// a 7-day pass is sold for costs[1] dollars;
// a 30-day pass is sold for costs[2] dollars.
// The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, 
// then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

// Return the minimum number of dollars you need to travel every day in the given list of days.

 

// Example 1:

// Input: days = [1,4,6,7,8,20], costs = [2,7,15]
// Output: 11
// Explanation: 
// For example, here is one way to buy passes that lets you travel your travel plan:
// On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
// On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
// On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
// In total you spent $11 and covered all the days of your travel.
// Example 2:

// Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
// Output: 17
// Explanation: 
// For example, here is one way to buy passes that lets you travel your travel plan:
// On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
// On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
// In total you spent $17 and covered all the days of your travel.
 

// Note:

// 1 <= days.length <= 365
// 1 <= days[i] <= 365
// days is in strictly increasing order.
// costs.length == 3
// 1 <= costs[i] <= 1000

//dp 将找index 问题 离散成为 365 fix index

class Solution {
    public int mincostTickets(int[] days, int[] costs) {

        boolean[] daysIncluded = new boolean[366];
        for(int day : days) daysIncluded[day] = true;
        
        int[] f = new int[366];
        for(int i = 1 ; i <= 365 ; i++){
            if(!daysIncluded[i]){
                f[i] = f[i - 1];
                continue;
            }
            f[i] = f[i - 1] + costs[0];
            f[i] = Math.min(f[i] , f[Math.max(i - 7 , 0)] + costs[1]);
            f[i] = Math.min(f[i] , f[Math.max(i - 30 , 0)] + costs[2]);
        }
        return f[365];
    }
}











// memo search 
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        Map<Integer , Integer> memo = new HashMap();
        return dfs(days , costs , 0 , memo);
    }
    private int dfs(int[] days , int[] costs , int s , Map<Integer , Integer> memo ){
        if(memo.containsKey(s)) return memo.get(s);
        if(s >= days.length) return 0;
        //1day
        int res = dfs(days , costs , s + 1 , memo) + costs[0];
        //7day

        // 注意 + 1 找下一个index
        res = Math.min(dfs(days , costs , bs(days , days[s] + 6) + 1 ,memo) + costs[1] , res);
        res = Math.min(dfs(days , costs , bs(days , days[s] + 29) + 1,memo) + costs[2] , res);
        memo.put(s , res);
        return res;
    }
    private int bs(int[] days , int target){
        int s = 0 , e = days.length - 1 ;
        while(s + 1 < e){
            int mid = (e - s) / 2  + s;
            if(days[mid] == target){
                return mid;
            }
            else if(days[mid] < target){
                s = mid;
            }
            else{
                e = mid;
            }
        }
        if(days[e] <= target )return e;
        return s;
    }
}