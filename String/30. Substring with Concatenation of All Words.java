// 30. Substring with Concatenation of All Words
// You are given a string, s, and a list of words, words, that are all of the same length.
//  Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly 
// once and without any intervening characters.

// Example 1:

// Input:
//   s = "barfoothefoobarman",
//   words = ["foo","bar"]
// Output: [0,9]
// Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
// The output order does not matter, returning [9,0] is fine too.
// Example 2:

public class Solution {
    public ArrayList<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(s.equals("") || words.length == 0){
            return result;
        }
        HashMap<String, Integer> toFind = new HashMap<String, Integer>();
        HashMap<String, Integer> found = new HashMap<String, Integer>();
        for(String word : words){
            toFind.put(word , toFind.getOrDefault(word , 0 ) + 1);
        }
        int m = words.length;
        int n = words[0].length();
        for(int i = 0 ; i < s.length() ; i++){
            int j = i;
            int ct = 0;
            found.clear();
            while(j + n <= s.length()){
                String tmp = s.substring(j , j + n);
                if(toFind.containsKey(tmp)){
                    ct++;
                    found.put(tmp , found.getOrDefault(tmp , 0 ) + 1);
                    if(found.get(tmp) > toFind.get(tmp)){
                        ct = 0;
                        break;
                    }
                }
                else{
                    ct = 0;
                    break;
                }
                
                if(ct == m){ //once met the request break!
                    result.add(i);
                    break;
                }
                j += n;
            }
        }
        return result; 
        
    }    
}



public class Solution {
    public ArrayList<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(s.equals("") || words.length == 0){
            return result;
        }
        HashMap<String, Integer> toFind = new HashMap<String, Integer>();
        HashMap<String, Integer> found = new HashMap<String, Integer>();
        for(String word : words){
            if(toFind.containsKey(word)){
                toFind.put( word , toFind.get(word) + 1 );
            }
            else{
                toFind.put( word , 1 );
            }
        }
        int m = words.length;
        int n = words[0].length();
        for(int i = 0 ; i < s.length() ; i++){
            int j = i;
            int ct = 0;
            found.clear();
            while(j + n <= s.length()){
                String tmp = s.substring(j , j + n);
                if(toFind.containsKey(tmp)){
                    ct++;
                    if(found.containsKey(tmp)){
                        found.put(tmp , found.get(tmp) + 1);
                    }
                    else{
                        found.put(tmp , 1);
                    }
                    if(found.get(tmp) > toFind.get(tmp)){
                        ct = 0;
                        break;
                    }
                }
                else{
                    ct = 0;
                    break;
                }
                j += n;
                if(ct == m){ //once met the request break!
                    result.add(i);
                    break;
                }
            }
        }
        return result; 
        
    }    
}







































public class Solution {
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashMap<String, Integer> toFind = new HashMap<String, Integer>();
        HashMap<String, Integer> found = new HashMap<String, Integer>();
        int m = L.length, n = L[0].length();
        for (int i = 0; i < m; i ++){
            if (!toFind.containsKey(L[i])){
                toFind.put(L[i], 1);
            }
            else{
                toFind.put(L[i], toFind.get(L[i]) + 1);
            }
        }
        for (int i = 0; i <= S.length() - n * m; i ++){
            found.clear();
            int j;
            for (j = 0; j < m; j ++){
                int k = i + j * n;
                String stub = S.substring(k, k + n);
                if (!toFind.containsKey(stub)) break;
                if(!found.containsKey(stub)){
                    found.put(stub, 1);
                }
                else{
                    found.put(stub, found.get(stub) + 1);
                }
                if (found.get(stub) > toFind.get(stub)) break;
            }
            if (j == m) result.add(i);
        }
        return result;
    }
}




public class Solution {
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        HashMap<String, Integer> toFind = new HashMap<String, Integer>();
        HashMap<String, Integer> found = new HashMap<String, Integer>();
        int m = L.length, n = L[0].length();
        for (int i = 0; i < m; i ++){
            if (!toFind.containsKey(L[i])){
                toFind.put(L[i], 1);
            }
            else{
                toFind.put(L[i], toFind.get(L[i]) + 1);
            }
        }
        for (int i = 0; i <= S.length() - n * m; i ++){
            found.clear();
            int j;
            for (j = 0; j < m; j ++){
                int k = i + j * n;
                String stub = S.substring(k, k + n);
                if (!toFind.containsKey(stub)) break;
                if(!found.containsKey(stub)){
                    found.put(stub, 1);
                }
                else{
                    found.put(stub, found.get(stub) + 1);
                }
                if (found.get(stub) > toFind.get(stub)) break;
            }
            if (j == m) result.add(i);
        }
        return result;
    }
}



// time exceeded
class Solution {
    int len;
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList();
        if(s.equals("") || words.length == 0){
            return res;
        }
        List<String> concat_list = new ArrayList();
        Arrays.sort(words);
        len = 0;
        for(String word : words){
            len += word.length();
        }
        dfs(concat_list , words , "",new boolean[words.length]);
       
        for(String concat : concat_list){
            if(!s.contains(concat)){
                continue;
            }
            int start = 0  , end = concat.length();
            while(end <= s.length()){
                if(s.substring(start , end).equals(concat)){
                    res.add(start);
                }
                start ++;
                end ++;
            }
        }
        Collections.sort(res);
        return res;

    }
    private void dfs(List<String> res , String[] words ,String cur , boolean[] visited){
        if(len == cur.length()){
            res.add(cur);
            return;
        }
        for(int i = 0 ; i < words.length ; i++){
            if(visited[i]){
                continue;
            }
            if( i - 1 >= 0 && words[i].equals(words[i - 1])   && !visited[i - 1]){
                continue;
            }
            visited[i] = true;
            dfs(res , words , cur + words[i] , visited);
            visited[i] = false;
        }

    }

}