// Implement regular expression matching with support for '.' and '*'.

// '.' Matches any single character.
// '*' Matches zero or more of the preceding element.
// The matching should cover the entire input string (not partial).


// The function prototype should be:

// bool isMatch(string s, string p)

// Example
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "a*") → true
// isMatch("aa", ".*") → true
// isMatch("ab", ".*") → true
// isMatch("aab", "c*a*b") → true

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[ m + 1 ][ n + 1];
        
        for(int i = 0 ; i <= m ; i ++){
            for(int j = 0 ; j <= n ; j++){
                if(i == 0  && j == 0 ){
                    f[i][j] = true;
                    continue;
                }
                if(i == 0){
                    if(check(j - 1 , p)){
                        f[i][j] = true;
                    }
                    else{
                        f[i][j] = false;
                    }
                    continue;
                }
                if( j == 0){
                    f[i][j] = false;
                    continue;
                }
                if(p.charAt(j - 1) == '*'){
                    //x * 匹配 one or more 前提是第i个和 第j -1 个match
                    f[i][j] = f[i - 1][j] && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1));
                    //x* 匹配 0 个
                    f[i][j] |= f[i][j - 2];
                    
                }
                else{
                    f[i][j] = f[i - 1][j - 1] && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1));
                }
                
            }
        }
        return f[m][n];
    }
    private boolean check(int index , String p){
        int i = 1 ;
        while(i <= index){
            if(p.charAt(i) != '*'){
                return false;
            }
            i+=2;
        }
        return index % 2 == 1;
        
    }
}



public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        boolean[][] visited = new  boolean[s.length()][p.length()];
        boolean[][] memo = new  boolean[s.length()][p.length()];
        return MathchHelper(s , 0 , p , 0 , visited ,memo);
    }
    private boolean MathchHelper(String s ,int sIndex , String p , int pIndex , boolean[][] visited , boolean[][] memo){
        if(sIndex == s.length()){
            return check(p , pIndex);
        }
        if(pIndex == p.length()){
            return sIndex == s.length();
        }
        if(visited[sIndex][pIndex]){
            return memo[sIndex][pIndex];
        }
        boolean res = false;
        if(pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*'){
            if(p.charAt(pIndex) != '.' && p.charAt(pIndex) != s.charAt(sIndex)){
                res|= MathchHelper(s , sIndex , p , pIndex + 2 ,visited , memo);
            }
            else{
                res|= MathchHelper(s , sIndex , p , pIndex + 2 ,visited , memo) || MathchHelper(s , sIndex + 1 , p , pIndex ,visited , memo);
            }
        }
        else{
            if(p.charAt(pIndex) == '.' || s.charAt(sIndex) == p.charAt(pIndex)){
                res |= MathchHelper(s , sIndex + 1 , p , pIndex + 1 ,visited , memo);
            }
            
        }
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = res;
        return res;
    }
    private boolean check(String p , int pIndex){
        if(pIndex == p.length()){
            return true;
        }
        if(p.charAt(p.length() - 1) != '*'){
            return false;
        }
        pIndex += 1;
        while(pIndex < p.length()){
            if(p.charAt(pIndex) != '*'){
                return false;
            }
            pIndex += 2;
        }
        return true;
    }
}