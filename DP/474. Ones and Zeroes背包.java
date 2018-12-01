// 474. Ones and Zeroes
// DescriptionHintsSubmissionsDiscussSolution
// In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

// For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

// Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

// Note:
// The given numbers of 0s and 1s will both not exceed 100
// The size of given string array won't exceed 600.
// Example 1:
// Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
// Output: 4

// Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
// Example 2:
// Input: Array = {"10", "0", "1"}, m = 1, n = 1
// Output: 2

// Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

// 背包问题 
// f[i][j][k] standfor make up  i , j count when judge from to the K  , no init cuz making 0 , 0 has only 0 length
// f[i][j][k] =     max(f[i][j][k - 1] ,                       f[i-ct(0)[j - ct(1)][k-1]);  
//                    not chose(from k -1 layer)           chose(from k - 1 layer)  
// 
// 




class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] nums = new int[strs.length][2];
        for(int i = 0 ; i < strs.length ; i++){
            nums[i] = getNumber(strs[i]);
        }
        int[][][] f = new int[m + 1][n + 1][nums.length + 1];
        for(int i = 0; i <= m ; i++){
            for(int j = 0; j <= n ; j++){
                // next loop wont effect the condion : each coin only once here  , cuz you only consider k -1 and k layer or put the k most out side loop as follows 
                for(int k = 1 ; k <= nums.length ; k++){
                    f[i][j][k] = f[i][j][k - 1];
                    if(i - nums[k - 1][0] >= 0 && j - nums[k - 1][1] >= 0 ){
                        f[i][j][k] = Math.max(f[i][j][k] , f[i - nums[k - 1][0]][j - nums[k - 1][1]][k - 1] + 1);
                    }
                }
            }
        }
        return f[m][n][nums.length];
    }
    private int[] getNumber(String s){
        int[] res = new int[2];
        for(char c : s.toCharArray()){
            if(c == '0') res[0]++;
            if(c == '1') res[1]++;
        }
        return res;
    }
}
// 背包问题 
// f[k][i][j] standfor make up  i , j count when judge from to the K  , no init cuz making 0 , 0 has only 0 length
// f[k][i][j] =     max(f[k - 1] [i][j], f[k-1][i-ct(0)[j - ct(1)]);  
//               not chose(from k -1 layer)           chose(from k - 1 layer)  
// 
// 


class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] nums = new int[strs.length][2];
        for(int i = 0 ; i < strs.length ; i++){
            nums[i] = getNumber(strs[i]);
        }
        
        int[][][] f = new int[nums.length + 1][m + 1][n + 1];

        for(int k = 1 ; k <= nums.length ; k++){
            for(int i = 0; i <= m ; i++){
                for(int j = 0; j <= n ; j++){
                    f[k][i][j] = f[k - 1][i][j];
                    if(i - nums[k - 1][0] >= 0 && j - nums[k - 1][1] >= 0 ){
                        f[k][i][j] = Math.max(f[k][i][j]  , f[k - 1][i - nums[k - 1][0]][j - nums[k - 1][1]] + 1);
                    }
                }
            }
        }
        return f[nums.length][m][n];
    }
    private int[] getNumber(String s){
        int[] res = new int[2];
        for(char c : s.toCharArray()){
            if(c == '0') res[0]++;
            if(c == '1') res[1]++;
        }
        return res;
    }
}