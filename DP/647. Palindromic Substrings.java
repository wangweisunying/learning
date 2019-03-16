// 647. Palindromic Substrings


// Share
// Given a string, your task is to count how many palindromic substrings in this string.

// The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

// Example 1:

// Input: "abc"
// Output: 3
// Explanation: Three palindromic strings: "a", "b", "c".
 

// Example 2:

// Input: "aaa"
// Output: 6
// Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

// Note:

// The input string length won't exceed 1000.
// judge whether a string is a palidrome ,
//state transfer : dp[i][j] = dp[i+1][j] + dp[i][j-1] - dp[i+1][j-1] + ( s.chatAt(i) == s.charAt(j) && s(i + 1 ~ j-1) is Palidrome) ? 1 : 0;
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        boolean[][] isP = new boolean[n][n];
        for(int i = n - 1 ; i >= 0 ; i--){
            for(int j = i ; j < n ; j++){
                if(i == j){
                    f[i][j] = 1;
                    isP[i][j] = true;
                } 
                else if(i + 1 == j ){
                    f[i][j] = s.charAt(i) == s.charAt(j) ? 3 : 2;
                    isP[i][j] = s.charAt(i) == s.charAt(j) ? true : false;
                } 
                else{
                    if(s.charAt(i) == s.charAt(j)){
                        isP[i][j] = isP[i + 1][j - 1];
                    }
                    // 状态转移  
                    f[i][j] = f[i + 1][j] + f[i][j - 1] - f[i + 1][j - 1] + (isP[i][j] ? 1 : 0);
                } 
            }
        }      
        return f[0][n - 1];
    }
}