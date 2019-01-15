// 292. Nim Game
/

// Share
// You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. 
// The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

// Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

// Example:

// Input: 4
// Output: false 
// Explanation: If there are 4 stones in the heap, then you will never win the game;
//              No matter 1, 2, or 3 stones you remove, the last stone will always be 
//              removed by your friend.



public boolean canWinNim(int n) {
    return (n % 4 != 0);
}

//tle
class Solution {
    public boolean canWinNim(int n) {
        boolean[] f = new boolean[4];
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= 3 ; j++){
                if(1 - j >= 0)f[1] |= !f[1 - j];
                if(2 - j >= 0)f[2] |= !f[2 - j];
                if(3 - j >= 0)f[3] |= !f[3 - j];
            }
        }
        return f[n % 4];
    }
}





// MLE
class Solution {
    public boolean canWinNim(int n) {
        boolean[] f = new boolean[n + 1];
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= 3 ; j++){
                if(i - j >= 0){
                    f[i] |= !f[i - j];  
                }
            }
        }
        return f[n];
    }
}