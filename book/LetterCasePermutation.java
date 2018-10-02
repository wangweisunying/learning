// Letter Case Permutation
// Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string. 
// Return a list of all possible strings we could create.

// Example
// Input: S = "a1b2"
// Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

// Input: S = "3z4"
// Output: ["3z4", "3Z4"]

// Input: S = "12345"
// Output: ["12345"]

class Solution {
    
    //画出递归树！这是求所有叶子节点的情况
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList();
        if(s == null){
            return res;
        }
        
        dfs(res, s , "" ,0);
        return res;
    }
    private void dfs(List<String> res, String s, String cur ,int startIndex){
        //exit
        if( cur.length() == s.length()){
            res.add(new String(cur));
            // return; // 找所有叶子节点，不需要回溯
        }
               
        else{
            if(Character.isLetter(s.charAt(startIndex))){
                dfs(res, s , cur + Character.toUpperCase(s.charAt(startIndex)) , startIndex + 1);
                dfs(res, s , cur + Character.toLowerCase(s.charAt(startIndex)) , startIndex + 1);
                
            }
            else{
                dfs(res, s , cur + s.charAt(startIndex) , startIndex + 1); 
            }
                       
            
        }
    }
}

public class Solution {
    /**
     * @param S: a string
     * @return: return a list of strings
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList();
        dfs(res , S.toCharArray(), "" , 0);
        return res;
    }
    private void dfs(List<String> res , char[] arr , String cur , int startIndex){
        if(cur.length() == arr.length){
            res.add(cur);
            return;
        }
        if(Character.isLetter(arr[startIndex])){
            dfs(res , arr , cur + Character.toUpperCase(arr[startIndex]) , startIndex + 1);
            dfs(res , arr , cur + Character.toLowerCase(arr[startIndex]) , startIndex + 1);
                
        }else{
            dfs(res , arr , cur + arr[startIndex] , startIndex + 1);
        }
        
    }
}