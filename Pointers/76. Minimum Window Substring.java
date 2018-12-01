// 76. Minimum Window Substring
// DescriptionHintsSubmissionsDiscussSolution
// Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

// Example:

// Input: S = "ADOBECODEBANC", T = "ABC"
// Output: "BANC"
// Note:

// If there is no such window in S that covers all characters in T, return the empty string "".
// If there is such window, you are guaranteed that there will always be only one unique minimum window in S.\
// slide window template
class Solution {
    public String minWindow(String str, String t) {
        if(str.length() < t.length()){
            return "";
        }
        HashMap<Character , Integer> map = new HashMap();
        for(char ch : t.toCharArray()){
            map.put(ch , map.getOrDefault( ch , 0 ) + 1 );
        }
        int ct = map.size();
        int s = 0 , e = 0;
        int len = Integer.MAX_VALUE;
        int begin = 0;
        while(e < str.length()){
            if(map.containsKey(str.charAt(e))){
                map.put(str.charAt(e) , map.get(str.charAt(e)) - 1 );
                if(map.get(str.charAt(e)) == 0){
                    ct--;
                }
            }
            e++;
            while(ct == 0){
                if(map.containsKey(str.charAt(s))){
                    map.put(str.charAt(s) , map.get(str.charAt(s)) + 1 );
                    if(map.get(str.charAt(s)) > 0){
                        ct++;
                    }
                }
                if(len > e - s){
                    len = e - s;
                    begin = s;
                }
                s++;
            }
        }
        return len == Integer.MAX_VALUE ? "" : str.substring(begin , begin + len);

    }
} 


//tmplate

the template:
public class Solution {
    public List<Integer> slidingWindowTemplateByHarryChaoyangHe(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        
        //create a hashmap to save the Characters of the target substring.
        //(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.
        
        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int begin = 0, end = 0;
        
        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE; 
        
        //loop at the begining of the source string
        while(end < s.length()){
            
            char c = s.charAt(end);//get a character
            
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);// plus or minus one
                if(map.get(c) == 0) counter--;//modify the counter according the requirement(different condition).
            }
            end++;
            
            //increase begin pointer to make it invalid/valid again
            while(counter == 0 /* counter condition. different question may have different condition */){
                
                char tempc = s.charAt(begin);//***be careful here: choose the char at begin pointer, NOT the end pointer
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
                    if(map.get(tempc) > 0) counter++;//modify the counter according the requirement(different condition).
                }
                
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                
                begin++;
            }
        }
        return result;
    }
}







class Solution {
    public String minWindow(String str, String t) {
        int[] hash = new int[128];  
        for(char ch : t.toCharArray()){
            hash[ch] ++;
        }
        int ct = t.length();
        String res = "";
        int min = str.length() + 1;
        int s = 0 , f = 0;
        
        while(f < str.length()){
            //只针对出现的char 而且需要的个数计数 只需要一个 B， 出现了2个B 此时hash[b] <0 就不用计数 //其他字母不会超过0 ；
            if(hash[str.charAt(f++)]-- > 0){
                ct--;
            }
            while(ct == 0){
                if(f - s < min){
                   min = f - s ;
                   res = str.substring(s , f);
                }      
                // 出现的字母为0时 此时是临界状态， 个数与t 匹配 ，不多不少，ct 才计数
                if(hash[str.charAt(s++)]++ ==  0){
                    ct++;
                }
            }
        }
        return res;
    }
}

// For most substring problem, we are given a string and need to find a substring of it which satisfy some restrictions.
//  A general way is to use a hashmap assisted with two pointers. The template is given below.

int findSubstring(string s){
        vector<int> map(128,0);
        int counter; // check whether the substring is valid
        int begin=0, end=0; //two pointers, one point to tail and one  head
        int d; //the length of substring

        for() { /* initialize the hash map here */ }

        while(end<s.size()){

            if(map[s[end++]]-- ?){  /* modify counter here */ }

            while(/* counter condition */){ 
                 
                 /* update d here if finding minimum*/

                //increase begin to make it invalid/valid again
                
                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
            }  

            /* update d here if finding maximum*/
        }
        return d;
  }

