// Implement wildcard pattern matching with support for '?' and '*'.

// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).
// The matching should cover the entire input string (not partial).

// Example
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "*") → true
// isMatch("aa", "a*") → true
// isMatch("ab", "?*") → true
// isMatch("aab", "c*a*b") → false
public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        if(s == null || p == null){
            return false;
        }
        //memorization 
        boolean[][] visited = new boolean[ s.length() ][ p.length() ];
        boolean[][] memo = new boolean[ s.length() ][ p.length() ];

        return isMatch(s , 0 , p , 0 , visited , memo);
    }
        //from start to end 
    private boolean isMatch(String s, int sIndex , String p , int pIndex ,boolean[][] visited , boolean[][] memo){
        //exit
        
        //pindex to the end  sindex must in the end
        if(pIndex == p.length()){
            return sIndex == s.length();
        }
        //sindex to the end pindex must in the end or pindex must be * from now on;
        if(sIndex == s.length()){
            return pIndex == p.length() || allStar(p,pIndex);
        }
        //put here to void out of Bound exception 
        if(visited[sIndex][pIndex]){
            return memo[sIndex][pIndex];
        }
        
        //if pidnex is * then  sIndex + 1 musht match pIndex aaaa  *aa  or  sIndex match  pIndex + 1  aa  *aa draw recursion tree
        boolean match = false;
        if(p.charAt(pIndex) == '*'){
            match = isMatch( s , sIndex + 1 , p , pIndex , visited , memo ) || isMatch( s , sIndex , p , pIndex + 1 , visited , memo );
        }
        else{
            match = charMatch(s.charAt(sIndex) , p.charAt(pIndex)) && isMatch(s, sIndex + 1 , p , pIndex + 1 , visited , memo);  
        }
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = match;
        return match;

    }
    private boolean charMatch(char a , char b){
        if(b == '?'){
            return true;
        }
        return a == b;
    }
    private boolean allStar(String p , int pIndex){
        for(int i = pIndex ; i < p.length() ; i ++){
            if(p.charAt(i) != '*'){
                return false;
            }
        }
        return true;
    }
}

















public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        return isMatchHelper(s, 0, p, 0, memo, visited);
    }
    
    private boolean isMatchHelper(String s, int sIndex,
                                  String p, int pIndex,
                                  boolean[][] memo,
                                  boolean[][] visited) {
        // 如果 p 从pIdex开始是空字符串了，那么 s 也必须从 sIndex 是空才能匹配上
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }
        
        // 如果 s 从 sIndex 是空，那么p 必须全是 * 
        if (sIndex == s.length()) {
            return allStar(p, pIndex);
        }
        
        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }
        
        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match;
        
        if (pChar == '*') {
            match = isMatchHelper(s, sIndex, p, pIndex + 1, memo, visited) ||
                isMatchHelper(s, sIndex + 1, p, pIndex, memo, visited); 
        } else {
            match = charMatch(sChar, pChar) &&
                isMatchHelper(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }
        
        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = match;
        return match;
    }
    
    private boolean charMatch(char sChar, char pChar) {
        return (sChar == pChar || pChar == '?');
    }
    
    private boolean allStar(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}