
// 267. Palindrome Permutation II
// DescriptionHintsSubmissionsDiscussSolution
// Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

// Example 1:

// Input: "aabb"
// Output: ["abba", "baab"]
// Example 2:

// Input: "abc"
// Output: []

// 思路 只需要pumutation 一半！
class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList();
        if(s.length() == 0) return res;
        int[] ct = new int[256];
        for(char ch : s.toCharArray()){
            ct[(int)ch]++;
        } 
        String mid = "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 256 ;i++){
            if(ct[i] % 2 == 1){
                if(!mid.equals("")) return res;
                mid += ((char)i);
            }
            for(int j = 0 ; j < ct[i] / 2 ; j++){
                sb.append((char)i);
            }
        }
        dfs(res , sb.toString() , new boolean[s.length()] ,"");
        for(int i = 0 ; i < res.size() ; i++){
            StringBuilder tmp = new StringBuilder(res.get(i));
            res.set(i , res.get(i) + mid + tmp.reverse());
        }
        return res;
    }
    private void dfs(List<String> res , String s , boolean[] visited , String cur){
        if(cur.length() == s.length()){
            res.add(cur);
            return;
        }
        for(int i = 0 ; i < s.length() ; i++){
            if(visited[i])continue;
            if( i > 0 && s.charAt(i) == s.charAt(i - 1) && !visited[i - 1])continue;
            visited[i] = true;
            dfs(res , s , visited , cur + s.charAt(i));
            visited[i] = false;    
        }
    }
}





//dfs 裸搜 TLE
class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList();
        if(s.length() == 0) return res;
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        dfs(res , new String(arr) , new boolean[s.length()] ,"");
        return res;
    }
    private void dfs(List<String> res , String s , boolean[] visited , String cur){
        if(cur.length() == s.length() && check(cur)){
            res.add(cur);
            return;
        }
        for(int i = 0 ; i < s.length() ; i++){
            if(visited[i])continue;
            if( i > 0 && s.charAt(i) == s.charAt(i - 1) && !visited[i - 1])continue;
            visited[i] = true;
            dfs(res , s , visited , cur + s.charAt(i));
            visited[i] = false;    
        }
    }
    private boolean check(String cur){
        int s = 0 , e = cur.length() - 1;
        while(s < e){
            if(cur.charAt(s++) != cur.charAt(e--)) return false;
        }
        return true;
    }

}