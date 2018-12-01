
// 829. Word Pattern II
// Given a pattern and a string str, find if str follows the same pattern.

// Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
// (i.e if a corresponds to s, then b cannot correspond to s.
//  For example, given pattern = "ab", str = "ss", return false.)

// Example
// Given pattern = "abab", str = "redblueredblue", return true.
// Given pattern = "aaaa", str = "asdasdasdasd", return true.
// Given pattern = "aabb", str = "xyzabcxzyabc", return false.

// Notice
// You may assume both pattern and str contains only lowercase letters.

//memo search ， decrease  str and pattern separately. 
//HashSet record the String already saved , map mapping the char to String ; aa ->ab is false use set to avoid that 
public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character , String > map = new HashMap();
        Set<String> set = new HashSet();
        return dfs(pattern , str , map , set);
    }
    private boolean dfs(String pattern, String str ,Map<Character , String> map , Set<String> set){
        if(pattern.length() == 0){
            return str.length() == 0;
        }
        if(str.length() == 0 ){
            return pattern.length() == 0;
        }
        if(map.containsKey(pattern.charAt(0))){
            String now = map.get(pattern.charAt(0));
            if(str.startsWith(now)){
                return dfs(pattern.substring(1) , str.substring(now.length()) , map , set);  
            }
        }
        else{
            for(int i = 1 ; i <= str.length() ; i++){
                String can = str.substring(0 , i);
                if(set.contains(can)) continue;
                set.add(can);
                map.put(pattern.charAt(0) , can);
                if(dfs(pattern.substring(1) , str.substring(can.length()) , map , set)){
                    return true;
                }
                set.remove(can);
                map.remove(pattern.charAt(0) );
            }
        }
        return false;
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
        Set<String> inMemo = new HashSet(); // one on one only a can not map to bb and cc 
        return dfs(pattern , str , memo , inMemo);
    }
    private boolean dfs(String pattern, String str , Map<Character , String> memo , Set<String> inMemo){
        if(pattern.length() == 0){
            return str.length() == 0;
        }
        if(str.length() == 0){
            return pattern.length() == 0;
        }
        
        char c = pattern.charAt(0);
        if(memo.containsKey(c)){
            if(str.startsWith(memo.get(c))){
                if(dfs(pattern.substring(1) , str.substring(memo.get(c).length()) , memo , inMemo)){
                    return true;
                }
            }
        }
        else{
            for(int i = 0 ; i < str.length() ; i++){
                String substring = str.substring(0 , i + 1);
                if(inMemo.contains(substring)){
                    continue;
                }
                inMemo.add(substring);
                memo.put(c , substring);
                if(dfs(pattern.substring(1) , str.substring(i + 1) , memo , inMemo)){
                    return true;
                }
                memo.remove(c);
                inMemo.remove(substring);
            }
        }
        return false;
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