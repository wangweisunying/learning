// 301. Remove Invalid Parentheses
// DescriptionHintsSubmissionsDiscussSolution
// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

// Note: The input string may contain letters other than the parentheses ( and ).

// Example 1:

// Input: "()())()"
// Output: ["()()()", "(())()"]
// Example 2:

// Input: "(a)())()"
// Output: ["(a)()()", "(a())()"]
// Example 3:

// Input: ")("
// Output: [""]



//count how many minum left and right needed to get the valid answer ct left  ct right 
// use dfs to check all the combination , skip the same elements appear in one straght (same as get rid of dup in subsets)
// i has been deleted ! , so next index  should also be i!
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList();
        int l = 0 , r = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                ++l;
            }
            if(c == ')'){
                if(l <= 0){
                    ++ r;
                    ++ l;
                } 
                --l;
            }
        }
        dfs(s , l , r , res , 0);
        return res;
    }
    private void dfs(String s , int l , int r , List<String> res , int index){
        if(l < 0 || r < 0 ) return;
        if(l == 0 && r == 0 && isValid(s)){
            res.add(s);
            return;
        } 
        for(int i = index ; i < s.length() ; i++){
            if(i > index && s.charAt(i) == s.charAt(i - 1))continue;
            if(s.charAt(i) == '('){
                // i has been deleted ! , so next index  should also be i!
                dfs(s.substring(0 , i) + s.substring(i + 1) , l - 1 , r , res , i);
            }
            if(s.charAt(i) == ')'){
                dfs(s.substring(0 , i) + s.substring(i + 1) , l , r - 1 , res , i);
            }
        }
    }
    private boolean isValid(String s){
        int level = 0;
        for(char c : s.toCharArray()){
            if(c == '('){
                if(level < 0) return false;
                ++level;
            }
            if(c == ')'){
                if(level <= 0) return false;
                --level;
            }
        }
        return level == 0;
    }
}