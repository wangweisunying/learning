// 3. Longest Substring Without Repeating Characters
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, find the length of the longest substring without repeating characters.

// Example 1:

// Input: "abcabcbb"
// Output: 3 
// Explanation: The answer is "abc", with the length of 3. 
// Example 2:

// Input: "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.
// Example 3:

// Input: "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3. 
//              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.


class Solution {
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int ss = 0 , f = 0;
        HashSet<Character> set = new HashSet();
        for(;f < s.length() ; f++){
            while(set.contains(s.charAt(f))){
                set.remove(s.charAt(ss++));
            }
            
            max = Math.max(f - ss + 1 , max);
            set.add(s.charAt(f));
        }
        return max;
    }
}