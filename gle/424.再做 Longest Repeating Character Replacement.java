// 424. Longest Repeating Character Replacement
// DescriptionHintsSubmissionsDiscussSolution
// Given a string that consists of only uppercase English letters, 
// you can replace any letter in the string with another letter at most k times.
//  Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

// Note:
// Both the string's length and k will not exceed 10^4.

// Example 1:

// Input:
// s = "ABAB", k = 2

// Output:
// 4

// Explanation:
// Replace the two 'A's with two 'B's or vice versa.
// Example 2:

// Input:
// s = "AABABBA", k = 1                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              

// Output:
// 4

// Explanation:
// Replace the one 'A' in the middle with 'B' and form "AABBBBA".
// The substring "BBBB" has the longest repeating letters, which is 4.

//this sliding window only used for get statistics of the count , the length of the sliding window should not greateer than the maxCt + k!
// maintain a varable maxCt stand for the main char you will keep , e - s + 1 - maxCt is the restCt compare to K 
class Solution {
    public int characterReplacement(String str, int k) {
        int[] ct = new int[26];
        int maxCt = 0;
        int s = 0 , e = 0;
        for(;  e < str.length() ; e++){
            ct[str.charAt(e) - 'A']++;
            maxCt = Math.max(maxCt , ct[str.charAt(e) - 'A']);
            if(maxCt + k < e - s + 1 ){
                ct[str.charAt(s++) - 'A']--;
            }
        }
        return e - s; // e = str.length()
    }
}









class Solution {
    public int characterReplacement(String str, int k) {
        Map<Character,Integer> map = new HashMap();
        int res = 0;
        int s = 0 , e = 0 , maxCt = 0;
        while(e < str.length()){
            char cE = str.charAt(e);
            map.put(cE , map.getOrDefault( cE , 0 ) + 1);
            maxCt = Math.max(map.get(cE) , maxCt);
            while(e - s + 1 - maxCt > k){
                char cS = str.charAt(s);
                map.put(cS , map.get(cS) - 1);
                if(map.get(cS) == 0){
                    map.remove(cS);
                } 
                s++;
            }
            res = Math.max( res , e - s + 1);
            e++;
        }
        return res;
    }
}
//
    public int characterReplacement(String s, int k) {
        int[] count = new int[128];
        int start = 0, end = 0, max = 0;
        for ( ; end < s.length(); end++) {
            max = Math.max(max, ++count[s.charAt(end)]);
            if (max + k <= end - start) {count[s.charAt(start++)]--;}
        }
        return s.length() - start;
    }
// "AABABBA"
// 1
// Output:
// 2
// Expected:
// 4

// Awesome solution, but it needs some more detailed explanation.
// Many people know the problem can be reiterated as :
// "longest substring where (length - max occurrence) <= k"
// The key point is we are focusing on "longest".
// So assume we initially found a valid substring, what do we do next?

// Still valid even appended by one more char——then we are happy, just expand the window
// It became invalid. But at this time, do we have to shrink the window?
// ——No, we shift the whole window rightwards by one unit. So that the length is unchanged.
// The reason for that is , any index smaller than original "start",
//  will never have the chance to lead a longer valid substring than current length of our window.
// Do we need to update max in time?
// ——No. Once again, we focus on "longest". The length of valid substring is determined by what?
// Max Occurrence + k
// We only need to update max occurrence when it becomes larger, because only that can we generate a longer valid substring.
// If we can't surpass the historic max occurrence, then we can not generate a longer valid substring from current "start",
//  I mean the new windows's left bound. To some extend, this becomes a game to find max occurrence.
// Or , a better understanding is:
// "A game to eliminate inferior 'left bounds'.
 public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - maxCount > k) {
                // The longest substring will always contain the most frequent character, in that way, we do not need to change the maxCount.
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
// There's no edge case for this question. 
// The initial step is to extend the window to its limit, that is, the longest we can get to with maximum number of modifications.
//  Until then the variable start will remain at 0.

// Then as end increase, the whole substring from 0 to end will violate the rule,
//  so we need to update start accordingly (slide the window). We move start to the right until the whole string satisfy the constraint again. Then each time we reach such situation, we update our max length.