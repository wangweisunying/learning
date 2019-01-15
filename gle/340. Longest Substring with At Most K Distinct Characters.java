// 340. Longest Substring with At Most K Distinct Characters
// DescriptionHintsSubmissionsDiscussSolution
// Given a string, find the length of the longest substring T that contains at most k distinct characters.

// Example 1:

// Input: s = "eceba", k = 2
// Output: 3
// Explanation: T is "ece" which its length is 3.
// Example 2:

// Input: s = "aa", k = 1
// Output: 2
// Explanation: T is "aa" which its length is 2.


class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character , Integer> map = new HashMap();
        int l = 0 , r = 0 , len = 0;
        while(r < s.length()){
            char cr = s.charAt(r);
            map.put(cr , map.getOrDefault(cr , 0) + 1);
            
            while(map.size() > k){
                char cl = s.charAt(l);
                map.put(cl , map.get(cl) - 1);
                if(map.get(cl) == 0){
                    map.remove(cl);
                }
                l++;
            }
            len = Math.max(len , r - l + 1 );
            r++;
        }
        return len;
    }
}