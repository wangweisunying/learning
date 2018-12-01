// 49. Group Anagrams
// DescriptionHintsSubmissionsDiscussSolution
// Given an array of strings, group anagrams together.

// Example:

// Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
// Output:
// [
//   ["ate","eat","tea"],
//   ["nat","tan"],
//   ["bat"]
// ]
// Note:

// All inputs will be in lowercase.
// The order of your output does not matter.


//HashMap + String 19 ms
class Solution {
    public List<List<String>> groupAnagrams(String[] str) {
        Map<String , List<String>> map = new HashMap();
        for(String word : str){
            char[] arr = word.toCharArray();
            //利用sort
            Arrays.sort(arr);
            String cur = new String(arr);
            if(map.containsKey(cur)){
                map.get(cur).add(word);
            }
            else{
                map.put(cur , new ArrayList(Arrays.asList(word)));
            }
        }
        return new ArrayList(map.values());

    }    
}



//dfs 裸搜 727ms
class Solution {
    public List<List<String>> groupAnagrams(String[] str) {
        List<List<String>> res = new ArrayList();
        if(str == null || str.length == 0) return res; 
        boolean[] visited = new boolean[str.length]; 
        for(int i = 0 ; i < str.length ; i++){
            if(visited[i]) continue;
            visited[i] = true;
            dfs(res ,new ArrayList(Arrays.asList(str[i])) , str , visited , i + 1);
        }
        return res;
    }
    private boolean dfs(List<List<String>> res , List<String> cur , String[] str , boolean[] visited , int index){
        int i = index;
        for(; i < str.length ; i++){
            if(visited[i] || !match(str[i] , cur.get(0))){
                continue;
            }
            visited[i] = true;
            cur.add(str[i]);
            if(dfs(res , cur , str , visited , i + 1)){
                return true;
            }
        }
        if(i == str.length){
            res.add(new ArrayList(cur));
            return true;
        }
        return false;
    }
    private boolean match(String s , String t){
        if(s.length() != t.length()) return false;
        int[] hash = new int[26];
        for(int i = 0 ; i < s.length() ; i++){
            hash[s.charAt(i) - 'a']++;
            hash[t.charAt(i) - 'a']--;
        }
        int res = 0;
        for(int i : hash) res|= i;
        return res == 0;
    }
}