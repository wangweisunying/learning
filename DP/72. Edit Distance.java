// Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

// You have the following 3 operations permitted on a word:

// Insert a character
// Delete a character
// Replace a character
// Example 1:

// Input: word1 = "horse", word2 = "ros"
// Output: 3
// Explanation: 
// horse -> rorse (replace 'h' with 'r')
// rorse -> rose (remove 'r')
// rose -> ros (remove 'e')
// Example 2:

// Input: word1 = "intention", word2 = "execution"
// Output: 5
// Explanation: 
// intention -> inention (remove 't')
// inention -> enention (replace 'i' with 'e')
// enention -> exention (replace 'n' with 'x')
// exention -> exection (replace 'n' with 'c')
// exection -> execution (insert 'u')
// 
//f[i][j] reprent word1 end with i , and word2 end with j, minmumn operation!
//f[i][j] = f[i][j - 1] + 1;  insert 
//  i = a , j = b  (j - 1 = a)
// word1 a 
// word2 a b

//f[i][j] = f[i - 1][j] + 1;  delete 
//  i = a , j = b  (i - 1 = a)
// word1 a b
// word2 a 
//f[i][j] = f[i - 1][j - 1] + 1; replace

//draw the dp map!;
//     '' h  o  r  s  e
// ''  0  1  2  3  4  5
// r   1  1  2  2  3  4
// o   2  2  1  2  3  4
// s   3  3  2  2  2  3

//only relating to the upper row ,so 
//
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] f = new int[2][n + 1]; // need to consider "" senario   OR  word.charAt(0) will start at the first character!(wrong)
        // for(int i = 0 ; i <= m ; i++){
        //     f[i][0] = i;
        // }
        for(int j = 0 ; j <= n ; j++){
            f[0][j] = j;
        }

        //滚动数组切换
        int oldIndex = 0;
        int newIndex = 1;
        //from very first character now.
        for(int i = 1 ; i <= m ; i++){
            f[newIndex][0] = i;
            for(int j = 1 ; j <= n ; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    f[newIndex][j] = f[oldIndex][j - 1];
                }
                else{
                    f[newIndex][j] = Math.min(Math.min(f[oldIndex][j] ,f[newIndex][j - 1]) , f[oldIndex][j - 1 ]) + 1;
                }
            }
            int tmp = newIndex;
            newIndex = oldIndex;
            oldIndex = tmp;
        }
        return f[oldIndex][n];
    }
}




class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] f = new int[m + 1][n + 1]; // need to consider "" senario   OR  word.charAt(0) will start at the first character!(wrong)
        for(int i = 0 ; i <= m ; i++){
            f[i][0] = i;
        }
        for(int j = 0 ; j <= n ; j++){
            f[0][j] = j;
        }

        //from very first character now.
        for(int i = 1 ; i <= m ; i++){
            for(int j = 1 ; j <= n ; j++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    f[i][j] = f[i - 1][j - 1];
                }
                else{
                    f[i][j] = Math.min(Math.min(f[i - 1][j] ,f[i][j - 1]) , f[i - 1][j - 1 ]) + 1;
                }
            }
        }
        return f[m][n];
    }
}