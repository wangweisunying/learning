
// Given a pattern and a string str, find if str follows the same pattern.

// Here follow means a full match, such that there is a bijection between a letter in pattern and 
// a non-empty substring in str.(i.e if a corresponds to s, then b cannot correspond to s. For example, given pattern = "ab", str = "ss", return false.)

// Example
// Given pattern = "abab", str = "redblueredblue", return true.
// Given pattern = "aaaa", str = "asdasdasdasd", return true.
// Given pattern = "aabb", str = "xyzabcxzyabc", return false.


//类似通配符 和 regiliar expressio 
public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<String , String> map = new HashMap();
        HashMap<String , String> re_map = new HashMap();
        return dfs(pattern , str , map , re_map);
    }
    private boolean dfs(String pattern , String str , HashMap<String , String> map , HashMap<String , String> re_map){
        
        if(pattern.length() == 0){
            return str.length() == 0;
        }
        if(map.containsKey(pattern) && map.get(pattern).equals(str)){
            return true;
        }
        boolean match = false;
        String first = pattern.substring(0 , 1);
        String rest = pattern.substring(1);
        if(map.containsKey(first)){
            String tmp = map.get(first);
            match = str.startsWith(tmp);
            if(match){
                match &= dfs(rest , str.substring(tmp.length()) , map , re_map);
            }
        }
        else{
            for(int i = 1 ; i <= str.length() ; i++){
                String start = str.substring(0 , i);
                String end = str.substring(i);
                if(re_map.containsKey(start) && !re_map.get(start).equals(first)){
                    continue;
                }
                map.put(first , start);
                re_map.put(start , first);
                match = dfs(rest , end , map , re_map);
                if(match){
                    return true;
                }
                map.remove(first);
                re_map.remove(start);
            }
        }    
        return match; 
    }
}

public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap<String , String> map = new HashMap();
        HashSet<String> set = new HashSet();
        return dfs(pattern , str , map , set);
    }
    private boolean dfs(String pattern , String str , HashMap<String , String> map , HashSet<String> set){
        
        if(pattern.length() == 0){
            return str.length() == 0;
        }
        if(map.containsKey(pattern) && map.get(pattern).equals(str)){
            return true;
        }
        boolean match = false;
        String first = pattern.substring(0 , 1);
        String rest = pattern.substring(1);
        if(map.containsKey(first)){
            String tmp = map.get(first);
            match = str.startsWith(tmp);
            if(match){
                match &= dfs(rest , str.substring(tmp.length()) , map , set);
            }
        }
        else{
            for(int i = 1 ; i <= str.length() ; i++){
                String start = str.substring(0 , i);
                String end = str.substring(i);
                if(set.contains(start)){
                    continue;
                }
                map.put(first , start);
                set.put(start);
                match = dfs(rest , end , map , set);
                if(match){
                    return true;
                }
                map.remove(first);
                set.remove(start);
            }
        }    
        return match; 
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


public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return match(pattern, str, map, set);
    }
    
    private boolean match(String pattern,
                          String str,
                          Map<Character, String> map,
                          Set<String> set) {
        if (pattern.length() == 0) {
            return str.length() == 0;
        }
        
        Character c = pattern.charAt(0);
        if (map.containsKey(c)) {
            if (!str.startsWith(map.get(c))) {
                return false;
            }
            
            return match(
                pattern.substring(1),
                str.substring(map.get(c).length()),
                map,
                set
            );
        }
        
        for (int i = 0; i < str.length(); i++) {
            String word = str.substring(0, i + 1);
            if (set.contains(word)) {
                continue;
            }
            map.put(c, word);
            set.add(word);
            if (match(pattern.substring(1),
                      str.substring(i + 1),
                      map,
                      set)) {
                return true;              
            }
            set.remove(word);
            map.remove(c);
        }
        
        return false;
    }
}

   void bfs(Map<String, List<String>> map, Map<String, Integer> distance,
            String start, String end, Set<String> dict) {
        Queue<String> q = new LinkedList<String>();
        q.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            map.put(s, new ArrayList<String>());
        }
        
        while (!q.isEmpty()) {
            String crt = q.poll();

            List<String> nextList = expand(crt, dict);
            for (String next : nextList) {
                map.get(next).add(crt);
                if (!distance.containsKey(next)) {
                    distance.put(next, distance.get(crt) + 1);
                    q.offer(next);
                }
            }
        }
    }