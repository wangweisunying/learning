
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

    //dfs 的嵌套 状态转移 的运用 ， 大问题化为小问题 ，记忆化
    public List<String> wordBreak(String s, Set<String> wordDict) {
        
        Map<String , List<String>> map = new HashMap();
        return dfs(s , wordDict , map);
    }

    private List<String> dfs(String s , Set<String> dict , Map<String , List<String>> map ){
        if(map.containsKey(s)){
            return map.get(s);
        }

        List<String> list = new ArrayList();
        if(dict.contains(s)){
            list.add(s);
        }
        for(int i = 1 ; i < s.length() ; i++){
            String startWord = s.substring(0 , i);
            if(!dict.contains(startWord)){
                continue;
            }
            String rest = s.substring(i);
            List<String> restWord = dfs(rest , dict , map);
            for(String resttmp : restWord){
                list.add(startWord + " " + resttmp); 
            }
        }
        map.put(s , list);
        return list;
    }
}