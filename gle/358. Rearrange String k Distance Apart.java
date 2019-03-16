// 358. Rearrange String k Distance Apart

// Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

// All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

// Example 1:

// Input: s = "aabbcc", k = 3
// Output: "abcabc" 
// Explanation: The same letters are at least distance 3 from each other.
// Example 2:

// Input: s = "aaabc", k = 3
// Output: "" 
// Explanation: It is not possible to rearrange the string.
// Example 3:

// Input: s = "aaadbbcc", k = 2
// Output: "abacabcd"
// Explanation: The same letters are at least distance 2 from each other.


class Solution {
    public String rearrangeString(String s, int k) {
        int[] count = new int[26];
        for(char c : s.toCharArray()){
            count[c - 'a']++;   
        }
        TreeMap<Integer , List<Character>> map = new TreeMap<>((a , b) -> (b - a));
        for(int i = 0 ; i < 26 ; i++){
            if(count[i] != 0){
                map.computeIfAbsent(count[i] , x -> new ArrayList()).add((char)('a' + i));
            }
        }
        
        if((map.firstKey() - 1) * k + 1 > s.length()) return "";
        char[] arr = new char[s.length()];
        Arrays.fill(arr, '@');
        int index = 0;
        while(!map.isEmpty()){
            TreeMap<Integer , List<Character>> tmpMap = new TreeMap<>((a , b) -> (b - a));
            for(int ct : map.keySet()){
                for(char c : map.get(ct)){
                    if(count[c - 'a'] > 0){
                        arr[index++] = c;
                        --count[c - 'a'];
                    }
                }
                if(ct - 1 > 0){
                    tmpMap.put(ct - 1 , map.get(ct));
                }
            }
            map = tmpMap;

        }
        return new String(arr);
        
    }
}
// "aabbcc"
// 2

// "aaabc"
// 2
// This is a greedy problem.
// Every time we want to find the best candidate: which is the character with the largest remaining count. Thus we will be having two arrays.
// One count array to store the remaining count of every character. Another array to keep track of the most left position that one character can appear.
// We will iterated through these two array to find the best candidate for every position. Since the array is fixed size, it will take constant time to do this.
// After we find the candidate, we update two arrays.

public class Solution {
    public String rearrangeString(String str, int k) {
        int length = str.length();
        int[] count = new int[26];
        int[] valid = new int[26];
        for(int i=0;i<length;i++){
            count[str.charAt(i)-'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(int index = 0;index<length;index++){
            int candidatePos = findValidMax(count, valid, index);
            if( candidatePos == -1) return "";
            count[candidatePos]--;
            valid[candidatePos] = index+k;
            sb.append((char)('a'+candidatePos));
        }
        return sb.toString();
    }
    
   private int findValidMax(int[] count, int[] valid, int index){
       int max = Integer.MIN_VALUE;
       int candidatePos = -1;
       for(int i=0;i<count.length;i++){
           if(count[i]>0 && count[i]>max && index>=valid[i]){
               max = count[i];
               candidatePos = i;
           }
       }
       return candidatePos;
   }
}