
// Minimum Window Substring
// Given a string source and a string target, find the minimum window in source which will contain all the characters in target.

// Example
// For source = "ADOBECODEBANC", target = "ABC", the minimum window is "BANC"

// Challenge
// Can you do it in time complexity O(n) ?

// Clarification
// Should the characters in minimum window has the same order in target?

// Not necessary.
// Notice
// If there is no such window in source that covers all characters in target, return the emtpy string "".
// If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in source.
// The target string may contain duplicate characters, the minimum window should cover all characters including the duplicate characters in target.



//sliding window
public class Solution {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
     */
    public String minWindow(String source, String target) {
        String res = "";
        int min = Integer.MAX_VALUE;
        if (source == null || source.length() == 0) {
            return res;
        }
        int i = 0, j = 0;

        HashMap<Character, Integer> tmap = new HashMap();
        HashMap<Character, Integer> smap = new HashMap();
        for (char ch : target.toCharArray()) {
            if (tmap.containsKey(ch)) {
                tmap.put(ch, tmap.get(ch) + 1);
            } else {
                tmap.put(ch, 1);
            }
        }
        char[] arr = source.toCharArray();
        for (; i < arr.length; i++) {
            while (j < arr.length && !isV(smap, tmap)) {
                if (smap.containsKey(arr[j])) {
                    smap.put(arr[j], smap.get(arr[j]) + 1);
                } else {
                    smap.put(arr[j], 1);
                }
                j++;
            }
            if (min > j - i && isV(smap, tmap)) {
                min = j - i;
                res = source.substring(i, j);
            }
            smap.put(arr[i], smap.get(arr[i]) - 1);
        }
        return res;

    }

    private boolean isV(HashMap<Character, Integer> smap, HashMap<Character, Integer> tmap) {
        for (char key : tmap.keySet()) {
            if (smap.containsKey(key) && smap.get(key) >= tmap.get(key)) {
                continue;
            }
            return false;
        }
        return true;
    }
}


public class Solution {    
//方法一:
    int initTargetHash(int []targethash, String Target) {
        int targetnum =0 ;
        for (char ch : Target.toCharArray()) {
            targetnum++;
            targethash[ch]++;
        }
        return targetnum;
    }
    boolean valid(int []sourcehash, int []targethash) {
        
        for(int i = 0; i < 256; i++) {
            if(targethash[i] > sourcehash[i])    
                return false;
        }
        return true;
    }
    public String minWindow(String Source, String Target) {
        // queueing the position that matches the char in T
        int ans = Integer.MAX_VALUE;
        String minStr = "";
        
        int[] sourcehash = new int[256];
        int[] targethash = new int[256];
        
        initTargetHash(targethash, Target);
        int j = 0, i = 0;
        for(i = 0; i < Source.length(); i++) {
            while( !valid(sourcehash, targethash) && j < Source.length()  ) {
                sourcehash[Source.charAt(j)]++;
                j++;
            }
            if(valid(sourcehash, targethash) ){
                if(ans > j - i ) {
                    ans = Math.min(ans, j - i );
                    minStr = Source.substring(i, j );
                }
            }
            sourcehash[Source.charAt(i)]--;
        }
        return minStr;
    }
}