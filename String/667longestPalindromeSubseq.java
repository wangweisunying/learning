
// Longest Palindromic Subsequence
// Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

// Example
// Given s = "bbbab" return 4
// One possible longest palindromic subsequence is "bbbb".

// dp f[i][j] 代表 i 到 j 最大
public class Solution {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[][] f = new int[n][n];

        for(int i = n - 1 ; i >=0 ; i--){
            for(int j = i ; j < n ; j++){
                if(i == j){
                    f[i][j] == 1;
                    continue;
                }
                if( i == j - 1){
                    f[i][j] =  s.charAt(i) == s.charAt(j) ?  2 : 1;
                    continue;
                }
                f[i][j] = f[i + 1][j - 1] + (s.charAt(i) == s.charAt(j) ?  2 : 0 );
                f[i][j] = Math.max(Math.max(f[i][j] , f[i + 1][j]),f[i][j - 1]);
            }
        }
        return f[0][n - 1];


    }
}

public class Solution {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
     
     
    // //DP 解法！！
    
    // 状态
    // 从后往前找因为最终要求找从头到尾的结果
    // 初始化f[i][i] = 1,
    // 
    
    
    
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[][] f = new int[n][n];
        // for(int i = 0 ; i < n ; i++){
        //     f[i][i] = 1;
        // }
        for(int i = n - 1; i >= 0 ; i--){
            for(int j = i  ; j < n ; j++){
                if(i == j){
                    f[i][j] = 1; //初始化f[i][i] = 1,
                    continue;
                }
                if(i + 1== j && s.charAt(i) == s.charAt(j)){
                    f[i][j] = f[i + 1][j] + 1; //考虑极限情况
                    continue;
                }
                if(s.charAt(i) == s.charAt(j)){ //前面的已经滤掉 极限情况
                    f[i][j] = f[i + 1][ j - 1] + 2;
                    continue;
                }
                if(s.charAt(i) != s.charAt(j)){
                    f[i][j] = Math.max(f[i + 1][j] , f[i][j - 1]);
                    continue;
                }
            }
        }
        return f[0][n - 1];
        
    }
}