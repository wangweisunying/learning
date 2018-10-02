// Longest Substring with At Most K Distinct Characters
// Given a string s, find the length of the longest substring T that contains at most k distinct characters.

// Example
// For example, Given s = "eceba", k = 3,

// T is "eceb" which its length is 4.

// Challenge
// O(n), n is the size of the string s.

public class Solution {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() == 0){
            return 0;
        }
        HashMap<Character , Integer> map = new HashMap();
        int i = 0 , j = 0;
        int res = 0;
        char[] arr = s.toCharArray();
        for(; i < arr.length ; i++){
   
            while(j < arr.length && map.size() <= k){
                res = Math.max(res , j - i );
                if(map.containsKey(arr[j])){
                    map.put(arr[j] , map.get(arr[j]) + 1);
                }
                else{
                    map.put(arr[j] , 1 );
                }
                j++;
            }
            if(j == arr.length && map.size() <=k){
                res = Math.max(res , j - i );
            }
 
            if(map.get(arr[i]) == 1){
                map.remove(arr[i]);
            }
            else{
                map.put(arr[i] , map.get(arr[i]) - 1);
            }
    
        }
        return res;
    }
}