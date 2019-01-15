// 516. Longest Palindromic Subsequence
// Medium


// Share
// Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

// Example 1:
// Input:

// "bbbab"
// Output:
// 4
// One possible longest palindromic subsequence is "bbbb".
// Example 2:
// Input:

// "cbbd"
// Output:
// 2
// One possible longest palindromic subsequence is "bb".


class Solution {
    public int longestPalindromeSubseq(String s) {
        int n =  s.length();
        int[][] f = new int[n][n];
        for(int i = n - 1 ; i >= 0 ; i --){
            for(int j = i ; j < n ; j ++){
                if(i == j) f[i][j] = 1;
                else if( i + 1 == j) f[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
                else{
                
                    f[i][j] = Math.max(f[i + 1][j] , f[i][j - 1]);
                    f[i][j] = Math.max(f[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ? 2 : 0) , f[i][j]);
                }
            }
        }
        return f[0][n - 1];
    }
}