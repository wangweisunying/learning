// 730. Count Different Palindromic Subsequences
// DescriptionHintsSubmissionsDiscussSolution
// Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.

// A subsequence of a string S is obtained by deleting 0 or more characters from S.

// A sequence is palindromic if it is equal to the sequence reversed.

// Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.

// Example 1:
// Input: 
// S = 'bccb'
// Output: 6
// Explanation: 
// The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
// Note that 'bcb' is counted only once, even though it occurs twice.
// Example 2:
// Input: 
// S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
// Output: 104860361
// Explanation: 
// There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
// Note:

// The length of S will be in the range [1, 1000].
// Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.

//ab
class Solution {
    public int countPalindromicSubsequences(String S) {
        
    }
}

//palidrome 需要从后往前遍历 
class Solution {
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        

        //palidrome 需要从后往前遍历 
        for(int i = n - 1 ; i >= 0; i --){
            for(int j = i ; j < n ; j ++){
                if(i == j){
                    f[i][j] = 1;
                }
                else if(s.charAt(i) != s.charAt(j)){
                    f[i][j] = f[i + 1][j] + f[i][j - 1] - f[i + 1][j - 1];
                }
                else{
                    int low = i + 1;
                    int high = j - 1;
                    while(low <= high && s.charAt(low) != s.charAt(j)){
                        low ++;
                    }
                    while(low <= high && s.charAt(high) != s.charAt(i)){
                        high --;
                    }
                    if(low > high){
                        f[i][j] = 2 * f[i + 1][j - 1] + 2;
                    }
                    if(low == high){
                        f[i][j] = 2 * f[i + 1][j - 1] + 1;
                    }
                    if(low < high){
                        f[i][j] = 2 * f[i + 1][j - 1] - f[low + 1][high - 1];
                    }
                }
                f[i][j] = f[i][j] < 0 ? 1000000007 + f[i][j] : f[i][j] % 1000000007;
                System.out.println(" i = " + i + " j = "+ j + "   =" + f[i][j] );
            }
            
            
        }
        return f[0][n-1];

    }
}


// "aab"
// Your stdout
//  i = 0 j = 1   =2
//  i = 0 j = 2   =1
//  i = 1 j = 2   =2



// The basic idea of DP is easy to understand, I maintain DP[i][j] to record in substring from i to j(included), 
// the number of palindrome without duplicate. Then we consider two cases of the DP equation:

// when s.charAt(i) != s.charAt(j):
// dp[i][j] = dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];

// When s.charAt(i) == s.charAt(j):
// the situation get much more complex and I fix a lot the wrong answers. I have comment the branches where which kind of test cases are considered.

class Solution {
    public int countPalindromicSubsequences(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        char[] chs = s.toCharArray();
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;   // Consider the test case "a", "b" "c"...
        }

        for(int distance = 1; distance < len; distance++){
            for(int i = 0; i < len - distance; i++){
                int j = i + distance;
                if(chs[i] == chs[j]){
                    int low = i + 1;
                    int high = j - 1;

              /* Variable low and high here are used to get rid of the duplicate*/

                    while(low <= high && chs[low] != chs[j]){
                        low++;
                    }
                    while(low <= high && chs[high] != chs[j]){
                        high--;
                    }
                    if(low > high){
                        // consider the string from i to j is "a...a" "a...a"... where there is no character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aba" while i = 0 and j = 2:  dp[1][1] = 1 records the palindrome{"b"}, 
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"b"}, 
                         and additional time as {"aba"}. The reason why 2 counted is that we also count {"a", "aa"}. 
                         So totally dp[i][j] record the palindrome: {"a", "b", "aa", "aba"}. 
                         */ 

                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;  
                    } 
                    else if(low == high){ // aaa 中 a , 加上后 aaa  , aa ,a , a和a重复了 所以是+1；
                        // consider the string from i to j is "a...a...a" where there is only one character 'a' inside the leftmost and rightmost 'a'
                       /* eg:  "aaa" while i = 0 and j = 2: the dp[i + 1][j - 1] records the palindrome {"a"}.  
                         the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a"}, 
                         and additional time as {"aaa"}. the reason why 1 counted is that 
                         we also count {"aa"} that the first 'a' come from index i and the second come from index j. So totally dp[i][j] records {"a", "aa", "aaa"}
                        */
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;  
                    }
                    else{
                        // consider the string from i to j is "a...a...a... a" where there are at least two character 'a' close to leftmost and rightmost 'a'
                       /* eg: "aacaa" while i = 0 and j = 4: the dp[i + 1][j - 1] records the palindrome {"a",  "c", "aa", "aca"}. 
                          the reason why dp[i + 1][j  - 1] * 2 counted is that we count dp[i + 1][j - 1] one time as {"a",  "c", "aa", "aca"}, 
                          and additional time as {"aaa",  "aca", "aaaa", "aacaa"}.  Now there is duplicate :  {"aca"}, 
                          which is removed by deduce dp[low + 1][high - 1]. So totally dp[i][j] record {"a",  "c", "aa", "aca", "aaa", "aaaa", "aacaa"}
                          */
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1];  // a...a..a....a  中间的部分需要计算2次！
                    }
                }
                else{
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  //s.charAt(i) != s.charAt(j)
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][len - 1];
    }
}