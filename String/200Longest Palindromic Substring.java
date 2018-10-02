//  Longest Palindromic Substring
// Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

// Example
// Given the string = "abcdzdcab", return "cdzdc".

// Challenge
// O(n2) time is acceptable. Can you do it in O(n) time.
//dp f[i][j] 判定 i 到j 是否为回文串？
public class Solution {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        String res = "";
        if(s == null || s.length() == 0){
            return res;
        }
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for(int i = n - 1 ; i >= 0 ; i--){
            for(int j = i ; j < n ; j++ ){
                if(i == j){
                    f[i][j] = true;
                }
                else if(i == j - 1){
                    f[i][j] = s.charAt(i) == s.charAt(j) ? true : false;
                    
                }
                else{
                    f[i][j] = f[i + 1][j - 1] && s.charAt(i) == s.charAt(j) ? true : false;
                }
                if( f[i][j] && res.length() < j - i + 1){
                    res = s.substring(i , j + 1);
                }
            }
        }
        return res;

    }
}






//中心扩散法 O(n)
public class Solution {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        String res = "";
        if(s == null || s.length() == 0){
            return res;
        }

        int max = Integer.MIN_VALUE;
        for(int i = 0 ; i < s.length() ; i++){
            String s1 = helper(i , i , s);
            String s2 = helper(i , i + 1 ,s);
            String x = s1.lenght() > s2.length()? s1 : s2;
            if(max < x.length()){
                max = x.length();
                res = x;
            }
        }
        return res;
    }
    private String helper(int l , int r ,String s){
        while(l >= 0 && r < s.length()){
            if(s.charAt(l) == s.charAt(r)){
                l--;
                r++;
            }
            else{
                break;
            }   
        }
        return s.substring(l + 1, r);
    }
}


