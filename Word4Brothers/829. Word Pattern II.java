
// 829. Word Pattern II
// Given a pattern and a string str, find if str follows the same pattern.

// Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.(i.e if a corresponds to s, then b cannot correspond to s.
//  For example, given pattern = "ab", str = "ss", return false.)

// Example
// Given pattern = "abab", str = "redblueredblue", return true.
// Given pattern = "aaaa", str = "asdasdasdasd", return true.
// Given pattern = "aabb", str = "xyzabcxzyabc", return false.

// Notice
// You may assume both pattern and str contains only lowercase letters.


public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    boolean found;
    public boolean wordPatternMatch(String pattern, String str) {
        found = false;
        Map<Character , String> memo  = new HashMap();
        Set<String> inMemo = new HashSet();
        dfs(pattern , str , memo , inMemo);
        return found;
    }
    private boolean dfs(String pattern, String str , Map<Character , String> memo , Set<String> inMemo){
        
        
        if(found){  
            return true;
        }
        if(pattern.length() == 0){
            if(str.length() == 0){
                found = true;
            }
            return str.length() == 0;
        }
        if(str.length() == 0){
            if(pattern.length() == 0){
                found = true;
            }
            return pattern.length() == 0;
        }
        
        boolean res = false;
        if(memo.containsKey(pattern.charAt(0))){
            String tmp = memo.get(pattern.charAt(0));
            res = str.startsWith(tmp);
            if(res){
                res &= dfs(pattern.substring(1) , str.substring(tmp.length()) , memo , inMemo);
            }
        }
        else{
            for(int i = 1 ; i <= str.length() ; i++){
                String tmp = str.substring(0 , i);
                if(inMemo.contains(tmp)){
                    continue;
                }
                char now = pattern.charAt(0);
                memo.put(now , tmp);
                inMemo.add(tmp);
                res = dfs(pattern.substring(1) , str.substring(i) , memo , inMemo);
                inMemo.remove(tmp);
                memo.remove(now);
            }
        }
        return res;
    }
}


public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */

    public boolean wordPatternMatch(String pattern, String str) {

        Map<Character , String> memo  = new HashMap();
        Set<String> inMemo = new HashSet();
        return dfs(pattern , str , memo , inMemo);

    }
    private boolean dfs(String pattern, String str , Map<Character , String> memo , Set<String> inMemo){

        
        if(pattern.length() == 0){
            return str.length() == 0;
        }
        if(str.length() == 0){
            return pattern.length() == 0;
        }
        
        boolean res = false;
        if(memo.containsKey(pattern.charAt(0))){
            String tmp = memo.get(pattern.charAt(0));
            res = str.startsWith(tmp);
            if(res){
                res &= dfs(pattern.substring(1) , str.substring(tmp.length()) , memo , inMemo);
            }
        }
        else{
            for(int i = 1 ; i <= str.length() ; i++){
                String tmp = str.substring(0 , i);
                if(inMemo.contains(tmp)){
                    continue;
                }
                char now = pattern.charAt(0);
                memo.put(now , tmp);
                inMemo.add(tmp);
                res = dfs(pattern.substring(1) , str.substring(i) , memo , inMemo);
                if(res){
                    return true; //跳出back tricking 
                }
                inMemo.remove(tmp);
                memo.remove(now);
            }
        }
        return res;
    }
}



public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character , String> reMap = new HashMap(); // save relationship 
        HashSet<String> set = new HashSet(); //save history

        return dfs(pattern , str , reMap , set);
    }
    public boolean dfs(String pattern, String str , HashMap<Character , String> reMap , HashSet<String> set){
        if(pattern.length() == 0){
            return str.length() == 0;
        }
        char c = pattern.charAt(0);
        if(reMap.containsKey(c)){
            if(!str.startsWith(reMap.get(c))){
                return false;
            }
            return dfs(pattern.substring(1) , str.substring(reMap.get(c).length()) , reMap , set);
        }

        for(int i = 0 ; i < str.length() ; i ++){
            String word = str.substring(0 , i + 1);
            if(set.contains(word)){// 一一对应 map 只能单一对应 所以需要加上set
                continue;
            }
            reMap.put(c, word);
            set.add(word);
            if (dfs(pattern.substring(1),
                      str.substring(i + 1),
                      reMap,
                      set)) {
                return true;              
            }
            set.remove(word);
            reMap.remove(c);            

        }
        return false;    
    }
}