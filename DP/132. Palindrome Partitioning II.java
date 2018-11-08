// 132. Palindrome Partitioning II


// Given a string s, partition s such that every substring of the partition is a palindrome.

// Return the minimum cuts needed for a palindrome partitioning of s.

// Example:

// Input: "aab"
// Output: 1
// Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.


class Solution {
    public int minCut(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        boolean[][] isPali = new boolean[n][n];
        int[] cut = new int[n];//represents the min cut end with j;
        for(int j = 0 ; j < n ; j++){
            int curMin = j; // max cut to get palindrome end with s.substring(0 , j + 1);
            for(int i = 0 ; i <= j ;i++ ){
                if(i == j){
                    isPali[i][j] = true;
                }
                else if (i + 1 == j){
                    isPali[i][j] = s.charAt(i) == s.charAt(j);
                }
                else{
                    isPali[i][j] = isPali[i + 1][ j - 1] && s.charAt(i) == s.charAt(j);
                }

                if(isPali[i][j]){
                    if(i - 1 >= 0 ){
                        curMin = Math.min(cut[i - 1] + 1 , curMin); //cut[i -1] already make substring( 0 , i) a palidrome   0 , j already judge before this i , j happens
                    }
                    else{
                        curMin = 0;
                    }
                    
                }

            }
            cut[j] = curMin;
        }
        return cut[n - 1];

    }
    
}













// This can be solved by two points:

// cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
// If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
// The 2nd point reminds us of using dp (caching).

// a   b   a   |   c  c
//                 j  i
//        j-1  |  [j, i] is palindrome
//    cut(j-1) +  1
// Hope it helps!

// public int minCut(String s) {
//     char[] c = s.toCharArray();
//     int n = c.length;
//     int[] cut = new int[n];
//     boolean[][] pal = new boolean[n][n];
    
//     for(int i = 0; i < n; i++) {
//         int min = i;
//         for(int j = 0; j <= i; j++) {
//             if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
//                 pal[j][i] = true;  
//                 min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
//             }
//         }
//         cut[i] = min;
//     }
//     return cut[n - 1];
// }