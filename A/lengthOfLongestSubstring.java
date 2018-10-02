// Longest Substring Without Repeating Characters
// Given a string, find the length of the longest substring without repeating characters.

// Example
// For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.

// For "bbbbb" the longest substring is "b", with the length of 1.

// Challenge
// O(n) time
public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        HashMap<Character , Integer> map = new HashMap();
        int res = -1;
        int i = 0 , preIndex = 0;
        for( ; i < s.length() ;i++){
            if(map.containsKey(s.charAt(i))){
                res = Math.max(i - preIndex  , res);
                preIndex = map.get(s.charAt(i)) + 1;
                map.clear();
                map.put(s.charAt(preIndex), preIndex);
                i = preIndex;
            }
            else{
                map.put(s.charAt(i) , i);
            }
        }
        res = Math.max(i - preIndex , res);
        return res;

    }
}

public class Solution {
    /**
     * @param s: a string
     * @return: an integer 
     */
     //方法一：
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[256]; // map from character's ASCII to its last occured index
        
        int j = 0;
        int i = 0;
        int ans = 0;
        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && map[s.charAt(j)]==0) {
                map[s.charAt(j)] = 1;
                ans = Math.max(ans, j-i + 1);
                j ++;
            }
            map[s.charAt(i)] = 0;
        }
        
        return ans;
    }
}


public class Solution {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        // write your code here
        int[] cnt = new int[256];
        char[] sc = s.toCharArray();

        int ans = 0;
        for (int l = 0, r = 0; r < s.length(); r++) {
            cnt[sc[r]]++;
            while (cnt[sc[r]] > 1) {
                cnt[sc[l]]--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}