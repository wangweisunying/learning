// 77. Longest Common Subsequence
// Given two strings, find the longest common subsequence (LCS).

// Your code should return the length of LCS.

// Example
// For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.

// For "ABCD" and "EACB", the LCS is "AC", return 2.

// Clarification
// What's the definition of Longest Common Subsequence?

public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        int m = A.length() , n = B.length();
        int[][] f = new int[m + 1][n + 1];
        for(int i = 1 ; i <= m ; i++){
            for(int j = 1 ; j <= n ; j++){
                if(A.charAt(i - 1) == B.charAt(j - 1)){
                    f[i][j] = f[i - 1 ][j - 1] + 1;
                }
                f[i][j] = Math.max(Math.max(f[i][j] , f[i - 1][j]) , f[i][j -1]); 
            }
        }
        return f[m][n];
    }
}