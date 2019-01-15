// 464. Can I Win
// DescriptionHintsSubmissionsDiscussSolution
// In the "100 game," two players take turns adding, to a running total, any integer from 1..10. The player who first causes the running total to reach or exceed 100 wins.

// What if we change the game so that players cannot re-use integers?

// For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

// Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

// You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

// Example

// Input:
// maxChoosableInteger = 10
// desiredTotal = 11

// Output:
// false

// Explanation:
// No matter which integer the first player choose, the first player will lose.
// The first player can choose an integer from 1 up to 10.
// If the first player choose 1, the second player can only choose integers from 2 up to 10.
// The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
// Same with other integers chosen by the first player, the second player will always win.

class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if(desiredTotal <= 0) return true;
        if((maxChoosableInteger + 1) * maxChoosableInteger / 2 < desiredTotal) return false;
        boolean[] can = new boolean[maxChoosableInteger + 1];
        return dfs(can , desiredTotal , new HashMap());
    }
    private boolean dfs(boolean[] can , int rest , Map<Integer , Boolean> memo){
        //对手留给你的rest 
        if(rest <= 0) return false;
        int key = hash(can);  
        if(memo.containsKey(key))return memo.get(key);
        boolean res = false;
        for(int i = 1 ; i < can.length ; i++){
            if(can[i])continue;
            can[i] = true;
            if(!dfs(can , rest - i , memo)){
                res = true;
                can[i] = false;
                break;
            }
            can[i] = false;
        }
        memo.put(key , res);
        return res;
    }    
    private int hash(boolean[] can){
        int key = 0;
        for(int i = 0 ; i < can.length ; i++){
            if(can[i]) key |= 1<<i;
        }
        return key;
    }
}