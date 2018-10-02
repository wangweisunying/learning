// 582. Word Break II
// Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

// Return all such possible sentences.

// Example
// Gieve s = lintcode,
// dict = ["de", "ding", "co", "code", "lint"].

// A solution is ["lint code", "lint co de"].
public class Solution {
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        HashMap<String , List<String>> map = new HashMap();
        return dfs();
    }
    private List<String> dfs(String s,Set<String> set , HashMap<String , List<String>> map){
        if(map.containsKey(s)){
            return map.get(s);
        }
        List<String> res = new ArraYlist();
        if(s == null || s.length() ==0){
            return res;
        }
        if(set.contains(s)){
            res.add(s);
        }

        for(int i = 1 ; i <= s.length() ; i++){
            String start = s.substring(0 , i);
            if(!set.contains(start)){
                continue;
            }
            List<String> restWords = dfs(s.substring(i), set , map);
            for(String rest : restWords){
                res.add(start + " " + rest);
            }
        }
        map.put(s , res);
        return res;
    }
}




public class Solution {
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
     String ss;
    public List<String> wordBreak(String s, Set<String> set) {
        ss = s;
        char[] arr = s.toCharArray();
        List<String> res = new ArrayList();



        HashSet<Character> preSet = new HashSet();
        for(String x : set){
            for(char ch : x.toCharArray()){
                preSet.add(ch);
            }
        }
        for(char ch : arr){
            if(!preSet.contains(ch)){
                return res;
            }
        }



        
        dfs(res , arr , set , "" , 0);
        return res;
    }
    private void dfs(List<String> res , char[] arr , Set<String> set , String cur , int index){
        if(cur.replaceAll(" ","").equals(ss)){
            res.add(cur.trim());
            return;
        }
        String tmp = "";
        for(int i = index ; i < arr.length ; i++){
            tmp += arr[i];
            if(!set.contains(tmp)){
                continue;
            }
            dfs(res, arr , set , cur + " " + tmp, i + 1);
        }
    }
}








































public class Solution {
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> dict) {
        HashMap<String , ArrayList<String>> map = new HashMap();
        return dfs(s , dict , map);
    }
    private ArrayList<String> dfs(String s, Set<String> dict , HashMap<String , ArrayList<String>> map ){
        if(map.containsKey(s)){
            return map.get(s);
        }
        ArrayList<String> res = new ArrayList();
        if(s == null || s.length() ==0){
            return res;
        }
        if(dict.contains(s)){
            res.add(s);
        }

        for(int e = 1 ; e <= s.length() ; e ++){
            String startWord = s.substring(0 , e);
            if(!dict.contains(startWord)){
                continue;
            }
            
            ArrayList<String> restWords = dfs(s.substring(e) , dict , map);
            
            for(String rest : restWords){
                res.add(startWord + " " + rest);
            }
        }
        map.put(s,res);
        return res;
    }
}





public class Solution {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String , ArrayList<String>> map = new HashMap();
        return dfs(s, dict , map);
    }
    private ArrayList<String> dfs(String s , Set<String> dict , Map<String , ArrayList<String>> map){
        if(map.containsKey(s)){
            return map.get(s);
        }
        
        ArrayList<String> res  = new ArrayList();
        if(s == null){
            return res;
        }

        if(dict.contains(s)){
            res.add(s);
        }
        
        for(int i = 1 ; i < s.length() ; i++){
            String startWord = s.substring(0 , i); 
            if(!dict.contains(startWord)){
                continue;
            }
            
            String restWord = s.substring(i);
            ArrayList<String> restResult = dfs(restWord , dict , map);
            for(String tmp :  restResult){
                res.add(startWord + " " + tmp);
            }
        }
        map.put(s,res);
        return res;

    }
}




public class Solution {
    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Map<String , ArrayList<String>> map = new HashMap();
        return dfs(s, dict , map);
    }
    private ArrayList<String> dfs(String s , Set<String> dict , Map<String , ArrayList<String>> map){
        if(map.containsKey(s)){
            return map.get(s);
        }
        
        ArrayList<String> res  = new ArrayList();
        if(s == null){
            return res;
        }

        if(dic.contains(s)){
            res.add(s);
        }
        
        for(int i = 1 ; i < s.length() ; i++){
            String startWord = s.substring(0 , i);
            if(!dic.contains(startWord)){
                continue;
            }
            
            String restWord = s.substring(i);
            ArrayList<String> restResult = dfs(restWord , dict , map);
            for(String tmp :  restResult){
                res.add(startWord + " " + tmp);
            }
        }
        map.put(s,res);
        return res;

    }
}
























//超时
public class Solution {
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> res = new ArrayList();
        if(s == null){
            return res;
        }
        dfs(res , s , wordDict , 0 , "" , "");
        return res;
    }
    private void dfs(List<String> res , String s , Set<String> wordDict ,int startIndex , String cur ,String base ){
        if(s.equals(base.replaceAll(" ",""))){
            res.add(base.trim());
            return;
        }
        if(startIndex == s.length()){
            return;
        }
            
        if(wordDict.contains(cur + s.charAt(startIndex))){
            
            dfs(res , s , wordDict , startIndex + 1 , "", base + " " + cur + s.charAt(startIndex));
            dfs(res , s , wordDict , startIndex + 1 , cur + s.charAt(startIndex) , base);
        }
        else{
            
            dfs(res , s , wordDict , startIndex + 1 , cur + s.charAt(startIndex) , base);
               
        }
 
    }
}


