// 395. Longest Substring with At Least K Repeating Characters
// DescriptionHintsSubmissionsDiscussSolution
// Find the length of the longest substring T of a given string
//  (consists of lowercase letters only) such that every character in T appears no less than k times.

// Example 1:

// Input:
// s = "aaabb", k = 3

// Output:
// 3

// The longest substring is "aaa", as 'a' is repeated 3 times.
// Example 2:

// Input:
// s = "ababbc", k = 2

// Output:
// 5

// The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.




// For each h, apply two pointer technique to find the longest substring with at least K repeating characters and the number of unique characters in substring is h.

public class Solution {
    public int longestSubstring(String s, int k) {
        if(s.length() < k){
            return 0;
        }
        int[] hash = new int[26];
        for(int i = 0 ; i < s.length() ; i ++){
            hash[s.charAt(i) - 'a'] ++;
        }


        boolean modified = false;
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0 ; i < s.length() ; i ++){
            if(hash[s.charAt(i) - 'a'] < k){
                modified = true;
                sb.setCharAt(i , ',');
            }
        }
        if(!modified){
            return s.length();
        }
        
        int res = 0;
        for(String subString : sb.toString().split(",")){
            res = Math.max(res , longestSubstring(subString , k));
        }
        return res;
    }
}



















public class Solution {
    public int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        int h, i, j, idx, max = 0, unique, noLessThanK;
        
        for (h = 1; h <= 26; h++) {
            Arrays.fill(counts, 0);
            i = 0; 
            j = 0;
            unique = 0;
            noLessThanK = 0;
            while (j < str.length) {
                if (unique <= h) {
                    idx = str[j] - 'a';
                    if (counts[idx] == 0)
                        unique++;
                    counts[idx]++;
                    if (counts[idx] == k)
                        noLessThanK++;
                    j++;
                }
                else {
                    idx = str[i] - 'a';
                    if (counts[idx] == k)
                        noLessThanK--;
                    counts[idx]--;
                    if (counts[idx] == 0)
                        unique--;
                    i++;
                }
                if (unique == h && unique == noLessThanK)
                    max = Math.max(j - i, max);
            }
        }
        
        return max;
    }
}