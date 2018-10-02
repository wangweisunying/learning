// Given a string s, partition s such that every substring of the partition is a palindrome.

// Return all possible palindrome partitioning of s.

// Example
// Given s = "aab", return:

// [
//   ["aa","b"],
//   ["a","a","b"]
// ]
public class Solution {
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList();
        if(s == null || s.length() == 0){
            return res;
        }
        dfs(s , res , new ArrayList() , 0);
        return res;
    }
    private void dfs(String s , List<List<String>> res , List<String> cur , int index){
        if(index == s.length()){
            res.add(new ArrayList(cur));
            return;
        }
        // key part!
        for(int j = index + 1 ; j <= s.length() ; j++){
            String tmp = s.substring(index , j);
            if(isPalindrome(tmp)){
                cur.add(tmp);
                dfs(s , res , cur , j );
                cur.remove(cur.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String s){
        int ss = 0 , ee = s.length() - 1;
        while(ss < ee){
            if(s.charAt(ss) == s.charAt(ee)){
                ss++;
                ee--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}





























public class Solution {
    /*
     * @param s: A string
     * @return: A list of lists of string
     */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList();
        if(s == ""){
            return res;
        }
        
        combination(res , s , 0 , new ArrayList());
        return res;
    }
    private void combination(List<List<String>> res , String s , int startIndex , List<String> curComb){
        if(startIndex == s.length()){ //出口不是 s.length() - 1 最后一位还是需要加入到curComb 
            res.add(new ArrayList(curComb));
        }



        for(int i = startIndex ; i < s.length() ; i++){
            String tmp = s.substring(startIndex,i + 1);
            if(!isPalindrome(tmp)){
                continue;
            }
            curComb.add(tmp);
            combination(res, s , i + 1 , curComb);
            curComb.remove(curComb.size() - 1);
        }
    }
    private boolean isPalindrome(String s){
        for(int i = 0 , j = s.length() - 1 ; i < s.length() ; i++ , j-- ){
            if(s.charAt(i) != s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}