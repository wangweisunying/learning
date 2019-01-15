// 91. Decode Ways
// DescriptionHintsSubmissionsDiscussSolution
// A message containing letters from A-Z is being encoded to numbers using the following mapping:

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given a non-empty string containing only digits, determine the total number of ways to decode it.

// Example 1:

// Input: "12"
// Output: 2
// Explanation: It could be decoded as "AB" (1 2) or "L" (12).
// Example 2:

// Input: "226"
// Output: 3
// Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
















// print out result
    public static void main(String[] args) throws Exception {
        numDecodings("123456789");
    }
    public static void numDecodings(String s){
        Map<Integer , Character> map = new HashMap();
        for(int i = 1 ; i <= 26 ; i++){
            map.put( i  , (char)(64 + i));
        }
        List<List<String>> res = new ArrayList();
        dfs(res , s , new ArrayList() , map);
        System.out.println(res);
    }

    private static void dfs(List<List<String>> res, String s, ArrayList list ,Map<Integer , Character> map) {
        if(s.length() == 0){
            res.add(new ArrayList(list));
            return;
        }
        if(s.charAt(0) - '0' > 0){
            list.add("" + map.get(s.charAt(0) - '0') );
            dfs(res , s.substring(1), list , map);
            list.remove(list.size() - 1);
        }
        if(s.length() > 1 && Integer.parseInt(s.substring(0 , 2)) <= 26 && s.charAt(0)!= '0'){
            list.add(map.get(Integer.parseInt(s.substring(0 , 2))));
            dfs(res , s.substring(2), list , map);
            list.remove(list.size() - 1);
        
        }
        
    }

public class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i));
            int second = Integer.valueOf(s.substring(i-2, i));
            if(first >= 1 && first <= 9) {
               dp[i] += dp[i-1];  
            }
            if(second >= 10 && second <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }
}
// dp
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[n] = 1;
        f[n - 1] = s.charAt(n - 1) - '0' > 0 ? 1 : 0;  
        for(int i = n - 2 ; i >= 0 ; i--){
            f[i] = (s.charAt(i) - '0' > 0 ? f[i + 1] : 0);
            f[i] += (Integer.parseInt(s.substring(i , i + 2)) <= 26 && s.charAt(i) >'0')? f[i + 2] : 0;
        }
        return f[0];
    }
}

class Solution {
    public int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[] f = new int[n + 1];
        f[n] = 1;
        for(int i = n - 1 ; i >= 0 ; i--){
            if(s.charAt(i) == '0'){
                f[i] = 0;
                continue;
            }
            if(s.charAt(i) <= '9' && s.charAt(i) >= '1'){
                f[i] = f[i + 1];
            }
            if(i + 2 <= n  && Integer.parseInt(s.substring(i , i + 2)) <= 26 && Integer.parseInt(s.substring(i , i + 2)) >= 10){
                f[i] += f[i + 2];
            }
            
        }
        return f[0];
    }
}