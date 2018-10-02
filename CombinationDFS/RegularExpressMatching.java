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

public class Solution {
    /**
     * @param s: A string 
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
    public boolean isMatch(String s, String p) {
        boolean[][] visited = boolean[s.length()][p.length()];
        boolean[][] memo = boolean[s.length()][p.length()];
        return MathchHelper(s , 0 , p , 0 , visited ,memo);
    }
    private boolean MathchHelper(String s ,int sIndex , String p , int pIndex , boolean[][] visited , boolean[][] memo){
        re

    }
}