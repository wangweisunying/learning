// // Find Substring
// Given the length k, find all substrings of length k in the string str.The characters of a substring 
// can not be repeated and output the number of substrings that satisfy such conditions (the same substring is counted only 1 times).

// Example
// Given str = "abc", k = 2, output 2.

// Explanation:
// Characters are not repeated, and substrings of length k have "ab", "bc".
// Given str = "abc", k = 2, output 2.

// Explanation:
// Characters are not repeated, and substrings of length k have "a", "b".”c”.
// Notice
// |str| <= 100000.
// k <= 100000.
// All characters are lowercase.
public class Solution {
    /**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        if(str.length() < k){
            return 0;
        }
        HashSet<String> set = new HashSet();
        int[] hash = new int[26];
        for(char ch : str.substring(0 , k).toCharArray()){
            hash[ch - 'a']++;
        }
        if(isV(hash)){
            set.add(str.substring(0 , k));
        }       
        for(int i = 1 ; i <= str.length() - k ; i ++){
            hash[str.charAt(i - 1) - 'a']--;
            hash[str.charAt(i + k - 1) - 'a']++;
            if(isV(hash)){
                set.add(str.substring(i , i + k));
            }
        }
        return set.size();
    }
    private boolean isV(int[] arr){
        for(int i : arr){
            if(i > 1){
                return false;
            }
        }
        return true;
    }
}

public class Solution {
    /**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        // Write your code here
        Set<String> set = new HashSet<>();
        int[] array = new int[26];
        
        for (int i = 0; i < k - 1 && i < str.length(); i++) {
            array[str.charAt(i) - 'a']++;
        }
        
        for (int i = k; i <= str.length(); i++) {
            array[str.charAt(i - 1) - 'a']++;
            
            if (i > k) {
                array[str.charAt(i - k - 1) - 'a']--;
            }
            
            if (!isValid(array)) {
                continue;
            }
            
            String sub = str.substring(i - k, i);
            set.add(sub);
        }
        
        return set.size();
    }
    
    private boolean isValid(int[] array) {
        for (int i : array) {
            if (i > 1) {
                return false;
            }
        }
        
        return true;
    }
    
}




public class Solution {
    /**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        if(str.length() < k){
            return 0;
        }
        HashSet<String> set = new HashSet();
        HashMap<Character , Integer> map = new HashMap();
        for(char ch : str.substring(0 , k).toCharArray()){
            if(map.containsKey(ch)){
                map.put(ch , map.get(ch) + 1);
            }
            else{
                map.put(ch , 1);
            }
        }
        if(map.size() == k){
            set.add(str.substring(0 , k));
        }       
        for(int i = 1 ; i <= str.length() - k ; i ++){
            if(map.get(str.charAt(i - 1)) == 1){
                map.remove(str.charAt(i - 1));
            }
            else{
                map.put(str.charAt(i - 1) , map.get(str.charAt(i - 1)) - 1);
            }
            if(map.containsKey(str.charAt(i + k - 1))){
                map.put(str.charAt(i + k - 1) , map.get(str.charAt(i + k - 1)) + 1);
            }
            else{
                map.put(str.charAt(i + k - 1) , 1);
            }
            String now = str.substring(i , i + k);
            if(map.size() == k){
                set.add(now);
            }
        }
        return set.size();
    }
}


public class Solution {
    /**
     * @param str: The string
     * @param k: The length of the substring
     * @return: The answer
     */
    public int findSubstring(String str, int k) {
        // Write your code here
        if (k > 26) {
            return 0;
        }
        HashSet<String> stringSet = new HashSet<String>();
        int ans = 0;
        for (int i = 0; i + k - 1 < str.length(); i++) {
            HashSet<Character> hashSet = new HashSet<Character>();
            boolean isLegal = true;
            for (int j = i; j <= i + k - 1; j++) {
                if (hashSet.contains(str.charAt(j))) {
                    isLegal = false;
                    break;
                } else {
                    hashSet.add(str.charAt(j));
                }
            }
            if (isLegal) {
                String s = str.substring(i, i + k);
                if (stringSet.contains(s)) {
                    continue;
                } else {
                    stringSet.add(s);
                    ans++;
                }
            }
        }
        return ans;
    }
}