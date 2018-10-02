
// // 841String Replace
// // Given two identical-sized string array A, B and a string S. 
// All substrings A appearing in S are replaced by B.
// (Notice: From left to right, it must be replaced if it can be replaced. If there are multiple alternatives, replace longer priorities. 
// After the replacement of the characters can't be replaced again.)

// // Example
// // Given A = ["ab","aba"], B = ["cc","ccc"], S = "ababa", return "cccba".

// // In accordance with the rules, the substring that can be replaced is "ab" or "aba". Since "aba" is longer, we replace "aba" with "ccc".  
// // Given A = ["ab","aba"], B = ["cc","ccc"] ,S = "aaaaa" ,return "aaaaa".

// // S does not contain strings in A, so no replacement is done.
// // Given A = ["cd","dab","ab"], B = ["cc","aaa","dd"], S = "cdab", return "ccdd".

// // From left to right, you can find the "cd" can be replaced at first, so after the replacement becomes "ccab", then you can find "ab" can be replaced, so the string after the replacement is "ccdd".
// // Notice
// // The size of each string array does not exceed 100, the total string length does not exceed 50000.
// // The lengths of A [i] and B [i] are equal.
// // The length of S does not exceed 50000.
// // All characters are lowercase letters.
// // We guarantee that the A array does not have the same string

public class Solution {
    /**
     * @param a: The A array
     * @param b: The B array
     * @param s: The S string
     * @return: The answer
     */
 public String stringReplace(String[] a, String[] b, String s) {
        TreeMap<String , String> map = new TreeMap(new Comparator<String>(){
            @Override
            public int compare(String s1 , String s2){
                return s2.length() - s1.length(); 
            }
        });
        for(int i = 0 ; i < a.length ; i++){
            map.put(a[i] , b[i]);
        }
        int pointer = 0;
        String tmp = s;
        while(pointer < s.length()){
            boolean found = false;
            for(String cur : map.keySet()){
                if(!tmp.substring(pointer).contains(cur)){
                    continue;
                }
                pointer += tmp.indexOf(cur) + cur.length();
                tmp = tmp.replaceFirst(cur , map.get(cur));
                
                s = s.substring(0 , pointer) + tmp;
                tmp = tmp.substring(cur.length());
                map.remove(cur);
                found = true;
                break;
            }
            if(!found){
                pointer++;
            }
        }
        return s;


    }
}


public class Solution {
    /**
     * @param a: The A array
     * @param b: The B array
     * @param s: The S string
     * @return: The answer
     */
    int max(int a, int b) {
        if(a > b)
            return a;
        return b;
    }
    int min(int a, int b) {
        if(a < b)
            return a;
        return b;
    }
    public String stringReplace(String[] a, String[] b, String s) {
       StringBuilder builder = new StringBuilder(s);
       int seed = 33;
       int mod = 1000000007;
       int ans, mxLen = -1;
       Vector<Integer> aHash = new Vector<Integer>();
       Vector<Integer> sHash = new Vector<Integer>();
       Vector<Integer> base = new Vector<Integer>();
       
       for(int i = 0; i < a.length; i++) {
           ans = 1;
           mxLen = max(mxLen, a[i].length());
           for(int j = 0; j < a[i].length(); j++) {
                ans = (int) (((long)ans * (long)seed + (long)a[i].charAt(j) - (long)'a') % (long)mod);
           }
           aHash.add(ans);
       }
       ans = 1;
       sHash.add(ans);
       mxLen = max(mxLen, s.length());
       for(int i = 0; i < s.length(); i++) {
           ans = (int) (((long)ans * (long)seed + (long)s.charAt(i) - (long)'a') % (long)mod);
           sHash.add(ans);
       }
       ans = 1;
       base.add(ans);
       for(int i = 0; i < mxLen; i++) {
           ans = (int) ((long)ans * (long)seed  % (long)mod);
           base.add(ans);
       }
       for(int i = 0; i < s.length(); i++) {
           int maxLen = -1;
           int index = -1;
           for(int j = 0; j < a.length; j++) {
               int len = a[j].length();
               int l = i + 1;
               int r = i + len;
               if(r > s.length()  ) {
                   continue;
               }
               int sHashValue = (int)(((long)sHash.get(r) - (long)(base.get(r - l + 1)) * (long)sHash.get(l - 1) % (long)mod + (long)mod) % (long)mod);
               int aHashValue = (aHash.get(j) - base.get(len) + mod) % mod;
               if(sHashValue == aHashValue && len > maxLen) {
                   maxLen = len;
                   index = j;
               }
           }
           if(maxLen != -1) {
               builder.replace(i, i + maxLen, b[index]);
               i += maxLen - 1;
           }
       }
       return builder.toString();
    }
}