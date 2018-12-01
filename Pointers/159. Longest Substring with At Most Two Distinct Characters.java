
// 159. Longest Substring with At Most Two Distinct Characters
// DescriptionHintsSubmissionsDiscussSolution
// Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

// Example 1:

// Input: "eceba"
// Output: 3
// Explanation: t is "ece" which its length is 3.
// Example 2:

// Input: "ccaabbb"
// Output: 5
// Explanation: t is "aabbb" which its length is 5.






class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String str) {
        HashMap<Character , Integer> map = new HashMap();
        int s = 0 , e = 0;
        char[] arr = str.toCharArray();
        int max = 0;
        for( ; e < arr.length ; e++){
            map.put(arr[e] , map.getOrDefault(arr[e] , 0) + 1 );   
            if(map.size() > 2){
                max = Math.max(max , e - s );
                while(map.size() > 2){
                    map.put(arr[s] , map.get(arr[s]) - 1);
                    if(map.get(arr[s]) == 0){
                        map.remove(arr[s]);
                    }
                    s++;
                }
            }
        }
        max = Math.max(max , e - s);
        return max;
        
    }
}