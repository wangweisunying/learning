// 583. Delete Operation for Two Strings
// DescriptionHintsSubmissionsDiscussSolution
// Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can 
// delete one character in either string.

// Example 1:
// Input: "sea", "eat"
// Output: 2
// Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
// Note:
// The length of given words won't exceed 500.
// Characters in given words can only be lower-case letters.

//longest common subsequences DP 
//F[I][J] 前长度为i 与 长度为j 的 最长的longest common subsequences
class Solution {
    public int minDistance(String A, String B) {
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
        return A.length() + B.length() - 2 * f[m][n];
    }
}