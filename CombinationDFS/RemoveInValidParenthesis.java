
// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

// Example
// "()())()" -> ["()()()", "(())()"]
// "(a)())()" -> ["(a)()()", "(a())()"]
// ")(" -> [""]


////1.从左到右遍历String获得左括号和右括号的计数
//2.从左到右遍历String，如果碰到左括号或右括号，就删除左括号或右括号，更新计数，然后把剩下的String放入DFS递归，注意当有连续的左括号或者右括号的时候，必须先删前面的
//3.DFS退出的条件是左括号和有括号的计数均为0

public class Solution {
    /**
     * @param s: The input string
     * @return: Return all possible results
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res  = new ArrayList();
        if(s.length() == 0){
            res.add("");
            return res;
        }
        int[] ct = ctLeftRight(s);
        dfs(res , 0 , s , ct[0] , ct[1]);
        return res;
    }
    private void dfs(List<String> res ,int startIndex, String s , int ctleft , int ctright ){
        if(ctleft == 0 && ctright == 0 && isValidString(s)){
            res.add(s);
            return;
        }
        for(int i = startIndex ; i < s.length() ; i++){
            if(i > startIndex && s.charAt(i) == s.charAt(i - 1)){
                continue;
            }
            if(s.charAt(i) == '(' && i < s.length()){
                ctleft -- ;
                dfs(res , i, s.substring(0 , i ) + s.substring( i + 1) , ctleft , ctright );
                ctleft ++ ;
            }
            if(s.charAt(i) == ')' && i < s.length()){
                ctright -- ;
                dfs(res ,i, s.substring(0 , i ) + s.substring( i + 1) , ctleft , ctright );
                ctright ++ ;
            }
            
        }
    }
    private boolean isValidString(String s){
        int[] res = ctLeftRight(s);
        return res[0] == 0 && res[1] == 0;
    }
    private int[] ctLeftRight(String s){ //左右括号计数
        int[] nums = new int[2];
        for(int i = 0 ; i < s.length(); i++){
            if(s.charAt(i) == '('){
                nums[0]++;
            }
            if(s.charAt(i) == ')'){
                if(nums[0] > 0){
                    nums[0] --;
                }else{
                    nums[1]++;
                }
            }
        }
        return nums;
    }
}



